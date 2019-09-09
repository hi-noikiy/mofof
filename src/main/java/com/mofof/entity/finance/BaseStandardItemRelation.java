package com.mofof.entity.finance;

import com.mofof.entity.common.BaseEntity;
import com.mofof.entity.fund.Fund;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * 标准表跨表勾稽关系
 * Created by chenerliang on 2017/8/23.
 */
@MappedSuperclass
public class BaseStandardItemRelation<T extends BaseFinanceItem> extends BaseEntity {

    @ManyToOne
    private Fund fund;
    @ManyToOne
    private T item;
    private String relation;

    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
