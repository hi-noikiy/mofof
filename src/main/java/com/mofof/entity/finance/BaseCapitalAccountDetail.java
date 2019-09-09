package com.mofof.entity.finance;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.math.BigDecimal;

/**
 * 权益表明细基类
 * Created by ChenErliang on 2017/8/6.
 */
@MappedSuperclass
public class BaseCapitalAccountDetail<T extends BaseFinanceItem> extends BaseFinanceDetail<T> {

    private BigDecimal gpAmount; // lp金额
    private BigDecimal lpAmount; // lp金额

    @Transient
    private BigDecimal platformAmount;

    public BigDecimal getGpAmount() {
        return gpAmount;
    }

    public void setGpAmount(BigDecimal gpAmount) {
        this.gpAmount = gpAmount;
    }

    public BigDecimal getLpAmount() {
        return lpAmount;
    }

    public void setLpAmount(BigDecimal lpAmount) {
        this.lpAmount = lpAmount;
    }

    public BigDecimal getPlatformAmount() {
        return platformAmount;
    }

    public void setPlatformAmount(BigDecimal platformAmount) {
        this.platformAmount = platformAmount;
    }
}
