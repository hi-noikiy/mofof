package com.mofof.dto;

/**
 * Created by ChenErliang on 2017/6/18.
 */
public class SelectOption {

    private String name;
    private Long id;

    public SelectOption(String value, String name) {
        this.id = Long.parseLong(value);
        this.name = name;
    }

    public SelectOption(int id, String name) {
        this.id = (long)id;
        this.name = name;
    }

    public SelectOption(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public SelectOption(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getValue() {
        return id;
    }

    public void setValue(Long value) {
        this.id = value;
    }
}
