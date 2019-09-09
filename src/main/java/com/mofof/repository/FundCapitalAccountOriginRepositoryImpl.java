package com.mofof.repository;

import com.mofof.entity.finance.FundCapitalAccountOrigin;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by ChenErliang on 2017/8/6.
 */

public class FundCapitalAccountOriginRepositoryImpl implements FinanceRepositoryCustom<FundCapitalAccountOrigin> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detach(FundCapitalAccountOrigin t) {
        entityManager.detach(t);
    }
}
