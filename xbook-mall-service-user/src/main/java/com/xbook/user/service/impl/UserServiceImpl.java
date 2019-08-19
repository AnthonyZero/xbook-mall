package com.xbook.user.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xbook.common.constant.SysConstant;
import com.xbook.common.core.Result;
import com.xbook.common.enums.CodeMsgEnum;
import com.xbook.common.redis.key.UserKey;
import com.xbook.common.utils.MD5Util;
import com.xbook.dao.user.UserMapper;
import com.xbook.entity.user.User;
import com.xbook.redis.service.RedisService;
import com.xbook.user.service.UserService;
import com.xbook.user.service.exception.UserException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service(version = SysConstant.XBOOK_MALL_USER_VERSION)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Reference(version = SysConstant.XBOOK_MALL_REDIS_VERSION)
    private RedisService redisService;


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

    @Override
    public void checkValid(String str, String type) {
        if(StringUtils.isBlank(str) || StringUtils.isBlank(type)) {
            throw new UserException(CodeMsgEnum.PARAMETER_NOTEXIST);
        }
        if(SysConstant.USERNAME.equalsIgnoreCase(type)) {
            Integer count = userMapper.selectCount(new LambdaQueryWrapper<User>().eq(User::getUsername, str));
            if (count > 0) {
                throw new UserException(CodeMsgEnum.USERNAME_EXIST);
            }
        }else if (SysConstant.EMAIL.equalsIgnoreCase(type)) {
            Integer count = userMapper.selectCount(new LambdaQueryWrapper<User>().eq(User::getEmail, str));
            if (count > 0) {
                throw new UserException(CodeMsgEnum.EMAIL_EXIST);
            }
        }
    }

    @Override
    public Result login(String username, String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new UserException(CodeMsgEnum.PARAMETER_NOTEXIST);
        }
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if (user == null){
            throw new UserException(CodeMsgEnum.USER_NOT_EXIST);
        }
        if (!user.getPassword().equals(MD5Util.encrypt(password))) {
            throw new UserException(CodeMsgEnum.PASSWORD_ERROR);
        }

        User result = new User();
        result.setId(user.getId());
        result.setUsername(username);
        String token = UUID.randomUUID().toString().replaceAll("-","");
        redisService.set(UserKey.loginUser, token, JSONObject.toJSONString(result));

        return Result.success(token);
    }
}
