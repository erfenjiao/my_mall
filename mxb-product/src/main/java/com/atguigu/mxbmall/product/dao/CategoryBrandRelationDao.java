package com.atguigu.mxbmall.product.dao;

import com.atguigu.mxbmall.product.entity.CategoryBrandRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

/**
 * 品牌分类关联
 * 
 * @author erfenjiao
 * @email gyf2002cc@gmail.com
 * @date 2023-05-28 10:38:28
 */
@Mapper
public interface CategoryBrandRelationDao extends BaseMapper<CategoryBrandRelationEntity> {

    void updateCategroy(@Param("catId") Long catId, @Param("name") String name);
}
