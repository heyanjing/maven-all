package com.he.maven.ssh.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by heyanjing on 2018/8/20 17:58.
 */
@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntityWithStringId extends BaseEntity<String> {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(unique = true)
    protected String id;
    protected Integer state;
}
