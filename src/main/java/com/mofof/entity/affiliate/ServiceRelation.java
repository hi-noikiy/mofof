package com.mofof.entity.affiliate;

import com.mofof.entity.administrator.TeamMember;
import com.mofof.entity.common.BaseEntity;
import com.mofof.entity.fund.Fund;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by ChenErliang on 17/6/9.
 * 外服关系
 */
@Entity
@SQLDelete(sql = "update service_relation set del = 1 where id = ?")
@SQLDeleteAll(sql = "update service_relation set del = 1 where id = ?")
@Where(clause = "del = 0")
public class ServiceRelation extends BaseEntity {

    @ManyToOne
    private Fund fund; //相关基金
    @ManyToOne
    private ServiceAgency serviceAgency; //相关外服机构
    private LocalDate serviceStartDate;//服务开始日期
    private LocalDate serviceEndDate; //服务结束日期
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<TeamMember> teamMember; //相关人员

    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

    public ServiceAgency getServiceAgency() {
        return serviceAgency;
    }

    public void setServiceAgency(ServiceAgency serviceAgency) {
        this.serviceAgency = serviceAgency;
    }

    public LocalDate getServiceStartDate() {
        return serviceStartDate;
    }

    public void setServiceStartDate(LocalDate serviceStartDate) {
        this.serviceStartDate = serviceStartDate;
    }

    public LocalDate getServiceEndDate() {
        return serviceEndDate;
    }

    public void setServiceEndDate(LocalDate serviceEndDate) {
        this.serviceEndDate = serviceEndDate;
    }

    public List<TeamMember> getTeamMember() {
        return teamMember;
    }

    public void setTeamMember(List<TeamMember> teamMember) {
        this.teamMember = teamMember;
    }
}
