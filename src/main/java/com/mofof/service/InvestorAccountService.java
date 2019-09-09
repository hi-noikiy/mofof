package com.mofof.service;

import com.mofof.entity.fund.FundAccount;
import com.mofof.entity.investor.InvestorAccount;
import com.mofof.repository.InvestorAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author HanWeiFeng 2017年6月4日
 */
@Service
@Transactional
public class InvestorAccountService {

    @Autowired
    private InvestorAccountRepository accountRepository;

    public InvestorAccount save(InvestorAccount account) {
        return accountRepository.save(account);
    }

    public List<InvestorAccount> platformAccountList(Long investorId) {
        return accountRepository.findByInvestorIdOrderByIdDesc(investorId);
    }

    public InvestorAccount findById(Long id) {
        return accountRepository.findOne(id);
    }
    public InvestorAccount getInvestorDefaultAccount(Long investorId){
    	return accountRepository.findOneByInvestorIdAndIsDefault(investorId,true);
    }
    public void changeDefaultAccount() {
        accountRepository.changeDefault();
    }
    public void delete(List<InvestorAccount> accounts) {
    	accountRepository.delete(accounts);
    }
}
