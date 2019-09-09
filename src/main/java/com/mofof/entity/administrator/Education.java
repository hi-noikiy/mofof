package com.mofof.entity.administrator;

import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by ChenErliang on 17/6/9
 * 教育背景
 */
@Entity
@SQLDelete(sql = "update education set del = 1 where id = ?")
@SQLDeleteAll(sql = "update education set del = 1 where id = ?")
@Where(clause = "del = 0")
public class Education extends BaseEntity {

    @ManyToOne
    private TeamMember teamMember; //相关团队成员
    private String startTime; //开始时间
    private String endTime; //结束时间
    private String school; //学校名称
    private String major;  //专业名称
    private String degree; //学位学历

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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}
