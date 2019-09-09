package com.mofof.entity.relation;

import com.mofof.entity.common.BaseEntity;
import com.mofof.entity.fund.Fund;
import com.mofof.entity.investor.Investor;//暂时保留
import com.mofof.entity.platform.Platform;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

/**
 * Created by ChenErliang on 2017/6/8.
 * 当前投资关系
 */
@Entity
@ApiModel(value="投资平台和基金间的投资关系")
@SQLDelete(sql = "update invest_relation set del = 1 where id = ?")
@SQLDeleteAll(sql = "update invest_relation set del = 1 where id = ?")
@Where(clause = "del = 0")
public class InvestRelation extends BaseEntity {

    @ApiModelProperty(value="对应的基金")
    @ManyToOne
    private Fund fund; //相关基金

    @ApiModelProperty(value="对应的投资人")//暂时恢复，后期再删掉
    @ManyToOne
    private Investor investor;//投资人

    @ApiModelProperty(value="对应的投资平台")
    @ManyToOne
    private Platform platform;//投资平台

    // private int investStatus; //0-投资中 2-已退出

    @ApiModelProperty(value="投资状态")
    private String investStatus; //改为保存字典内容，不再保存int

    @ApiModelProperty(value="投资金额")
    private BigDecimal investAmount;//投资金额

    @ApiModelProperty(value="投资日期")
    private LocalDate investDate;//投资日期

    @ApiModelProperty(value="备注")
    private String note;//备注

    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

    public Investor getInvestor() {
        return investor;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }
    
    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public String getInvestStatus() {
        return investStatus;
    }

    public void setInvestStatus(String investStatus) {
        this.investStatus = investStatus;
    }

    public BigDecimal getInvestAmount() {
        return investAmount;
    }

    public void setInvestAmount(BigDecimal investAmount) {
        this.investAmount = investAmount;
    }

    public LocalDate getInvestDate() {
        return investDate;
    }

    public void setInvestDate(LocalDate investDate) {
        this.investDate = investDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
