package com.mofof.entity.project;

import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

/**
 * Created by hzh on 2017/10/12.
 */
@Entity
@SQLDelete(sql = "update investment_behavior_info set del = 1 where id = ?")
@SQLDeleteAll(sql = "update investment_behavior_info set del = 1 where id = ?")
@Where(clause = "del = 0")
public class InvestmentBehaviorInfo extends BaseEntity{

    @OneToOne
    private ProjectInvest projectInvest;

    private String investTurns; //投资轮次
    private String projectSource; // 项目来源
    private String currentStatus; // 当前状态
    private BigDecimal accumulatedCost; //累计成本
    private BigDecimal currentCost; // 现有成本
    private BigDecimal withdrawalCost; // 退出成本
    private BigDecimal accumulatedValue;// 累计价值
    private BigDecimal currentValue; //现有价值
    private BigDecimal payValue; // 实现价值
    private String estimateMethod; //估值方法
    private float profitTimes; //收益倍数
    private float profitRatio;//内部收益率
    private BigDecimal adjCurrentValue; //现有价值调整
    private float adjProfitTimes; //收益倍数调整
    private float adjProfitRatio;//内部收益率调整

    @Lob
    @Column(columnDefinition = "text")
    private String note; //调整说明


    public ProjectInvest getProjectInvest() {
        return projectInvest;
    }

    public void setProjectInvest(ProjectInvest projectInvest) {
        this.projectInvest = projectInvest;
    }

    public String getInvestTurns() {
        return investTurns;
    }

    public void setInvestTurns(String investTurns) {
        this.investTurns = investTurns;
    }

    public String getProjectSource() {
        return projectSource;
    }

    public void setProjectSource(String projectSource) {
        this.projectSource = projectSource;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public BigDecimal getAccumulatedCost() {
        return accumulatedCost;
    }

    public void setAccumulatedCost(BigDecimal accumulatedCost) {
        this.accumulatedCost = accumulatedCost;
    }

    public BigDecimal getCurrentCost() {
        return currentCost;
    }

    public void setCurrentCost(BigDecimal currentCost) {
        this.currentCost = currentCost;
    }

    public BigDecimal getWithdrawalCost() {
        return withdrawalCost;
    }

    public void setWithdrawalCost(BigDecimal withdrawalCost) {
        this.withdrawalCost = withdrawalCost;
    }

    public BigDecimal getAccumulatedValue() {
        return accumulatedValue;
    }

    public void setAccumulatedValue(BigDecimal accumulatedValue) {
        this.accumulatedValue = accumulatedValue;
    }

    public BigDecimal getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(BigDecimal currentValue) {
        this.currentValue = currentValue;
    }

    public BigDecimal getPayValue() {
        return payValue;
    }

    public void setPayValue(BigDecimal payValue) {
        this.payValue = payValue;
    }

    public String getEstimateMethod() {
        return estimateMethod;
    }

    public void setEstimateMethod(String estimateMethod) {
        this.estimateMethod = estimateMethod;
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

    public float getAdjProfitRatio() {
        return adjProfitRatio;
    }

    public void setAdjProfitRatio(float adjProfitRatio) {
        this.adjProfitRatio = adjProfitRatio;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BigDecimal getAdjCurrentValue() {
        return adjCurrentValue;
    }

    public void setAdjCurrentValue(BigDecimal adjCurrentValue) {
        this.adjCurrentValue = adjCurrentValue;
    }

    public float getAdjProfitTimes() {
        return adjProfitTimes;
    }

    public void setAdjProfitTimes(float adjProfitTimes) {
        this.adjProfitTimes = adjProfitTimes;
    }
}
