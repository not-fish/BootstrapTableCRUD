package com.example.demo3.common;

/**
 * @author Peko.Lai
 */

public enum ReturnMsgEnum {
    /**
     * 用户id已存在
     */
    FAIL_DATA_EXIST("CODE5001", "该id用户已经存在"),
    /**
     * 添加用户成功
     */
    ADD_DATA_SUCCESS("CODE6000", "添加用户成功");

    private final String value;
    private final String name;

    ReturnMsgEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
