package com.atguigu.mxbmall.product.service.impl;

import com.atguigu.common.constant.ProductConstant;
import com.atguigu.mxbmall.product.dao.AttrAttrgroupRelationDao;
import com.atguigu.mxbmall.product.dao.AttrGroupDao;
import com.atguigu.mxbmall.product.dao.CategoryDao;
import com.atguigu.mxbmall.product.entity.AttrAttrgroupRelationEntity;
import com.atguigu.mxbmall.product.entity.AttrGroupEntity;
import com.atguigu.mxbmall.product.entity.CategoryEntity;
import com.atguigu.mxbmall.product.service.CategoryService;
import com.atguigu.mxbmall.product.vo.AttrRespVo;
import com.atguigu.mxbmall.product.vo.AttrVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.mxbmall.product.dao.AttrDao;
import com.atguigu.mxbmall.product.entity.AttrEntity;
import com.atguigu.mxbmall.product.service.AttrService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {
    @Autowired
    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Autowired
    AttrGroupDao attrGroupDao;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    CategoryService categoryService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 关联关系DAO
     * pms_attr_attrgroup_relation(colums:attr_id attr_group_id attr_sort)
     */

    @Transactional
    @Override
    public void saveAttr(AttrVo attr) {
        AttrEntity attrEntity = new AttrEntity();
        //保存attrEntity
        //利用attr的属性给attrEntity的属性赋值，前提是他们俩的属性名一直
        BeanUtils.copyProperties(attr, attrEntity);
        // 保存基本数据
        this.save(attrEntity);
        // 保存关联关系 - 保存AttrGroupId信息
        if(attr.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()) { //是基础类型才需要保存
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            relationEntity.setAttrGroupId(attr.getAttrGroupId());  //属性分组ID
            relationEntity.setAttrId(attrEntity.getAttrId());      //属性ID
            attrAttrgroupRelationDao.insert(relationEntity);
        }

    }

    @Override
    public PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String type){
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<AttrEntity>().eq("attr_type", "base".equalsIgnoreCase(type)?ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode():ProductConstant.AttrEnum.ATTR_TYPE_SALE.getCode());
        /**
         * 根据不同情况封装不同条件
         * 第一种情况 catelogId == 0,查询所有 ; != 0 ，拼条件：若catelogId等于指定的值，就查询
         */
        if(catelogId != 0) {
            queryWrapper.eq("catelog_id", catelogId);
        }
        String key = (String) params.get("key");
        if(!StringUtils.isEmpty(key)) {
            // attr_id attr_name
            queryWrapper.and((wrapper)->{
                wrapper.eq("attr_id", key).or().like("attr_like", key);
            });
        }
        /**
         * 使用 this.page 方法将分页条件 params 封装成 IPage 参数，如下：
         *  public IPage<T> page(IPage<T> page, Wrapper<T> queryWrapper) {
         *          return this.baseMapper.selectPage(page, queryWrapper);
         *  }
         */
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params), queryWrapper );


        /**
         * 查询出分页结果以后，再查询一遍分组信息和分类信息 pms_attr pms_attr_attrgroup_relation
         */
        PageUtils pageUtils = new PageUtils(page);
        List<AttrEntity> recodes = page.getRecords();
        //流式编程处理
        List<AttrRespVo> respVos = recodes.stream().map((attrEntity) -> {
            AttrRespVo attrRespVo = new AttrRespVo();
            // 将 attrEntity 拷贝到 attrRespVo 中
            BeanUtils.copyProperties(attrEntity, attrRespVo);
            /**
             *    attrRespVo 设置分类和分组的名字
             *    1 分组的组名查询 按照属性id - attr_id 查出分组id
             *    pms_attr_attrgroup_relation(colums:attr_id attr_group_id attr_sort)
             *    2 如果是销售属性，是不存在分组的
             */
            if ("base".equalsIgnoreCase(type)){
                AttrAttrgroupRelationEntity attrId = attrAttrgroupRelationDao.selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrEntity.getAttrId()));
                if(attrId != null) {
                    // 查出当前分组的信息
                    AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrId.getAttrGroupId());
                    // 然后设置组的名字
                    attrRespVo.setGroupName(attrGroupEntity.getAttrGroupName());
                }
            }

            // 分类信息
            CategoryEntity categoryEntity = categoryDao.selectById(attrEntity.getCatelogId());
            if(categoryEntity != null) {
                // 设置分类信息
                attrRespVo.setCatelogName(categoryEntity.getName());
            }
            return attrRespVo;
        }).collect(Collectors.toList());

        pageUtils.setList(respVos);

        return pageUtils;
    }

    @Override
    public AttrRespVo getAttrInfo(Long attrId) {
        // 查出当当前属性的详细信息
        AttrRespVo respVo = new AttrRespVo();
        AttrEntity attrEntity = this.getById(attrId);
        BeanUtils.copyProperties(attrEntity, respVo);

        if(attrEntity.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()){
            /**
             *  1 设置分组信息
             *  分组Id
             */
            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = attrAttrgroupRelationDao.selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrId));
            if(attrAttrgroupRelationEntity != null) {
                respVo.setAttrGroupId(attrAttrgroupRelationEntity.getAttrGroupId());
                // 分组名称
                AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrAttrgroupRelationEntity.getAttrGroupId());
                if(attrGroupEntity != null) {
                    respVo.setGroupName(attrGroupEntity.getAttrGroupName());
                }
            }
        }



        /**
         *  2 设置分类信息
         */
        Long catelogId = attrEntity.getCatelogId();
        Long[] catelogPath = categoryService.findCatelogPath(catelogId);
        respVo.setCatelogPath(catelogPath);
        CategoryEntity categoryEntity = categoryDao.selectById(catelogId);
        if(categoryEntity != null) {
            respVo.setCatelogName(categoryEntity.getName());
        }
        return respVo;
    }

    //保存时，要修改两张表
    @Transactional
    @Override
    public void updateAttr(AttrVo attr) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr, attrEntity);
        this.updateById(attrEntity);

        if(attrEntity.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()){
            /**
             * 修改分组关联
             */
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            relationEntity.setAttrGroupId(attr.getAttrGroupId());
            relationEntity.setAttrId(attr.getAttrId());

            //判断是新增还是删除
            Integer count = attrAttrgroupRelationDao.selectCount(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attr.getAttrId()));
            if (count > 0){
                attrAttrgroupRelationDao.update(relationEntity, new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attr.getAttrId()));
            }else{
                attrAttrgroupRelationDao.insert(relationEntity);
            }
        }



    }

}