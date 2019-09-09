package com.mofof.repository;

import com.mofof.entity.finance.FundBalanceOrigin;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by ChenErliang on 2017/8/6.
 */

public class FundBalanceOriginRepositoryImpl implements FinanceRepositoryCustom<FundBalanceOrigin> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detach(FundBalanceOrigin t) {
        entityManager.detach(t);
    }
}
