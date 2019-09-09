package com.mofof.entity.finance;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;

/**
 * Created by chenerliang on 2017/8/28.
 */
@Entity
@SQLDelete(sql = "update fund_capital_account_platform_origin set del = 1 where id = ?")
@SQLDeleteAll(sql = "update fund_capital_account_platform_origin set del = 1 where id = ?")
@Where(clause = "del = 0")
public class FundCapitalAccountPlatformOrigin extends BaseCapitalAccountPlatform<FundCapitalAccountOrigin,FundCapitalAccountPlatformOriginDetail> {
}
