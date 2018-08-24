package com.he.maven.core.time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by heyanjing on 2018/8/23 9:09.
 */
public class Times {

    public static String now(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
