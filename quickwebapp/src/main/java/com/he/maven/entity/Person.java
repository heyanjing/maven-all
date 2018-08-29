package com.he.maven.entity;

import com.he.maven.hibernate.entity.BaseEntityWithStringId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;

/**
 * @author heyanjing
 * @date 2018/8/20 17:54
 */
@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
public class Person extends BaseEntityWithStringId {
    private static final long serialVersionUID = -5601627121689891218L;
    private String name;
    private Integer age;
    private String addr;
    private Integer showOrder;

}
