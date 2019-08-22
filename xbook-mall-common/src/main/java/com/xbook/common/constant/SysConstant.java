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

    /** 时间格式(yyyy-MM-dd) */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /** 时间格式(yyyy-MM-dd HH:mm:ss) */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";


    public final static String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
    public final static String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";
    //dubbo版本
    public static final String XBOOK_MALL_REDIS_VERSION = "1.0.0";
    public static final String XBOOK_MALL_USER_VERSION = "1.0.0";
    public static final String XBOOK_MALL_PRODUCT_VERSION = "1.0.0";
    public static final String XBOOK_MALL_CART_VERSION = "1.0.0";
    public static final String XBOOK_MALL_ORDER_VERSION = "1.0.0";
    public static final String XBOOK_MALL_PAYMENT_VERSION = "1.0.0";
}
