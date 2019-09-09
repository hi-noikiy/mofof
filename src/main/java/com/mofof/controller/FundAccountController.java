package com.mofof.controller;


import com.mofof.entity.fund.FundAccount;
import com.mofof.service.FundAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hzh on 17/6/16.
 */
@RestController
@RequestMapping(path = "/fundAccount")
public class FundAccountController {
    @Autowired
    FundAccountService fundAccountService;

    @GetMapping(path = "/fundAccount")
    public FundAccount fundaccount(Long id) {
        return fundAccountService.findOne(id);
    }

    @GetMapping(path = "/fundAccountList")
    public List<FundAccount> fundAccountList(Long fundId) {
        return fundAccountService.findByFundId(fundId);
    }

    @PostMapping(path = "/save")
    public FundAccount save(@RequestBody FundAccount fundAccount) {
        //修改默认账户
    	if (fundAccount.isDefault()) {
    		fundAccountService.changeDefaultAccount();
        }
        return fundAccountService.save(fundAccount);
    }

    @PostMapping(path = "/delete")
    public void delete(@RequestBody List<FundAccount> fundAccounts) {
        fundAccountService.delete(fundAccounts);
    }

    @GetMapping(path = "/defaultAccount")
    public FundAccount getFundDefaultAccount(Long fundId) {
        return fundAccountService.getFundDefaultAccount(fundId);
    }
}
