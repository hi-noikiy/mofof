package com.mofof.entity.finance;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;

/**
 * 利润表
 * Created by ChenErliang on 2017/8/6.
 */
@Entity
@SQLDelete(sql = "update fund_profit_loss_standard set del = 1 where id = ?")
@SQLDeleteAll(sql = "update fund_profit_loss_standard set del = 1 where id = ?")
@Where(clause = "del = 0")
public class FundProfitLossStandard extends BaseFinance<FundProfitLossStandardDetail> {


}
