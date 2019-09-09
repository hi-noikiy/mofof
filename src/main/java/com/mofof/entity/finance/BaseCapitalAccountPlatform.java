package com.mofof.entity.finance;

import com.mofof.entity.common.BaseEntity;
import com.mofof.entity.relation.InvestRelation;

import javax.persistence.ElementCollection;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;
import java.util.List;

/**
 * 权益表投资平台相关数据
 * Created by chenerliang on 2017/8/28.
 */

@MappedSuperclass
public class BaseCapitalAccountPlatform<T extends BaseCapitalAccount,R extends BaseCapitalAccountPlatformDetail> extends BaseEntity {


    @ManyToOne
    private InvestRelation investRelation;
    @ManyToOne
    private T capitalAccount;
    private BigDecimal platformPercent;
    @ElementCollection
    private List<R> details; //明细

    public InvestRelation getInvestRelation() {
        return investRelation;
    }

    public void setInvestRelation(InvestRelation investRelation) {
        this.investRelation = investRelation;
    }

    public T getCapitalAccount() {
        return capitalAccount;
    }

    public void setCapitalAccount(T capitalAccount) {
        this.capitalAccount = capitalAccount;
    }

    public BigDecimal getPlatformPercent() {
        return platformPercent;
    }

    public void setPlatformPercent(BigDecimal platformPercent) {
        this.platformPercent = platformPercent;
    }

    public List<R> getDetails() {
        return details;
    }

    public void setDetails(List<R> details) {
        this.details = details;
    }
}
