package com.he.maven.ssh.entity;

import com.he.maven.hibernate.entity.BaseEntityWithStringId;
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
public class Product extends BaseEntityWithStringId {
    private String personId;
    private String name;
    private Double price;
    private Long quantity;
    private Integer showOrder;

}
