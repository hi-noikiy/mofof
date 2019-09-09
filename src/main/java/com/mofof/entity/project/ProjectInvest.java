package com.mofof.entity.project;

import com.mofof.entity.common.BaseEntity;
import com.mofof.entity.fund.Fund;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by ChenErliang on 17/6/9.
 * 基金对项目的投资关系
 */
@Entity
@SQLDelete(sql = "update project_invest set del = 1 where id = ?")
@SQLDeleteAll(sql = "update project_invest set del = 1 where id = ?")
@Where(clause = "del = 0")
public class ProjectInvest extends BaseEntity {

    @ManyToOne
    private Project project; //相关项目
    @ManyToOne
    private Fund fund; //相关基金
    private int investStatus; //0-投资中 2-已退出 1-未投资
    private BigDecimal investAmount;//投资金额
    private LocalDate investDate;//投资日期
    @Lob
    @Column(columnDefinition = "text")
    private String note;//备注

    public static int INVEST_STATUS_INVESTING = 0;
    public static int INVEST_STATUS_BEFORE = 1;
    public static int INVEST_STATUS_QUIT = 2;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

    public int getInvestStatus() {
        return investStatus;
    }

    public void setInvestStatus(int investStatus) {
        this.investStatus = investStatus;
    }

    public BigDecimal getInvestAmount() {
        return investAmount;
    }

    public void setInvestAmount(BigDecimal investAmount) {
        this.investAmount = investAmount;
    }

    public LocalDate getInvestDate() {
        return investDate;
    }

    public void setInvestDate(LocalDate investDate) {
        this.investDate = investDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
