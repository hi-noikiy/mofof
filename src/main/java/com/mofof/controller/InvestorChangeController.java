package com.mofof.controller;

import com.mofof.entity.relation.InvestorChange;
import com.mofof.service.FundService;
import com.mofof.service.InvestorChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Created by hzh on 17/7/10.
 */
@RestController
@RequestMapping(path = "/investor-change")
public class InvestorChangeController {
    @Autowired
    InvestorChangeService investorChangeService;
    @Autowired
    FundService fundService;

    @GetMapping(path = "/investor-changes")
    public List<InvestorChange> investorChanges(Long fundId,String changeDate) {
    	List<InvestorChange> changes=investorChangeService.findAllByFundIdAndChangeDate(fundId, changeDate);
    	if (changes.size()==0) {
			InvestorChange investorChange=new InvestorChange();
			investorChange.setFund(fundService.findById(fundId));
			changes.add(investorChange);
		}
        return changes;
    }

    @PostMapping(path = "/save")
    public InvestorChange save(@RequestBody InvestorChange investorChange) {
        return investorChangeService.save(investorChange);
    }
}
