package com.mofof.entity.finance;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;

/**
 * 权益表明细基类
 * Created by ChenErliang on 2017/8/6.
 */
@MappedSuperclass
public class BaseCapitalAccountPlatformDetail<T extends BaseFinanceItem> {

    @ManyToOne
    private T item; // 科目
    private BigDecimal amount; // 金额
    private String memo;//备注

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
