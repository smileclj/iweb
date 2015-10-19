package com.clj.panda.common.enums;

/**
 * Created by wana on 2015/10/13.
 */
public enum PandaCode {
    SUCCESS(1, "成功"),
    ERROR_PARAM(2, "参数错误"),
    ERROR_REPEAT(3, "重复操作"),
    ACTION_NOT_EXIST(4,"接口不存在"),
    FILE_NOT_EXIST(5,"文件不存在"),
    UNKNOW(0, "未知错误");

    private PandaCode(int value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    /**
     * code码
     */
    private int value;
    /**
     * code信息
     */
    private String msg;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 根据code码查找
     */
    public static PandaCode valueOf(int value) {
        PandaCode result = UNKNOW;
        for (PandaCode p : PandaCode.values()) {
            if (p.getValue() == value) {
                result = p;
            }
        }
        return result;
    }

    /**
     * 根据name查找
     */
    public static PandaCode fromName(String name) {
        PandaCode result = UNKNOW;
        for (PandaCode p : PandaCode.values()) {
            if (p.getMsg().equals(name)) {
                result = p;
            }
        }
        return result;
    }
}
