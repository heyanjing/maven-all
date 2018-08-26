package com.he.maven.core.time;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

/**
 * Created by heyanjing on 2017/12/19 16:10.
 */
public class LocalDateConverter implements Converter<String, LocalDate> {
    @Override
    public LocalDate convert(String s) {
        return LocalDate.parse(s, java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
