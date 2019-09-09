package com.mofof.entity.common;

import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 基类
 * Created by ChenErliang on 17/4/12.
 */

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String creator;
    private LocalDateTime createTime;

    @Column(length = 1, nullable = false, columnDefinition = "tinyint default 0")
    private int del = 0;

    public BaseEntity() {
        createTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }
}
