package com.xbook.common.redis.key;

import com.xbook.common.redis.AbstractPrefix;

public class UserKey extends AbstractPrefix {
    public UserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static UserKey loginUser = new UserKey(100, "session");
    public static UserKey forgetPassword = new UserKey(120, "forget_password");
}
