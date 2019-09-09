package com.mofof.util.enumeration;

/**
 * 自定义返回码
 * @author hzh
 * @date 2019-06-07
 */
public enum ResultCode {
    SUCCESS("操作成功","0"),
    UNKOWN("未知异常", "50"),
    ERROR("系统错误", "51");

    private String name;
    private String value;
    private String description = "服务器自定义返回状态码";

    ResultCode(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public int getIntegerValue(){
        return  Integer.valueOf(value);
    }

    public String getDescription() {
        return description;
    }
}
