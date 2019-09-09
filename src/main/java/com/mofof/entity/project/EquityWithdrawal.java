package com.mofof.entity.project;

import com.mofof.entity.affiliate.Affiliate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

/**
 * Created by hzh on 17/7/4.
 * 股权退出
 */
@Entity
@SQLDelete(sql = "update equity_withdrawal set del = 1 where id = ?")
@SQLDeleteAll(sql = "update equity_withdrawal set del = 1 where id = ?")
@Where(clause = "del = 0")
public class EquityWithdrawal extends InvestmentBehavior {

    private String withdrawalForms; //退出形式
    private BigDecimal withdrawalAmount;//退出金额
    private BigDecimal correspondingCost;//对应成本
    private float correspondingRatio;//对应比例
    private float profitTimes;//收益倍数
    private float profitRatio;//内部收益率
    private String note;//备注

    private boolean indirect;//间接
    @ManyToOne
    private Affiliate affiliate;//关联机构

    public String getWithdrawalForms() {
        return withdrawalForms;
    }

    public void setWithdrawalForms(String withdrawalForms) {
        this.withdrawalForms = withdrawalForms;
    }

    public BigDecimal getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(BigDecimal withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    public BigDecimal getCorrespondingCost() {
        return correspondingCost;
    }

    public void setCorrespondingCost(BigDecimal correspondingCost) {
        this.correspondingCost = correspondingCost;
    }

    public float getCorrespondingRatio() {
        return correspondingRatio;
    }

    public void setCorrespondingRatio(float correspondingRatio) {
        this.correspondingRatio = correspondingRatio;
    }

    public float getProfitTimes() {
        return profitTimes;
    }

    public void setProfitTimes(float profitTimes) {
        this.profitTimes = profitTimes;
    }

    public float getProfitRatio() {
        return profitRatio;
    }

    public void setProfitRatio(float profitRatio) {
        this.profitRatio = profitRatio;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isIndirect() {
        return indirect;
    }

    public void setIndirect(boolean indirect) {
        this.indirect = indirect;
    }

    public Affiliate getAffiliate() {
        return affiliate;
    }

    public void setAffiliate(Affiliate affiliate) {
        this.affiliate = affiliate;
    }
}
