package com.mofof.repository;

import com.mofof.entity.finance.FundCashFlowStandard;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by ChenErliang on 2017/8/6.
 */

public class FundCashFlowStandardRepositoryImpl implements FinanceRepositoryCustom<FundCashFlowStandard> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detach(FundCashFlowStandard t) {
        entityManager.detach(t);
    }
}
