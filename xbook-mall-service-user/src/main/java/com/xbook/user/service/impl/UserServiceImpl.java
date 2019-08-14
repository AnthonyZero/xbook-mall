package com.xbook.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xbook.common.constant.SysConstant;
import com.xbook.dao.user.entity.User;
import com.xbook.dao.user.mapper.UserMapper;
import com.xbook.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = SysConstant.XBOOK_MALL_USER_VERSION)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void login(String username, String password) {
        User user = userMapper.selectById(1);
        System.out.println(user);
    }
}
