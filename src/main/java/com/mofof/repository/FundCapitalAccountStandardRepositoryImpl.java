package com.mofof.repository;

import com.mofof.entity.finance.FundCapitalAccountOrigin;
import com.mofof.entity.finance.FundCapitalAccountStandard;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by ChenErliang on 2017/8/6.
 */

public class FundCapitalAccountStandardRepositoryImpl implements FinanceRepositoryCustom<FundCapitalAccountStandard> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detach(FundCapitalAccountStandard t) {
        entityManager.detach(t);
    }
}
