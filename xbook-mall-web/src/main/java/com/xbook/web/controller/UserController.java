package com.xbook.web.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.xbook.common.constant.SysConstant;
import com.xbook.common.core.Result;
import com.xbook.entity.user.User;
import com.xbook.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
@Slf4j
public class UserController {

    @Reference(version = SysConstant.XBOOK_MALL_USER_VERSION)
    private UserService userService;

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
}
