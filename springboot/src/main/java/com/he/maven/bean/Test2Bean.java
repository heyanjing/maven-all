package com.he.maven.bean;

/**
 * Created by heyanjing on 2018/9/1 23:09.
 */
public class Test2Bean {
    private String name;
    private Integer age;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Test2Bean(String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public Test2Bean() {
    }
}
