package com.atguigu.mxbmall.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.mxbmall.product.dao.CategoryDao;
import com.atguigu.mxbmall.product.entity.CategoryEntity;
import com.atguigu.mxbmall.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    public List<CategoryEntity> listWithTree() {

        //1、查出所有分类 null条件
        List<CategoryEntity> entities = baseMapper.selectList(null);
        //2、组装成父子的树形结构

        //2.1 找到所有的一级分类 parent_cid = 0
        List<CategoryEntity> level1Menus = entities.stream()
                .filter((categoryEntity)->{
                    return categoryEntity.getParentCid() == 0;})
                .map((menu)->{
                    menu.setChildren(getChildren(menu, entities));
                    return menu;})
                .sorted((menu1, menu2)->{
                    return menu1.getSort() - menu2.getSort();})
                .collect(Collectors.toList());

        return level1Menus;
    }

    /**
     * 递归查找所有菜单的子菜单
     * @param root 当前菜单
     * @param all  所有菜单
     * @return
     */
    private List<CategoryEntity> getChildren(CategoryEntity root, List<CategoryEntity> all){
        List<CategoryEntity> children = all.stream()
                .filter(CategoryEntity -> CategoryEntity.getParentCid().equals(root.getCatId()))
                .map(categoryEntity -> {
                    //递归查找子菜单         当前菜单 categoryEntity
                    categoryEntity.setChildren(getChildren(categoryEntity, all));
                    return categoryEntity;
                })
                .sorted((menu1, menu2) -> {
                    //菜单排序 有可能空指针异常，添加条件判断
                    return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
                })
                .collect(Collectors.toList());
        return children;
    }

}