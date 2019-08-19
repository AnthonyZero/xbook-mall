package com.xbook.user.service;


import com.xbook.common.core.Result;
import com.xbook.entity.user.User;

public interface UserService {

    /**
     * 用户注册
     * @param user
     */
    void register(User user);

    /**
     * 校验参数
     * @param str
     * @param type
     */
    void checkValid(String str, String type);

    /**
     * 用户登录 成功返回token
     * @param username
     * @param password
     * @return
     */
    Result login(String username, String password);


    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    User getUserInfo(Integer userId);

    /**
     * 通过用户名获取设置的问题
     * @param username
     * @return
     */
    Result getQuestionByUsername(String username);

    /**
     * 校验问题对应的答案是否正确
     * @param username
     * @param question
     * @param answer
     * @return
     */
    Result checkAnswer(String username, String question, String answer);
}
