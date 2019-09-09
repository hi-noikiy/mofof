package com.mofof.entity.relation;

import com.mofof.entity.common.BaseEntity;
import com.mofof.entity.system.User;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * 人员分工/外派
 * Created by ChenErliang on 2017/4/13.
 */
@Entity
@SQLDelete(sql = "update response_person set del = 1 where id = ?")
@SQLDeleteAll(sql = "update response_person set del = 1 where id = ?")
@Where(clause = "del = 0")
public class ResponsePerson extends BaseEntity {

    @ManyToOne
    private InvestRelation investRelation;//相关投资关系
    @ManyToOne
    private User user; //系统用户
    private int responseType; //0-分工 1-外派
    private String role; //角色 字典选
    private String note; // 备注 //冗余

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public InvestRelation getInvestRelation() {
        return investRelation;
    }

    public void setInvestRelation(InvestRelation investRelation) {
        this.investRelation = investRelation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getResponseType() {
        return responseType;
    }

    public void setResponseType(int responseType) {
        this.responseType = responseType;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
