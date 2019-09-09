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
 * 股权投资
 */
@Entity
@SQLDelete(sql = "update equity_investment set del = 1 where id = ?")
@SQLDeleteAll(sql = "update equity_investment set del = 1 where id = ?")
@Where(clause = "del = 0")
public class EquityInvestment extends InvestmentBehavior {

    private String investTurn; //投资轮次
    private String role; //角色
    private String investForms; //投资形式
    private BigDecimal investAmount; //投资金额
    private Float investRatio;//股权比例
    private BigDecimal preMoney;//投前估值
    private BigDecimal postMoney; //投后估值
    private BigDecimal financingAmount; //本轮融资金额

    private boolean shareReform; //股改
    private BigDecimal equityAmount; //总股本
    private BigDecimal sharePrice;//每股价格
    private BigDecimal shareNumber; //股份数

    private boolean preferredEquity;//优先股
    private float dividendRatio; //股息率

    private boolean buyBack;//回购
    private LocalDate nodeDate; //时间节点
    private String obligor; // 义务人
    private float rates; // 利率
    private String buyBackTerm; // 回购条件

    private boolean gambling; // 对赌
    private String term; // 对赌条款

    private boolean indirect; // 间接
    @ManyToOne
    private Affiliate affiliate; // 关联机构

    @ElementCollection
    private List<InvestmentBehaviorDetail> investmentBehaviorDetails; //投资明细

    public String getInvestTurn() {
        return investTurn;
    }

    public void setInvestTurn(String investTurn) {
        this.investTurn = investTurn;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getInvestForms() {
        return investForms;
    }

    public void setInvestForms(String investForms) {
        this.investForms = investForms;
    }

    public BigDecimal getInvestAmount() {
        return investAmount;
    }

    public void setInvestAmount(BigDecimal investAmount) {
        this.investAmount = investAmount;
    }

    public Float getInvestRatio() {
        return investRatio;
    }

    public void setInvestRatio(Float investRatio) {
        this.investRatio = investRatio;
    }

    public BigDecimal getPreMoney() {
        return preMoney;
    }

    public void setPreMoney(BigDecimal preMoney) {
        this.preMoney = preMoney;
    }

    public BigDecimal getPostMoney() {
        return postMoney;
    }

    public void setPostMoney(BigDecimal postMoney) {
        this.postMoney = postMoney;
    }

    public BigDecimal getFinancingAmount() {
        return financingAmount;
    }

    public void setFinancingAmount(BigDecimal financingAmount) {
        this.financingAmount = financingAmount;
    }

    public boolean isShareReform() {
        return shareReform;
    }

    public void setShareReform(boolean shareReform) {
        this.shareReform = shareReform;
    }

    public BigDecimal getEquityAmount() {
        return equityAmount;
    }

    public void setEquityAmount(BigDecimal equityAmount) {
        this.equityAmount = equityAmount;
    }

    public BigDecimal getSharePrice() {
        return sharePrice;
    }

    public void setSharePrice(BigDecimal sharePrice) {
        this.sharePrice = sharePrice;
    }

    public BigDecimal getShareNumber() {
        return shareNumber;
    }

    public void setShareNumber(BigDecimal shareNumber) {
        this.shareNumber = shareNumber;
    }

    public boolean isPreferredEquity() {
        return preferredEquity;
    }

    public void setPreferredEquity(boolean preferredEquity) {
        this.preferredEquity = preferredEquity;
    }

    public float getDividendRatio() {
        return dividendRatio;
    }

    public void setDividendRatio(float dividendRatio) {
        this.dividendRatio = dividendRatio;
    }

    public boolean isBuyBack() {
        return buyBack;
    }

    public void setBuyBack(boolean buyBack) {
        this.buyBack = buyBack;
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

    public boolean isGambling() {
        return gambling;
    }

    public void setGambling(boolean gambling) {
        this.gambling = gambling;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
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

    public String getBuyBackTerm() {
        return buyBackTerm;
    }

    public void setBuyBackTerm(String buyBackTerm) {
        this.buyBackTerm = buyBackTerm;
    }
}
