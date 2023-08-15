package com.atguigu.mxbmall.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.mxbmall.product.dao.AttrGroupDao;
import com.atguigu.mxbmall.product.entity.AttrGroupEntity;
import com.atguigu.mxbmall.product.service.AttrGroupService;
import org.springframework.util.StringUtils;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    /**
     *  GET  /product/attrgroup/list/{catelogId}
     *  public R list(@RequestParam Map<String, Object> params, @PathVariable("catelogId") Long catelogId)
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catelogId){
        String key = (String) params.get("key");
        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<AttrGroupEntity>();

        /**
            在属性分组中点击 查询全部 按钮，如果输入框中由v数据，一同带着模糊查询
            即，无论是 查询 还是 查询全部 ，都需要有模糊查询的功能，故此将共同代码提取出来，放在最前面
         */
        // select * from pms_attr_group where catelogId = ? and (attr_group_id = key or attr_group_name like %key%)
        if (StringUtils.hasLength(key)){
            wrapper.and((obj)->{
                obj.like("attr_group_id", key).or().eq("attr_group_name", key);
            });
        }
        if(catelogId == 0) {
            // 没有选中三级分类，查询所有
            IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params), wrapper);
            // 将 page 传入 PageUtils，自动解析出分页信息
            return new PageUtils(page);
        }else {
            // 按照三级分类查询
            // select * from pms_attr_group where catelogId = ? and (attr_group_id = key or attr_group_name like %key%)
            wrapper.eq("catelog_id", catelogId);
            /**
             * p72 放在这里，之后改变为最上方，原因：？查询所有时，没有附带关键字查询 无论是模糊查询还是全部查询都需要关键字查询
             * if (StringUtils.hasLength(key)){
             *     wrapper.and((obj)->{
             *         obj.like("attr_group_id", key).or().eq("attr_group_name", key);
             *     });
             * }
             * like ： %key%
             */
            // 查哪张表，就找哪张表的实体类 例如 pms_attr_group - AttrGroupEntity
            IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params), wrapper);

            return new PageUtils(page);
        }
    }

}