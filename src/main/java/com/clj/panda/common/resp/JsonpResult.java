package com.clj.panda.common.resp;

import java.io.Serializable;

/**
 * Created by wana on 2015/11/5.
 */
public class JsonpResult implements Serializable{
    private static final long serialVersionUID = 6892690531658601315L;

    private Result result;
    private String method;

    public JsonpResult(Result result, String method) {
        this.result = result;
        this.method = method;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
