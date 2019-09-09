package com.mofof.entity.administrator;

import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by ChenErliang on 17/6/9.
 * 职业背景
 */
@Entity
@SQLDelete(sql = "update professional set del = 1 where id = ?")
@SQLDeleteAll(sql = "update professional set del = 1 where id = ?")
@Where(clause = "del = 0")
public class Professional extends BaseEntity {

    @ManyToOne
    private TeamMember teamMember;//相关团队成员
    private String startTime; //开始时间
    private String endTime; //结束时间
    private String agencyName; //服务机构
    private String position;  //职位
    private String positionDesc; //职能描述

    public TeamMember getTeamMember() {
        return teamMember;
    }

    public void setTeamMember(TeamMember teamMember) {
        this.teamMember = teamMember;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
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
