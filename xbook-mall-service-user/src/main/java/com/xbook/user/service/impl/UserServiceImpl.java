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
import java.util.List;
import java.util.UUID;

@Service(version = SysConstant.XBOOK_MALL_USER_VERSION)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Reference(version = SysConstant.XBOOK_MALL_REDIS_VERSION, check = false)
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

    @Override
    public User getUserInfo(Integer userId) {
        if (userId == null || userId <= 0) {
            throw new UserException(CodeMsgEnum.PARAMETER_ERROR);
        }
        return userMapper.selectById(userId);
    }

    @Override
    public Result getQuestionByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return Result.error(CodeMsgEnum.PARAMETER_NOTEXIST);
        }
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if (user == null) {
            return Result.error(CodeMsgEnum.USER_NOT_EXIST);
        }
        if (StringUtils.isBlank(user.getQuestion())) {
            return Result.error(CodeMsgEnum.QUESTION_NOT_SETUP);
        }
        return Result.success(user.getQuestion());
    }

    @Override
    public Result checkAnswer(String username, String question, String answer) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(question) || StringUtils.isBlank(answer)) {
            return Result.error(CodeMsgEnum.PARAMETER_NOTEXIST);
        }
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username).eq(User::getQuestion,question).eq(User::getAnswer, answer));
        if (user == null) {
            return Result.error(CodeMsgEnum.ANSWER_ERROR); //问题答案有误
        }
        String forgetToken = redisService.get(UserKey.forgetPassword, username);
        if (StringUtils.isNotBlank(forgetToken)) {
            return Result.success(forgetToken);
        } else {
            forgetToken = UUID.randomUUID().toString().replaceAll("-", "");
            redisService.set(UserKey.forgetPassword, username, forgetToken);
            return Result.success(forgetToken);
        }
    }

    @Override
    @Transactional
    public Result forgetResetPasswd(String username, String passwordNew, String forgetToken) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(passwordNew) || StringUtils.isBlank(forgetToken)) {
            return Result.error(CodeMsgEnum.PARAMETER_NOTEXIST);
        }
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if (user == null) {
            return Result.error(CodeMsgEnum.USER_NOT_EXIST);
        }
        String token = redisService.get(UserKey.forgetPassword, username);
        if (StringUtils.isBlank(token) || !forgetToken.equals(token)) {
            return Result.error(CodeMsgEnum.FORGET_TOKEN_ERROR);
        }
        //不允许使用上次使用过的密码
        String encrypt = MD5Util.encrypt(passwordNew);
        if (encrypt.equals(user.getPassword())) {
            return Result.error(CodeMsgEnum.USE_REPEAT_PASSWORD);
        }
        user.setPassword(encrypt);
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateById(user);
        return Result.success();
    }

    @Override
    @Transactional
    public Result resetPasswd(String passwordOld, String passwordNew, Integer userId) {
        if (StringUtils.isBlank(passwordOld) || StringUtils.isBlank(passwordNew)) {
            return Result.error(CodeMsgEnum.PARAMETER_NOTEXIST);
        }
        User user = userMapper.selectById(userId);
        if (user == null) {
            return Result.error(CodeMsgEnum.SESSION_ERROR);
        }
        String encrypt = MD5Util.encrypt(passwordOld);
        if (!encrypt.equals(user.getPassword())) {
            return Result.error(CodeMsgEnum.PASSWORD_ERROR);
        }
        User modifyUser = new User();
        modifyUser.setId(userId);
        modifyUser.setPassword(MD5Util.encrypt(passwordNew));
        modifyUser.setUpdateTime(LocalDateTime.now());
        userMapper.updateById(modifyUser);
        return Result.success();
    }

    @Override
    @Transactional
    public void updateInfomation(String email, String phone, String question, String answer, Integer userId) {
        List<User> users = userMapper.selectList(new LambdaQueryWrapper<User>().eq(User::getEmail, email).notIn(User::getId, userId));
        if (users.size() > 0) {
            throw new UserException(CodeMsgEnum.EMAIL_EXIST);
        }

        User updateUser = new User();
        updateUser.setId(userId);
        updateUser.setEmail(email);
        updateUser.setPhone(phone);
        updateUser.setQuestion(question);
        updateUser.setAnswer(answer);
        userMapper.updateById(updateUser);
    }
}
