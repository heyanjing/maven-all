package com.he.maven.ssh.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by heyanjing on 2018/8/22 11:20.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageObject<T> extends BaseBean {

    private long total;
    private List<T> data;
}
