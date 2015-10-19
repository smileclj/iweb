package com.clj.panda.model.entity.test;

import java.io.Serializable;

/**
 * Created by lao on 2015/9/28.
 */
public class TestStudent implements Serializable{
    private static final long serialVersionUID = -6690808235323588635L;

    private String id;
    private String name;
    private int age;
    private String remark;
    private long creationTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }
}
