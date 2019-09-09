package com.mofof.util;

/**
 *
 * @param <T>
 */
public class Result<T> {
    /**状态码*/
    private String code;
    /**提示信息*/
    private String msg;
    /**数据对象*/
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
