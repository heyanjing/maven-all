package com.he.maven.core;

import com.he.maven.core.Json.Jsons;
import com.he.maven.core.bean.Result;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author heyanjing
 * @date 2018/8/29 10:21
 */
@Slf4j
public class CommonTest {

    @Test
    public void test() {
        Result result = new Result();
        result.setSuccess(false);
        log.info("{}", result);
        log.info("{}", Jsons.toJson(result));
        Result result1 = Result.failureWithError(101, null, "错了");
        log.info("{}", Jsons.toJson(result1));
    }
}