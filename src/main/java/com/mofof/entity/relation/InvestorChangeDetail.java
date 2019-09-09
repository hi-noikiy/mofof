package com.mofof.entity.relation;

import com.mofof.entity.investor.Investor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

/**
 * Created by ChenErliang on 2017/6/8.
 * 投资人变更明细
 */
@Embeddable
public class InvestorChangeDetail {

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Investor investor; //投资人
    private BigDecimal investAmount; //投资金额
    private String note; //备注

    public Investor getInvestor() {
        return investor;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
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
