package com.he.maven.core.reg;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author heyanjing
 * @date 2018/8/29 10:21
 */
@Slf4j
public class RegTest {

    @Test
    public void test() {
        String phone = "18423452585";
        boolean matches = Pattern.matches(Reg.phone, phone);
        log.info("{}", matches);
        phone = "18423452585,13068308753";

        Pattern p = Pattern.compile(Reg.phone);
        Matcher m = p.matcher(phone);
        while (m.find()) {
            log.info("{}", m.group());
        }
    }
}