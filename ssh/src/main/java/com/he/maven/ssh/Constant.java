package com.he.maven.ssh;

import com.he.maven.core.config.Configuration2;

/**
 * Created by heyanjing on 2018/8/25 10:31.
 */
public interface Constant {

    String USER_SESSION = "user_session";

    int USER_ALLOW_NUM = Configuration2.getInteger("user.allow.num", 1);
    boolean USER_KICKOUT_BEFORE = Configuration2.getBoolean("user.kickout.before", true);
}
