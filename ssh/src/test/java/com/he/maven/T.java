package com.he.maven;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by heyanjing on 2018/8/21 12:50.
 */@Slf4j
public class T {

    @Test
    public void test() {
        log.debug("{}","aa");
        log.info("{}","aa");
        log.warn("{}","aa");
        log.error("{}","aa");
    }
}
