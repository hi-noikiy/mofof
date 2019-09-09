package com.mofof.repository;

import com.mofof.entity.finance.FundProfitLossOrigin;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by ChenErliang on 2017/8/6.
 */

public class FundProfitLossOriginRepositoryImpl implements FinanceRepositoryCustom<FundProfitLossOrigin> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detach(FundProfitLossOrigin t) {
        entityManager.detach(t);
    }
}
