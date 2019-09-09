package com.mofof.entity.finance;

import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;

/**
 * 权益表数据基类
 * Created by ChenErliang on 2017/8/6.
 */
@MappedSuperclass
public class BaseCapitalAccount<T extends BaseCapitalAccountDetail> extends BaseFinance<T> {

    private BigDecimal lpPercent;
    private BigDecimal gpPercent;

    public BigDecimal getLpPercent() {
        return lpPercent;
    }

    public void setLpPercent(BigDecimal lpPercent) {
        this.lpPercent = lpPercent;
    }

    public BigDecimal getGpPercent() {
        return gpPercent;
    }

    public void setGpPercent(BigDecimal gpPercent) {
        this.gpPercent = gpPercent;
    }
}
