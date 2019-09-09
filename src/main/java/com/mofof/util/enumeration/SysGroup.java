package com.mofof.util.enumeration;

/**
 * Created by hzh on 2019/4/15.
 */
public enum SysGroup {
    FUND("基金","fund"),
    PROJECT("项目","project"),
    PLATFORM("平台","platform");


    private String name;
    private String value;

    SysGroup(String name,String value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
