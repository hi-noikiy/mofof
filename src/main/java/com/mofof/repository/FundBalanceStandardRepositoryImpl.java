package com.mofof.repository;

import com.mofof.entity.finance.FundBalanceStandard;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by ChenErliang on 2017/8/6.
 */

public class FundBalanceStandardRepositoryImpl implements FinanceRepositoryCustom<FundBalanceStandard> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detach(FundBalanceStandard t) {
        entityManager.detach(t);
    }
}
