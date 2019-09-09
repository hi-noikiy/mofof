package com.mofof.entity.fund;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

/**
 * Created by ChenErliang on 17/6/8.
 * 管理费
 */
@Embeddable
public class AdminFee {

    private String fundStatus; //基金状态

    private String feeBase;  //计算基数
    private float feePercent; //费率（百分比）
    @Lob
    @Column(columnDefinition = "text")
    private String note;// 描述

    public String getFundStatus() {
        return fundStatus;
    }

    public void setFundStatus(String fundStatus) {
        this.fundStatus = fundStatus;
    }

    public String getFeeBase() {
        return feeBase;
    }

    public void setFeeBase(String feeBase) {
        this.feeBase = feeBase;
    }

    public float getFeePercent() {
        return feePercent;
    }

    public void setFeePercent(float feePercent) {
        this.feePercent = feePercent;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
