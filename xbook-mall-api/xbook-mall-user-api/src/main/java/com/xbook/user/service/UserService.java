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

    /**
     * 忘记密码-》重置密码
     * @param username
     * @param passwordNew
     * @param forgetToken
     * @return
     */
    Result forgetResetPasswd(String username, String passwordNew, String forgetToken);

    /**
     * 修改密码
     * @param passwordOld
     * @param passwordNew
     * @param userId
     * @return
     */
    Result resetPasswd(String passwordOld, String passwordNew, Integer userId);

    /**
     * 修改用户信息
     * @param email
     * @param phone
     * @param question
     * @param answer
     * @param userId
     * @return
     */
    void updateInfomation(String email, String phone, String question, String answer, Integer userId);
}
