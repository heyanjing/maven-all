package com.he.maven.ssh.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;

/**
 * Created by heyanjing on 2018/8/20 17:54.
 */
@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
public class Person extends BaseEntityWithStringId {
    private String name;
    private Integer age;
    private String addr;
    private Integer showOrder;

}
