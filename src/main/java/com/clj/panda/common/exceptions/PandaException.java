package com.clj.panda.common.exceptions;

import com.clj.panda.common.enums.PandaCode;

/**
 * Created by wana on 2015/10/12.
 */
public class PandaException extends RuntimeException{
    protected PandaCode code;

    public PandaException(PandaCode code) {
        this.code = code;
    }

    public PandaException(Throwable cause, PandaCode code) {
        super(cause);
        this.code = code;
    }

    public PandaException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        if(code != null){
            return code.getMsg();
        }else{
            return null;
        }
    }

    public PandaCode getErrorCode() {
        return this.code;
    }
}
