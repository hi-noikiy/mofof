package com.mofof.dto;

import java.math.BigDecimal;

/**
 * 投资信息
 *
 * @author HanWeiFeng 2017年7月7日
 */
public class InvestorInfos {
    private Long id;//投资平台id
    private String platformName;//名称
    private String investType;//类型
    private String investNum;//投资数
    private BigDecimal actualContributionAmount;//实际出资金额
    private BigDecimal reckonAmount;//承诺出资金额
    private String contributionRatio;//出资比例
    private BigDecimal actualAllocationAmount;//实际分配金额
    private BigDecimal gainAmount;//计入收益金额
    private String allocationRatio;//分配比例

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getInvestType() {
        return investType;
    }

    public void setInvestType(String investType) {
        this.investType = investType;
    }

    public String getInvestNum() {
        return investNum;
    }

    public void setInvestNum(String investNum) {
        this.investNum = investNum;
    }

    public BigDecimal getActualContributionAmount() {
        return actualContributionAmount;
    }

    public void setActualContributionAmount(BigDecimal actualContributionAmount) {
        this.actualContributionAmount = actualContributionAmount;
    }

    public BigDecimal getReckonAmount() {
        return reckonAmount;
    }

    public void setReckonAmount(BigDecimal reckonAmount) {
        this.reckonAmount = reckonAmount;
    }

    public String getContributionRatio() {
        return contributionRatio;
    }

    public void setContributionRatio(String contributionRatio) {
        this.contributionRatio = contributionRatio;
    }

    public BigDecimal getActualAllocationAmount() {
        return actualAllocationAmount;
    }

    public void setActualAllocationAmount(BigDecimal actualAllocationAmount) {
        this.actualAllocationAmount = actualAllocationAmount;
    }

    public BigDecimal getGainAmount() {
        return gainAmount;
    }

    public void setGainAmount(BigDecimal gainAmount) {
        this.gainAmount = gainAmount;
    }

    public String getAllocationRatio() {
        return allocationRatio;
    }

    public void setAllocationRatio(String allocationRatio) {
        this.allocationRatio = allocationRatio;
    }

}
