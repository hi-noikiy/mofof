package com.mofof.repository;

import com.mofof.entity.fund.FundAccount;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by hzh on 17/6/16.
 */

public interface FundAccountRepository extends CrudRepository<FundAccount, Long> {

    List<FundAccount> findByFundId(Long fundId);
    List<FundAccount> findAllByAccountAccountNumberOrderByIdDesc(String accountNumber);
    FundAccount findOneByFundIdAndIsDefault(Long fundId,boolean isDefault);
    @Query("UPDATE FundAccount fa SET fa.isDefault=false WHERE fa.isDefault=true")
    @Modifying
    void changeDefault();
}
