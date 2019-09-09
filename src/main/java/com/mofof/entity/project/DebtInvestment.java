package com.mofof.entity.project;


import com.mofof.entity.affiliate.Affiliate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by hzh on 17/7/4.
 * 债权投资
 */
@Entity
@SQLDelete(sql = "update debt_investment set del = 1 where id = ?")
@SQLDeleteAll(sql = "update debt_investment set del = 1 where id = ?")
@Where(clause = "del = 0")
public class DebtInvestment extends InvestmentBehavior {

    private BigDecimal investAmount; // 投资金额
    private BigDecimal financingAmount; // 本轮融资金额
    private LocalDate nodeDate; //时间节点
    private String obligor; //义务人
    private float rates; //利率

    private boolean convertibleDebt;//可转债
    private BigDecimal postMoney; //投后估值
    private float investRatio; //投资比例

    private boolean indirect; // 间接
    @ManyToOne
    private Affiliate affiliate; // 关联机构

    @ElementCollection
    private List<InvestmentBehaviorDetail> investmentBehaviorDetails; //投资明细


    public BigDecimal getInvestAmount() {
        return investAmount;
    }

    public void setInvestAmount(BigDecimal investAmount) {
        this.investAmount = investAmount;
    }

    public BigDecimal getFinancingAmount() {
        return financingAmount;
    }

    public void setFinancingAmount(BigDecimal financingAmount) {
        this.financingAmount = financingAmount;
    }

    public LocalDate getNodeDate() {
        return nodeDate;
    }

    public void setNodeDate(LocalDate nodeDate) {
        this.nodeDate = nodeDate;
    }

    public String getObligor() {
        return obligor;
    }

    public void setObligor(String obligor) {
        this.obligor = obligor;
    }

    public float getRates() {
        return rates;
    }

    public void setRates(float rates) {
        this.rates = rates;
    }

    public boolean isConvertibleDebt() {
        return convertibleDebt;
    }

    public void setConvertibleDebt(boolean convertibleDebt) {
        this.convertibleDebt = convertibleDebt;
    }

    public BigDecimal getPostMoney() {
        return postMoney;
    }

    public void setPostMoney(BigDecimal postMoney) {
        this.postMoney = postMoney;
    }

    public float getInvestRatio() {
        return investRatio;
    }

    public void setInvestRatio(float investRatio) {
        this.investRatio = investRatio;
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

    public List<InvestmentBehaviorDetail> getInvestmentBehaviorDetails() {
        return investmentBehaviorDetails;
    }

    public void setInvestmentBehaviorDetails(List<InvestmentBehaviorDetail> investmentBehaviorDetails) {
        this.investmentBehaviorDetails = investmentBehaviorDetails;
    }
}
