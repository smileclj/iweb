package com.clj.panda.common.resp;

import com.clj.panda.common.enums.PandaCode;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by wana on 2015/10/13.
 */
public class Result implements Serializable{
    private static final long serialVersionUID = 7002643665860449661L;

    private int code; //返回码
    private long serverTime; //服务器时间
    private Object result; //返回内容

    public Result() {
        this.serverTime = System.currentTimeMillis();
        this.result = new HashMap<String, Object>();
    }

    public Result(PandaCode pandaCode) {
        super();
        this.code = pandaCode.getValue();
        this.result = new HashMap<String, Object>();
    }

    public Result(PandaCode pandaCode, Object result) {
        this.code = pandaCode.getValue();
        this.serverTime = System.currentTimeMillis();
        if (result != null) {
            this.result = result;
        } else {
            this.result = new HashMap<String, Object>();
        }

    }

    public int getCode() {
        return code;
    }

    public void setCode(PandaCode code) {
        this.code = code.getValue();
    }

    public long getServerTime() {
        return serverTime;
    }

    public void setServerTime(long serverTime) {
        this.serverTime = serverTime;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
