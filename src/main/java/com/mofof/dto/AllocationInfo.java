package com.mofof.dto;

import java.time.LocalDate;

/**
 * Created by hzh on 17/6/17.
 */
public class AllocationInfo {

    private LocalDate foundDate; //成立日期
    private LocalDate investDate; //投资日期
    private String fundStatus; //基金状态
    private String fundAmount;//基金规模
    private String investAmount; //投资金额
    private String investRatio; //投资比例
    private Integer investNum;//投资数量
    private String investCurrency;//投资币种
    //出资统计
    private LocalDate lastContributionDate;//最后出资日期
    private String contributionTime;//出资次数
    private String actualContributionAmount;//实际出资金额
    private String reckonAmount;//计入承诺金额
    private String contributionRatio;//出资比例
    //分配统计
    private LocalDate lastAllocationDate;//最后分配日期
    private String allocationTime;//分配次数
    private String actualAllocationAmount;//实际分配金额
    private String gainAmount;//计入收益金额
    private String allocationRatio;//分配比例

    //出处：
//            "fund.foundDate":'成立日期',
//            "fund.timeLine.investDate":'投资日期',
//            "fund.fundStatus": '基金状态',
//            "fund.fundAmount": '基金规模',
//            "investRelation.investAmount": '投资金额',


    public LocalDate getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(LocalDate foundDate) {
        this.foundDate = foundDate;
    }

    public LocalDate getInvestDate() {
        return investDate;
    }

    public void setInvestDate(LocalDate investDate) {
        this.investDate = investDate;
    }

    public String getFundStatus() {
        return fundStatus;
    }

    public void setFundStatus(String fundStatus) {
        this.fundStatus = fundStatus;
    }

    public String getFundAmount() {
        return fundAmount;
    }

    public void setFundAmount(String fundAmount) {
        this.fundAmount = fundAmount;
    }

    public String getInvestAmount() {
        return investAmount;
    }

    public void setInvestAmount(String investAmount) {
        this.investAmount = investAmount;
    }

    public String getInvestRatio() {
        return investRatio;
    }

    public void setInvestRatio(String investRatio) {
        this.investRatio = investRatio;
    }

    public LocalDate getLastContributionDate() {
        return lastContributionDate;
    }

    public void setLastContributionDate(LocalDate lastContributionDate) {
        this.lastContributionDate = lastContributionDate;
    }

    public String getContributionTime() {
        return contributionTime;
    }

    public void setContributionTime(String contributionTime) {
        this.contributionTime = contributionTime;
    }

    public String getActualContributionAmount() {
        return actualContributionAmount;
    }

    public void setActualContributionAmount(String actualContributionAmount) {
        this.actualContributionAmount = actualContributionAmount;
    }

    public String getReckonAmount() {
        return reckonAmount;
    }

    public void setReckonAmount(String reckonAmount) {
        this.reckonAmount = reckonAmount;
    }

    public String getContributionRatio() {
        return contributionRatio;
    }

    public void setContributionRatio(String contributionRatio) {
        this.contributionRatio = contributionRatio;
    }

    public LocalDate getLastAllocationDate() {
        return lastAllocationDate;
    }

    public void setLastAllocationDate(LocalDate lastAllocationDate) {
        this.lastAllocationDate = lastAllocationDate;
    }

    public String getAllocationTime() {
        return allocationTime;
    }

    public void setAllocationTime(String allocationTime) {
        this.allocationTime = allocationTime;
    }

    public String getActualAllocationAmount() {
        return actualAllocationAmount;
    }

    public void setActualAllocationAmount(String actualAllocationAmount) {
        this.actualAllocationAmount = actualAllocationAmount;
    }

    public String getGainAmount() {
        return gainAmount;
    }

    public void setGainAmount(String gainAmount) {
        this.gainAmount = gainAmount;
    }

    public String getAllocationRatio() {
        return allocationRatio;
    }

    public void setAllocationRatio(String allocationRatio) {
        this.allocationRatio = allocationRatio;
    }

    public Integer getInvestNum() {
        return investNum;
    }

    public void setInvestNum(Integer investNum) {
        this.investNum = investNum;
    }

    public String getInvestCurrency() {
        return investCurrency;
    }

    public void setInvestCurrency(String investCurrency) {
        this.investCurrency = investCurrency;
    }

}
