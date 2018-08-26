package com.he.maven.core.time;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by heyanjing on 2017/12/19 16:10.
 * springmvc 参数封装成对象需要的格式化
 */
public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        DateFormat dateFormat;
        if (s.length() == 19) {
            dateFormat = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT);
        } else {
            dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT);
        }
        try {
            return dateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
