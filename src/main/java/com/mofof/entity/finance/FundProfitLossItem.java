package com.mofof.entity.finance;

import com.mofof.entity.fund.Fund;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by chenerliang on 2017/8/11.
 */
@Entity
@SQLDelete(sql = "update fund_profit_loss_item set del = 1 where id = ?")
@SQLDeleteAll(sql = "update fund_profit_loss_item set del = 1 where id = ?")
@Where(clause = "del = 0")
public class FundProfitLossItem extends BaseFinanceItem {

    @ManyToOne
    private Fund fund; //相关基金


    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

}
