package com.xbook.user.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.xbook.common.constant.SysConstant;
import com.xbook.dao.user.mapper.UserMapper;
import com.xbook.product.service.ProductService;
import com.xbook.redis.service.RedisService;
import com.xbook.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service(version = SysConstant.XBOOK_MALL_USER_VERSION)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Reference(version = SysConstant.XBOOK_MALL_REDIS_VERSION)
    private RedisService redisService;
    @Reference(version = SysConstant.XBOOK_MALL_PRODUCT_VERSION)
    private ProductService productService;

    @Override
    @Transactional
    public void login(String username, String password) {

    }
}
