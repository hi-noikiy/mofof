package com.mofof.entity.relation;

import com.mofof.entity.common.BaseEntity;
import com.mofof.entity.fund.Fund;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by ChenErliang on 2017/6/8.
 * 投资人变更
 */
@Entity
@SQLDelete(sql = "update investor_change set del = 1 where id = ?")
@SQLDeleteAll(sql = "update investor_change set del = 1 where id = ?")
@Where(clause = "del = 0")
public class InvestorChange extends BaseEntity {
    @ManyToOne
    private Fund fund; //相关基金
    private LocalDate changeDate;//变更日期
    //    private LocalDate lastUpdateDate; //最后更新日期 (工商数据的最后更新日期，从工商接口读取)
    @Lob
    private String note;//变更说明
    @ElementCollection
    private List<InvestorChangeDetail> investorChangeDetails; //详细变更明细（变更后的所有投资人信息）

    @ElementCollection
    private List<InvestorChangeProcess> investorChangeProcesses; //详细变更过程（变更内容）

    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

    public LocalDate getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDate changeDate) {
        this.changeDate = changeDate;
    }


    public List<InvestorChangeDetail> getInvestorChangeDetails() {
        return investorChangeDetails;
    }

    public void setInvestorChangeDetails(List<InvestorChangeDetail> investorChangeDetails) {
        this.investorChangeDetails = investorChangeDetails;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<InvestorChangeProcess> getInvestorChangeProcesses() {
        return investorChangeProcesses;
    }

    public void setInvestorChangeProcesses(List<InvestorChangeProcess> investorChangeProcesses) {
        this.investorChangeProcesses = investorChangeProcesses;
    }

//    public LocalDate getLastUpdateDate() {
//        return lastUpdateDate;
//    }
//
//    public void setLastUpdateDate(LocalDate lastUpdateDate) {
//        this.lastUpdateDate = lastUpdateDate;
//    }
}
