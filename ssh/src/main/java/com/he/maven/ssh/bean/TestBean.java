package com.he.maven.ssh.bean;

import com.he.maven.core.bean.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by heyanjing on 2018/8/26 14:03.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestBean extends BaseBean {

    LocalDateTime ldt;
    LocalDate ld;
    Date d1;
    Date d2;
    String str;
}
