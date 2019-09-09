package com.mofof.entity.finance;

import com.mofof.entity.common.BaseEntity;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

/**
 * Created by chenerliang on 2017/8/22.
 */
@MappedSuperclass
public class BaseFinanceItem extends BaseEntity {

    @Embedded
    private com.mofof.entity.common.FinanceItem financeItem; //科目

    private boolean deleted;//是否删除

    private int orderNum;

    public com.mofof.entity.common.FinanceItem getFinanceItem() {
        return financeItem;
    }

    public void setFinanceItem(com.mofof.entity.common.FinanceItem financeItem) {
        this.financeItem = financeItem;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
}
