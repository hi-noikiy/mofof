package com.mofof.entity.administrator;

import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by ChenErliang on 17/6/9.
 * 管理团队(本质上这个实体是个管理关系)
 */
@Entity
@SQLDelete(sql = "update administrator_team set del = 1 where id = ?")
@SQLDeleteAll(sql = "update administrator_team set del = 1 where id = ?")
@Where(clause = "del = 0")
public class AdministratorTeam extends BaseEntity {
    @ManyToOne
    private Administrator administrator; //管理人
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private TeamMember teamMember; //团队成员
    private String jobStatus; //在职状态 字典
    private String position; //职务 字典
    private String positionDesc; //职责描述

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public TeamMember getTeamMember() {
        return teamMember;
    }

    public void setTeamMember(TeamMember teamMember) {
        this.teamMember = teamMember;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPositionDesc() {
        return positionDesc;
    }

    public void setPositionDesc(String positionDesc) {
        this.positionDesc = positionDesc;
    }
}
