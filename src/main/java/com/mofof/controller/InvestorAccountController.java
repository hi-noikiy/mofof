package com.mofof.controller;

import com.mofof.entity.investor.InvestorAccount;
import com.mofof.service.InvestorAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author HanWeiFeng 2017年6月18日
 */
@RestController
@RequestMapping(path = "/platformAccount")
public class InvestorAccountController {

    @Autowired
    InvestorAccountService accountService;

    @PostMapping(path = "/save")
    public InvestorAccount newRecord(@RequestBody InvestorAccount platformAccount) {
        if (platformAccount.isDefault()) {
            accountService.changeDefaultAccount();
        }
        return accountService.save(platformAccount);
    }

    @GetMapping(path = "/platformAccountList")
    public List<InvestorAccount> platformAccountList(Long platformId) {
        //platformId 就是 investorId
        return accountService.platformAccountList(platformId);
    }

    @GetMapping(path = "/account")
    public InvestorAccount getInvestor(Long id) {
        return accountService.findById(id);
    }
    @PostMapping(path = "/delete")
    public void delete(@RequestBody List<InvestorAccount> accounts) {
         accountService.delete(accounts);
    }
    @GetMapping(path = "/defaultAccount")
    public InvestorAccount getInvestorDefaultAccount(Long investorId) {
        return accountService.getInvestorDefaultAccount(investorId);
    }
}
