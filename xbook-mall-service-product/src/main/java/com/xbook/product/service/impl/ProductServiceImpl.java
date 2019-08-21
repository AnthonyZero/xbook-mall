package com.xbook.product.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.xbook.common.constant.SysConstant;
import com.xbook.dao.product.CategoryMapper;
import com.xbook.dao.product.ProductMapper;
import com.xbook.entity.product.Category;
import com.xbook.entity.product.Product;
import com.xbook.entity.product.ProductData;
import com.xbook.product.service.ProductService;
import org.apache.commons.lang3.StringUtils;
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
        List<Integer> categoryIdList = Lists.newArrayList();
        if (categoryId != null) {
            Category category = categoryMapper.selectById(categoryId);
            if(category == null && StringUtils.isBlank(keyword)) {
                PageHelper.startPage(pageNum, pageSize); //分类不存在关键字没有  返回空集合
                List<ProductData> productListVoList = Lists.newArrayList();
                PageInfo pageInfo = new PageInfo(productListVoList);
                return pageInfo;
            }
            categoryIdList = selectCategoryChildrenById(categoryId);
        }
        if(StringUtils.isNotBlank(keyword)){
            keyword = new StringBuilder().append("%").append(keyword).append("%").toString();
        }
        //如果orderBy不为空
        if(StringUtils.isNotBlank(orderBy)){
            if(SysConstant.PRICE_ASC_DESC.contains(orderBy)){
                String[] orderByArray = orderBy.split("_");
                //特定的格式
                PageHelper.orderBy(orderByArray[0]+" "+orderByArray[1]);
            }
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Product> products = productMapper.selectProductSearch(StringUtils.isBlank(keyword) ? "" : keyword, categoryIdList.size() == 0 ? null : categoryIdList);

        List<ProductData> data = Lists.newArrayList();
        for(Product product : products){
            ProductData productData = setupProductDataVo(product);
            data.add(productData);
        }
        //返回
        PageInfo pageInfo = new PageInfo(products);
        pageInfo.setList(data);
        return pageInfo;
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


    private ProductData setupProductDataVo(Product product) {
        ProductData productData = new ProductData();
        productData.setId(product.getId());
        productData.setSubtitle(product.getSubtitle());
        productData.setMainImage(product.getMainImage());
        productData.setPrice(product.getPrice());
        productData.setCategoryId(product.getCategoryId());
        productData.setName(product.getName());
        productData.setStatus(product.getStatus());
        productData.setImageHost(SysConstant.IMG_HOST);
        return productData;
    }
}
