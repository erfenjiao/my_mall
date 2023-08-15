package com.atguigu.mxbmall.product.controller;

import java.util.Arrays;
import java.util.Map;

import com.atguigu.mxbmall.product.vo.AttrRespVo;
import com.atguigu.mxbmall.product.vo.AttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.atguigu.mxbmall.product.entity.AttrEntity;
import com.atguigu.mxbmall.product.service.AttrService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.R;



/**
 * 商品属性
 *
 * @author erfenjiao
 * @email gyf2002cc@gmail.com
 * @date 2023-05-28 10:38:28
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;

    /**
     * 查询规格参数
     * catelogId：点击分类，就带上分类Id
     * attrType： 0-销售属性 1-规格参数
     * 还需要模糊查询功能


     *  获取基础属性
     *  /product/attr/base/list/{catelogId}
     *  //@GetMapping("/base/list/{catelogId}")
     *
     *  获取销售属性
     *  /product/attr/sale/list/{catelogId}
     */
    @RequestMapping("/{attrType}/list/{catelogId}")
    public R baseList(@RequestParam Map<String, Object> params,
                      @PathVariable("catelogId") Long catelogId,
                      @PathVariable("attrType") String type){
        PageUtils page = attrService.queryBaseAttrPage(params, catelogId, type);
        return R.ok().put("page", page);
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 在级联菜单的修改功能中，在所属分类中需要回显当前分类的完整路径
     * 查询属性详情功能
     */
    @RequestMapping("/info/{attrId}")
    public R info(@PathVariable("attrId") Long attrId){
		//AttrEntity attr = attrService.getById(attrId);
        AttrRespVo attrRespVo = attrService.getAttrInfo(attrId);

        return R.ok().put("attr", attrRespVo);
    }

    /**
     * 保存
     * 基本保存： attrService.save(attr);

     */
    @RequestMapping("/save")
    public R save(@RequestBody AttrVo attr){
        attrService.saveAttr(attr);

        return R.ok();
    }


    /**
     * 在级联菜单的修改功能中，修改原先的属性，就会发送update请求
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AttrVo attr){
		//attrService.updateById(attr);
        attrService.updateAttr(attr);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] attrIds){
		attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }

}
