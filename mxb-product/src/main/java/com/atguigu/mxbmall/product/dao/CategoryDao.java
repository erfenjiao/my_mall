package com.atguigu.mxbmall.product.dao;

import com.atguigu.mxbmall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author erfenjiao
 * @email gyf2002cc@gmail.com
 * @date 2023-05-27 20:54:29
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
