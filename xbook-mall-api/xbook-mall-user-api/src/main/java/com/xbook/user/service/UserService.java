package com.xbook.user.service;


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
}
