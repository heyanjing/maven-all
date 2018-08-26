package com.he.maven.core.time;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;

/**
 * Created by heyanjing on 2017/12/19 16:10.
 */
public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {
    @Override
    public LocalDateTime convert(String s) {
        return LocalDateTime.parse(s, java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
