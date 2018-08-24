package com.he.maven.core.Json;

import com.he.maven.core.bean.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * Created by heyanjing on 2018/8/24 22:47.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class A extends BaseBean {
    private Date date;
    private LocalDateTime localDateTime;
    private LocalDate localDate;
    private LocalTime localTime;
    private String name;
    private Double money;
    private Integer age;
    private Long second;
    private String temp;
}
