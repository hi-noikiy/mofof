package com.mofof.repository;

import com.mofof.entity.finance.FundCashFlowOrigin;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by ChenErliang on 2017/8/6.
 */

public class FundCashFlowOriginRepositoryImpl implements FinanceRepositoryCustom<FundCashFlowOrigin> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detach(FundCashFlowOrigin t) {
        entityManager.detach(t);
    }
}
