package com.he.maven;

import com.he.maven.core.config.Configuration2;

/**
 * @author heyanjing
 * @date 2018/8/25 10:31
 */
public interface Constant {

    boolean APP_DEBUG = Configuration2.getBoolean("app.debug", true);
    String USER_SESSION_KEY = Configuration2.getString("user_session_key", "user_session_key");
    int USER_ALLOW_NUM = Configuration2.getInteger("user.allow.num", 1);
    boolean USER_KICKOUT_BEFORE = Configuration2.getBoolean("user.kickout.before", true);
}
