package com.mofof.entity.fund;

import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by ChenErliang on 17/6/8.
 * 时间节点
 */
@Entity
@SQLDelete(sql = "update time_line set del = 1 where id = ?")
@SQLDeleteAll(sql = "update time_line set del = 1 where id = ?")
@Where(clause = "del = 0")
public class TimeLine extends BaseEntity {

    @OneToOne
    private Fund fund;
    private LocalDate investDate; //投资日期
    private LocalDate entitleDate; //起算日期
    private LocalDate settleDate; //清算日期
    private LocalDate investEndDate; //投资期结束日期
    private LocalDate paybackEndDate; //回收期结束日期
    private LocalDate extendEndDate; //延长期结束日期

    private String payCycle; // 支付周期
    private String paymentWay;//支付方式
    private Float offsetDays; //提前或延后天数
    private String dayType; //支付日类型 日然日或工作日
    @Lob
    @Column(columnDefinition = "text")
    private String note;//备注

    @ElementCollection
    private List<AdminFee> adminFees; //管理费率

    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

    public LocalDate getInvestDate() {
        return investDate;
    }

    public void setInvestDate(LocalDate investDate) {
        this.investDate = investDate;
    }

    public LocalDate getEntitleDate() {
        return entitleDate;
    }

    public void setEntitleDate(LocalDate entitleDate) {
        this.entitleDate = entitleDate;
    }

    public LocalDate getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(LocalDate settleDate) {
        this.settleDate = settleDate;
    }

    public LocalDate getInvestEndDate() {
        return investEndDate;
    }

    public void setInvestEndDate(LocalDate investEndDate) {
        this.investEndDate = investEndDate;
    }

    public LocalDate getPaybackEndDate() {
        return paybackEndDate;
    }

    public void setPaybackEndDate(LocalDate paybackEndDate) {
        this.paybackEndDate = paybackEndDate;
    }

    public LocalDate getExtendEndDate() {
        return extendEndDate;
    }

    public void setExtendEndDate(LocalDate extendEndDate) {
        this.extendEndDate = extendEndDate;
    }

    public List<AdminFee> getAdminFees() {
        return adminFees;
    }

    public void setAdminFees(List<AdminFee> adminFees) {
        this.adminFees = adminFees;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPayCycle() {
        return payCycle;
    }

    public void setPayCycle(String payCycle) {
        this.payCycle = payCycle;
    }

    public String getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(String paymentWay) {
        this.paymentWay = paymentWay;
    }

    public float getOffsetDays() {
        return offsetDays;
    }

    public void setOffsetDays(float offsetDays) {
        this.offsetDays = offsetDays;
    }

    public String getDayType() {
        return dayType;
    }

    public void setDayType(String dayType) {
        this.dayType = dayType;
    }
}
