package com.mofof.entity.relation;

import com.mofof.entity.investor.Investor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

/**
 * Created by hzh on 17/7/13.
 */
@Embeddable
public class InvestorChangeProcess{

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Investor investor; //投资人
    private String changeType;//变更类型 //新增 退出
    private BigDecimal investAmount; //投资金额
    private String note; //备注

    public Investor getInvestor() {
        return investor;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
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
