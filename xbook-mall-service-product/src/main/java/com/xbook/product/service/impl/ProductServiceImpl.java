package com.xbook.product.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.xbook.common.constant.SysConstant;
import com.xbook.dao.product.CategoryMapper;
import com.xbook.dao.product.ProductMapper;
import com.xbook.entity.product.Category;
import com.xbook.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

@Service(version = SysConstant.XBOOK_MALL_PRODUCT_VERSION)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public PageInfo pageProduct(String keyword, Integer categoryId, String orderBy, int pageNum, int pageSize) {

        return null;
    }

    @Override
    public List<Integer> selectCategoryChildrenById(Integer categoryId) {
        Set<Category> categorySet = Sets.newHashSet();
        findChildCategory(categorySet, categoryId); //构造获取子节点以及本身
        List<Integer> categoryIdList = Lists.newArrayList();
        if(categoryId != null){
            for(Category category : categorySet){
                categoryIdList.add(category.getId());
            }
        }
        return categoryIdList;
    }


    private Set<Category> findChildCategory(Set<Category> categorySet,Integer categoryId){
        Category category = categoryMapper.selectById(categoryId);
        if(category != null){
            categorySet.add(category);
        }
        //获取父节点下的子节点
        List<Category> categoryList = categoryMapper.selectList(new LambdaQueryWrapper<Category>().eq(Category::getParentId, categoryId));
        //递归
        for(Category categoryItem : categoryList){
            findChildCategory(categorySet,categoryItem.getId());
        }
        return categorySet;
    }
}
