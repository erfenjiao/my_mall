package com.atguigu.mxbmall.product.dao;

import com.atguigu.mxbmall.product.entity.CategoryBrandRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 品牌分类关联
 * 
 * @author erfenjiao
 * @email sunlightcs@gmail.com
 * @date 2022-09-18 10:23:10
 */
@Mapper
public interface CategoryBrandRelationDao extends BaseMapper<CategoryBrandRelationEntity> {
	
}
