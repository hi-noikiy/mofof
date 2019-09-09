package com.mofof.repository;

import com.mofof.entity.finance.BaseFinance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by ChenErliang on 2017/8/6.
 */
@NoRepositoryBean
public interface FinanceRepository<T extends BaseFinance> extends CrudRepository<T, Long> {

    T findOneByFundIdAndYearAndFinanceTypeAndCycle(Long fundId, String year, String financeType, String Cycle);

    List<T> findByFundIdAndFinanceDateGreaterThanEqualAndFinanceDateLessThanEqual(Long fundId, LocalDate beginDate, LocalDate endDate);


}
