package com.mofof.entity.project;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

/**
 * Created by hzh on 17/7/4.
 * 投资明细 - 投资人投资金额等
 */
@Embeddable
public class InvestmentBehaviorDetail {

    private String investorName; //投资人
    private BigDecimal investAmount;//投资金额
    private String investForms;//投资形式
    private float investRatio;//股权比例
    private String role;//角色
    private String note;//备注

    public String getInvestorName() {
        return investorName;
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    public BigDecimal getInvestAmount() {
        return investAmount;
    }

    public void setInvestAmount(BigDecimal investAmount) {
        this.investAmount = investAmount;
    }

    public String getInvestForms() {
        return investForms;
    }

    public void setInvestForms(String investForms) {
        this.investForms = investForms;
    }

    public float getInvestRatio() {
        return investRatio;
    }

    public void setInvestRatio(float investRatio) {
        this.investRatio = investRatio;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
