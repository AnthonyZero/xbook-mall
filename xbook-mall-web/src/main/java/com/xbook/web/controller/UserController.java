package com.xbook.web.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.xbook.common.constant.SysConstant;
import com.xbook.common.core.Result;
import com.xbook.common.redis.key.UserKey;
import com.xbook.common.utils.CookieUtil;
import com.xbook.entity.user.User;
import com.xbook.redis.service.RedisService;
import com.xbook.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/user")
@RestController
@Slf4j
public class UserController extends BaseController{

    @Reference(version = SysConstant.XBOOK_MALL_USER_VERSION)
    private UserService userService;
    @Reference(version = SysConstant.XBOOK_MALL_REDIS_VERSION)
    private RedisService redisService;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping("/register")
    public Result register(User user) {
        userService.register(user);
        return Result.success();
    }

    /**
     * 验证用户参数
     * @param str
     * @param type
     * @return
     */
    @PostMapping("/checkValid")
    public Result checkValid(String str, String type) {
        userService.checkValid(str, type);
        return Result.success();
    }

    /**
     * 用户登录
     * @param request
     * @param response
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public Result login(HttpServletRequest request, HttpServletResponse response, String username, String password) {
        Result result = userService.login(username, password);
        if (result.isSuccess()) {
            //token值写入cookie中
            CookieUtil.setCookie(request, response, SysConstant.LOGIN_TOKEN, result.getData().toString());
        }
        return Result.success();
    }


    /**
     * 获取用户信息
     * @param request
     * @return
     */
    @RequestMapping("/getUserInfo")
    public Result getUserInfo(HttpServletRequest request) {
        Integer currentUserId = getCurrentUserId(request);
        User userInfo = userService.getUserInfo(currentUserId);
        userInfo.setPassword("");
        return Result.success(userInfo);
    }


    /**
     * 根据用户名去拿到对应的问题
     * @param username
     * @return
     */
    @RequestMapping("/forgetGetQuestion")
    public Result forgetGetQuestion(String username) {
        return userService.getQuestionByUsername(username);
    }


    /**
     * 校验答案是否正确 并返回修改令牌token
     * @param username
     * @param question
     * @param answer
     * @return
     */
    @RequestMapping("/forgetCheckAnswer")
    public Result forgetCheckAnswer(String username,String question,String answer) {
        return userService.checkAnswer(username, question, answer);
    }



    /**
     * 退出
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/logout")
    public Result logout(HttpServletRequest request, HttpServletResponse response) {
        String loginToken = CookieUtil.getCookieValue(request, SysConstant.LOGIN_TOKEN);
        //删除cookie
        CookieUtil.deleteCookie(request, response, SysConstant.LOGIN_TOKEN);
        //删除缓存
        redisService.delete(UserKey.loginUser, loginToken);
        return Result.success();
    }
}
