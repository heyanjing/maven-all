package com.he.maven.core.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by heyanjing on 2018/8/23 9:09.
 */
public class Times {

    private static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE = "yyyy-MM-dd";
    private static final String VERSION_DATE_TIME = "yyyyMMddHHmmss";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE);
    private static final DateTimeFormatter VERSION_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(VERSION_DATE_TIME);

    public static String nowDateTime() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER);
    }

    public static String nowDate() {
        return LocalDateTime.now().format(DATE_FORMATTER);
    }

    public static String nowVersion() {
        return LocalDateTime.now().format(VERSION_DATE_TIME_FORMATTER);
    }

    public static String parseMillisecond2LocalDateTime(long millisecond) {
        Instant instant = Instant.ofEpochSecond(millisecond / 1000);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.of("+8"));
        return localDateTime.format(DATE_TIME_FORMATTER);
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
