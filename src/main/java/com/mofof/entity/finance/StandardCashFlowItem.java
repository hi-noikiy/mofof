package com.mofof.entity.finance;


import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;

/**
 * 基金现金流量表科目（标准）
 * Created by chenerliang on 2017/8/11.
 */
@Entity
@SQLDelete(sql = "update standard_cash_flow_item set del = 1 where id = ?")
@SQLDeleteAll(sql = "update standard_cash_flow_item set del = 1 where id = ?")
@Where(clause = "del = 0")
public class StandardCashFlowItem extends BaseFinanceItem {

}
