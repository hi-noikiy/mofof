package com.mofof.entity.project;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.mofof.entity.common.BaseEntity;
import com.mofof.entity.system.User;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

/**
 * 项目－人员分工/外派
 * Created by ChenErliang on 2017/4/13.
 */
@Entity
@SQLDelete(sql = "update project_response_person set del = 1 where id = ?")
@SQLDeleteAll(sql = "update project_response_person set del = 1 where id = ?")
@Where(clause = "del = 0")
public class ProjectResponsePerson extends BaseEntity {

    @ManyToOne
    private ProjectInvest projectInvest;//相关投资关系
    @ManyToOne
    private User user; //基金的管理团队
    private int responseType; //0-分工 1-外派
    private String role; //角色 字典选择
    private String note; // 备注 //冗余

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ProjectInvest getProjectInvest() {
        return projectInvest;
    }

    public void setProjectInvest(ProjectInvest projectInvest) {
        this.projectInvest = projectInvest;
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
