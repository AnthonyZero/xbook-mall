package com.xbook.common.constant;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * 系统常量
 */
public class SysConstant {

    /**cookie 用户登录token*/
    public static final String LOGIN_TOKEN = "_xbook";

    public static final int ROLE_CUSTOME = 0;//普通用户
    public static final int ROLE_ADMIN = 1;//管理员用户

    // 用户注册判断重复的参数类型
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";

    public static final String IMG_HOST = "https://i.loli.net/";
    public static final Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc","price_asc"); //商品排序选择

    //dubbo版本
    public static final String XBOOK_MALL_REDIS_VERSION = "1.0.0";
    public static final String XBOOK_MALL_USER_VERSION = "1.0.0";
    public static final String XBOOK_MALL_PRODUCT_VERSION = "1.0.0";
}
