package com.mofof.repository;

import com.mofof.entity.investor.InvestorAccount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author HanWeiFeng 2017年6月18日
 */
public interface InvestorAccountRepository extends CrudRepository<InvestorAccount, Long> {
    List<InvestorAccount> findByInvestorIdOrderByIdDesc(Long investorId);
    List<InvestorAccount> findAllByAccountAccountNumberOrderByIdDesc(String accountNumber);
    InvestorAccount findOneByInvestorIdAndIsDefault(Long investorId,boolean isDefault);
    @Query("UPDATE InvestorAccount ia SET ia.isDefault=false WHERE ia.isDefault=true")
    @Modifying
    void changeDefault();
}
