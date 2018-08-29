package com.he.maven.bean;

import com.he.maven.core.bean.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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
    @NotBlank(message = "名称不能为空") String name;
    @Range(min = 0, max = 120, message = "无效年龄") String age;

    /**
     * 升高不低于120cm的验证只对小孩生效
     */
    @Min(value = 120, message = "身高不能低于120cm", groups = {Child.class}) String height;
    String addr;
    @Email(message = "邮箱地址无效") String email;
    @Pattern(regexp = "", message = "电话号码无效") String phone;

    public TestBean(LocalDateTime ldt, LocalDate ld, Date d1, Date d2) {
        this.ldt = ldt;
        this.ld = ld;
        this.d1 = d1;
        this.d2 = d2;
    }

    /**
     * 小孩
     */
    public interface Child {
    }

    /**
     * 成年人
     */
    public interface Adult {
    }
}
