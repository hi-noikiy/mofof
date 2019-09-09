package com.mofof.entity.administrator;

import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * 联系方式
 * Created by ChenErliang on 17/4/12.
 */
@Entity
@SQLDelete(sql = "update contact set del = 1 where id = ?")
@SQLDeleteAll(sql = "update contact set del = 1 where id = ?")
@Where(clause = "del = 0")
public class Contact extends BaseEntity {

    @OneToOne
    private TeamMember teamMember;

    private String mobile; //手机
    private String email; //邮件
    private String address; //地址

    public TeamMember getTeamMember() {
        return teamMember;
    }

    public void setTeamMember(TeamMember teamMember) {
        this.teamMember = teamMember;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
