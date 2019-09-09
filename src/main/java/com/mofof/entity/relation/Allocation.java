package com.mofof.entity.relation;

import com.mofof.entity.common.BaseEntity;
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
 * Created by ChenErliang on 2017/6/8.
 * 出资/分配
 */
@Entity
@SQLDelete(sql = "update allocation set del = 1 where id = ?")
@SQLDeleteAll(sql = "update allocation set del = 1 where id = ?")
@Where(clause = "del = 0")
public class Allocation extends BaseEntity {
    public static int ALLOCATION_TYPE_CZ = 0;
    public static int Allocation_TYPE_FP = 1;

    @ManyToOne
    private InvestRelation investRelation;//相关投资关系
    private int allocationType; //0-出资 1-分配
    private LocalDate noticeDate; //通知日期
    private LocalDate gainedDate;//截止日期/计划日期
    private LocalDate actualDate; //实际日期
    private BigDecimal planAmount; //应付/应收
    private BigDecimal actualAmount; //实付/实收
    private int timeNumber;//次数
    private String note;//备注

    private Long toAccount; // 划入账户id  出资时为基金账户  分配时为投资人账户
    private Long fromAccount;  //划出账户id  出资时为投资人账户 分配时为基金账户

    @ElementCollection
    private List<AllocationDetail> allocationDetails; //出资明细/分配明细

    public InvestRelation getInvestRelation() {
        return investRelation;
    }

    public void setInvestRelation(InvestRelation investRelation) {
        this.investRelation = investRelation;
    }

    public int getAllocationType() {
        return allocationType;
    }

    public void setAllocationType(int allocationType) {
        this.allocationType = allocationType;
    }

    public LocalDate getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(LocalDate noticeDate) {
        this.noticeDate = noticeDate;
    }

    public LocalDate getGainedDate() {
        return gainedDate;
    }

    public void setGainedDate(LocalDate gainedDate) {
        this.gainedDate = gainedDate;
    }

    public LocalDate getActualDate() {
        return actualDate;
    }

    public void setActualDate(LocalDate actualDate) {
        this.actualDate = actualDate;
    }

    public BigDecimal getPlanAmount() {
        return planAmount;
    }

    public void setPlanAmount(BigDecimal planAmount) {
        this.planAmount = planAmount;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public List<AllocationDetail> getAllocationDetails() {
        return allocationDetails;
    }

    public void setAllocationDetails(List<AllocationDetail> allocationDetails) {
        this.allocationDetails = allocationDetails;
    }

    public int getTimeNumber() {
        return timeNumber;
    }

    public void setTimeNumber(int timeNumber) {
        this.timeNumber = timeNumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getToAccount() {
        return toAccount;
    }

    public void setToAccount(Long toAccount) {
        this.toAccount = toAccount;
    }

    public Long getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Long fromAccount) {
        this.fromAccount = fromAccount;
    }
}
