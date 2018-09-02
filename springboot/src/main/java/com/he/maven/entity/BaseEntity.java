package com.he.maven.entity;

import com.he.maven.core.bean.BaseBean;

import java.io.Serializable;

/**
 * Created by heyanjing on 2018/8/20 17:58.
 */
public abstract class BaseEntity<ID extends Serializable> extends BaseBean {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public abstract ID getId();

    public abstract void setId(ID id);
}
