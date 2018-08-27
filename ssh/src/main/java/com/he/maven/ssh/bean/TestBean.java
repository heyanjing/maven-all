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
 * @author heyanjing
 * @date 2018/8/26 14:03
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestBean extends BaseBean {

    private static final long serialVersionUID = -3024350689618646889L;
    LocalDateTime ldt;
    LocalDate ld;
    Date d1;
    Date d2;
    String str;
}
