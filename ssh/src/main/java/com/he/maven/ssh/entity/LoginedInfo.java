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
 *
 * @author heyanjing
 * @date 2018/8/26 1:58
 */
@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
public class LoginedInfo extends BaseEntityWithStringId {
    private static final long serialVersionUID = 319220805003510993L;
    private String userId;
    private String sessionId;
    private String kickout;

}
