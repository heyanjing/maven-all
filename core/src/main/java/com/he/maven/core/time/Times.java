package com.he.maven.core.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by heyanjing on 2018/8/23 9:09.
 */
public class Times {

    private static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    public static String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME));
    }

    public static LocalDateTime parseDate2LocalDateTime(Date date) {
        Instant instant1 = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant1, zoneId);
    }

    public static LocalDate parseDate2LocalDate(Date date) {
        return parseDate2LocalDateTime(date).toLocalDate();
    }

    public static LocalTime parseDate2LocalTime(Date date) {
        return parseDate2LocalDateTime(date).toLocalTime();
    }

    public static Date parseInstant2Date(Instant instant) {
        return new Date(instant.toEpochMilli());
    }

    public static Date parseLocalDate2Date(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    public static Date parseLocalDateTime2Date(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    public static String format(Date date) {
        LocalDateTime localDateTime = parseDate2LocalDateTime(date);
        return localDateTime.format(DateTimeFormatter.ofPattern(DATE_TIME));
    }

    public static String format(Date date, String pattern) {
        LocalDateTime localDateTime = parseDate2LocalDateTime(date);
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }
}
