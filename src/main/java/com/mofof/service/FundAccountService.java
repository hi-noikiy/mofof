package com.mofof.service;

import com.mofof.entity.fund.FundAccount;
import com.mofof.repository.FundAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hzh on 17/6/16.
 */
@Service
@Transactional
public class FundAccountService {
    @Autowired
    FundAccountRepository fundAccountRepository;

    public FundAccount findOne(Long id) {
        return fundAccountRepository.findOne(id);
    }
    
    public FundAccount getFundDefaultAccount(Long fundId){
    	return fundAccountRepository.findOneByFundIdAndIsDefault(fundId,true);
    }
    
    public List<FundAccount> findByFundId(Long fundId) {
        return fundAccountRepository.findByFundId(fundId);
    }

    public FundAccount save(FundAccount fundAccount) {
        return fundAccountRepository.save(fundAccount);
    }

    public void delete(List<FundAccount> fundAccounts) {
        fundAccountRepository.delete(fundAccounts);
    }
    public void changeDefaultAccount() {
    	fundAccountRepository.changeDefault();
    }
}