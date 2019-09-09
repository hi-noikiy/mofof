package com.mofof.repository;

import com.mofof.entity.finance.FundProfitLossStandard;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by ChenErliang on 2017/8/6.
 */

public class FundProfitLossStandardRepositoryImpl implements FinanceRepositoryCustom<FundProfitLossStandard> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detach(FundProfitLossStandard t) {
        entityManager.detach(t);
    }
}
