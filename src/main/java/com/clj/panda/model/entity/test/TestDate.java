package com.clj.panda.model.entity.test;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wana on 2015/10/10.
 */
public class TestDate implements Serializable{
    private static final long serialVersionUID = 8907252593054576690L;

    private int id;
    private String timeOne;
    private long timeTwo;
    private Date timeThree;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimeOne() {
        return timeOne;
    }

    public void setTimeOne(String timeOne) {
        this.timeOne = timeOne;
    }

    public long getTimeTwo() {
        return timeTwo;
    }

    public void setTimeTwo(long timeTwo) {
        this.timeTwo = timeTwo;
    }

    public Date getTimeThree() {
        return timeThree;
    }

    public void setTimeThree(Date timeThree) {
        this.timeThree = timeThree;
    }
}
