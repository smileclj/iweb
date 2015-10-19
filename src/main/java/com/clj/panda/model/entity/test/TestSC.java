package com.clj.panda.model.entity.test;

import java.io.Serializable;

/**
 * Created by wana on 2015/10/10.
 */
public class TestSC implements Serializable{
    private static final long serialVersionUID = -9028629049072637637L;

    private String id;
    private String sId;
    private String cId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }
}
