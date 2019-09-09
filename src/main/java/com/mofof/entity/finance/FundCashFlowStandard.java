package com.mofof.entity.finance;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;

/**
 * 现金流量表
 * Created by ChenErliang on 2017/8/6.
 */
@Entity
@SQLDelete(sql = "update fund_cash_flow_standard set del = 1 where id = ?")
@SQLDeleteAll(sql = "update fund_cash_flow_standard set del = 1 where id = ?")
@Where(clause = "del = 0")
public class FundCashFlowStandard extends BaseFinance<FundCashFlowStandardDetail> {

}
