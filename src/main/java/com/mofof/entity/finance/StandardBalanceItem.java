package com.mofof.entity.finance;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;

/**
 * 基金资产负债表科目（标准）
 * Created by chenerliang on 2017/7/27.
 */

@Entity
@SQLDelete(sql = "update standard_balance_item set del = 1 where id = ?")
@SQLDeleteAll(sql = "update standard_balance_item set del = 1 where id = ?")
@Where(clause = "del = 0")
public class StandardBalanceItem extends BaseFinanceItem {

}
