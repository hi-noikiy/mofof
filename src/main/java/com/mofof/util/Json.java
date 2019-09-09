package com.mofof.util;

import java.util.HashMap;


public class Json extends HashMap<String, Object> {

    public static final String KEY_OK = "ok";
    public static final String KEY_CODE = "code";
    public static final String KEY_MSG = "msg";
    public static final String KEY_DATA = "data";

    public static final int DEFAULT_OK_CODE = 200;
    public static final int DEFAULT_ERR_CODE = -1;
    public static final String DEFAULT_OK_MSG = "ok";
    public static final String DEFAULT_ERR_MSG = "err";

    public Json() {
        this.put(KEY_OK, true);
        this.put(KEY_CODE, DEFAULT_OK_CODE);
        this.put(KEY_MSG,  DEFAULT_OK_MSG);
    }

    /**
     * @param ok
     * @param code
     * @param msg
     * @param data
     */
    public Json(boolean success, int code, String msg, Object data) {
        this.put(KEY_OK, success);
        this.put(KEY_CODE, code);
        this.put(KEY_MSG, msg);
        if (data != null){
            this.put(KEY_DATA, data);
        }
    }

    public static Json ok() {
        return new Json();
    }

    public static Json ok(String message) {
        return new Json(true, DEFAULT_OK_CODE, message, null);
    }

    public static Json ok(Object data) {
        return new Json(true, DEFAULT_OK_CODE, DEFAULT_OK_MSG, data);
    }

    public static Json ok(String dataKey, Object dataVal) {
        return new Json(true, DEFAULT_OK_CODE, DEFAULT_OK_MSG, null).data(dataKey,dataVal);
    }

    public static Json error() {
        return new Json(false, DEFAULT_ERR_CODE, DEFAULT_ERR_MSG, null);
    }

    public static Json error(String message) {
        return new Json(false, DEFAULT_ERR_CODE, message, null);
    }

    public static Json error(Object data) {
        return new Json(false, DEFAULT_ERR_CODE, DEFAULT_ERR_MSG, data);
    }

    public static Json error(String dataKey, Object dataVal) {
        return new Json(false, DEFAULT_ERR_CODE, DEFAULT_ERR_MSG,null).data(dataKey,dataVal);
    }


    public Json data(Object dataVal){
        this.put(KEY_DATA,dataVal);
        return this;
    }

    public Json data(String dataKey, Object dataVal){
        this.put(dataKey,dataVal);
        return this;
    }

}