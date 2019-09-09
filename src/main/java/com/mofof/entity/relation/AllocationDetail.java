package com.mofof.entity.relation;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

/**
 * Created by ChenErliang on 2017/6/8.
 * 出资明细/分配明细
 */
@Embeddable
public class AllocationDetail {

    private String allocationType; //出资类型/分配类型 字典
    private BigDecimal amount; //金额
    private int numLabel; // 分配类型对应的numLabel
    @Column(name = "reckon", columnDefinition = "bit default 0")
    private boolean reckon; //是否计入 承诺/收益
    private String note; //说明


    public String getAllocationType() {
        return allocationType;
    }

    public void setAllocationType(String allocationType) {
        this.allocationType = allocationType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setNumLabel(int numLabel) {
        this.numLabel = numLabel;
    }

    public int getNumLabel(){
        return this.numLabel;
    }
    
    public boolean isReckon() {
        return reckon;
    }

    public void setReckon(boolean reckon) {
        this.reckon = reckon;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
