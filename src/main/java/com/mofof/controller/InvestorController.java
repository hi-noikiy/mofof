package com.mofof.controller;

import com.mofof.dto.InvestorInfos;
import com.mofof.entity.investor.Investor;
import com.mofof.entity.investor.InvestorAccount;
import com.mofof.service.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author HanWeiFeng 2017年6月4日
 */
@RestController
@RequestMapping(path = "/investor")
public class InvestorController {

    @Autowired
    InvestorService investorService;

    @PostMapping(path = "/save")
    public Investor newRecord(@RequestBody Investor investor) {
        return investorService.save(investor);
    }
    @PostMapping(path = "/delete")
    public String delete(@RequestBody List<Investor> investors) {
    	String flag="success";
    	try {
    		investorService.delete(investors);
		} catch (Exception e) {
			e.printStackTrace();
			flag="fail";
		}		
        return flag;
    }
    @PostMapping(path = "/savePlatform")
    public Investor newPlatformRecord(@RequestBody Investor investor) {
        investor.setPlatform(true);
        investor.setType(1);
        investor.getOrganization().setOrganizationType("非基金");
        return investorService.save(investor);
    }

    @GetMapping(path = "/all")
    public Iterable<Investor> allInvestors() {
        return investorService.findAllInvestor();
    }

    @GetMapping(path = "/platforms")
    public List<InvestorInfos> allPlatforms() {
        return investorService.findAllPlatformInvestorInfos();
    }

    @GetMapping(path = "/investor")
    public Investor getInvestor(Long id) {
        return investorService.findById(id);
    }

    @GetMapping(path = "/platform")
    public Investor getPlatform(Long id) {
        return investorService.findById(id);
    }

    @GetMapping(path = "/byName")
    public Iterable<Investor> investors(@RequestParam("chineseName") String chineseName) {
        return investorService.findByName("%" + chineseName + "%");
    }
}
