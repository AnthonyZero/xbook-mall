package com.xbook.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xbook.common.constant.SysConstant;
import com.xbook.common.enums.CodeMsgEnum;
import com.xbook.common.utils.MD5Util;
import com.xbook.dao.user.UserMapper;
import com.xbook.entity.user.User;
import com.xbook.user.service.UserService;
import com.xbook.user.service.exception.UserException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service(version = SysConstant.XBOOK_MALL_USER_VERSION)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    @Transactional
    public void register(User user) {
        //1.校验参数是否为空
        if(StringUtils.isBlank(user.getUsername()) ||
                StringUtils.isBlank(user.getEmail()) ||
                StringUtils.isBlank(user.getPassword()) ||
                StringUtils.isBlank(user.getQuestion()) ||
                StringUtils.isBlank(user.getAnswer())){
            throw new UserException(CodeMsgEnum.PARAMETER_NOTEXIST);
        }
        Integer count = userMapper.selectCount(new LambdaQueryWrapper<User>().eq(User::getUsername, user.getUsername()));
        if (count > 0) {
            throw new UserException(CodeMsgEnum.USERNAME_EXIST);
        }
        count = userMapper.selectCount(new LambdaQueryWrapper<User>().eq(User::getEmail, user.getEmail()));
        if (count > 0) {
            throw new UserException(CodeMsgEnum.EMAIL_EXIST);
        }
        user.setRole(SysConstant.ROLE_CUSTOME);
        user.setPassword(MD5Util.encrypt(user.getPassword()));
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
    }
}
