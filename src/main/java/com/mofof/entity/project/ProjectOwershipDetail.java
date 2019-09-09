package com.mofof.entity.project;

import com.mofof.entity.fund.Fund;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

/**
 * Created by ChenErliang on 17/6/9.
 * 项目股权明细
 */
@Embeddable
public class ProjectOwershipDetail {

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Fund fund; //投资基金
    private BigDecimal investAmount; //投资金额
    private String note; //备注

    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

    public BigDecimal getInvestAmount() {
        return investAmount;
    }

    public void setInvestAmount(BigDecimal investAmount) {
        this.investAmount = investAmount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
