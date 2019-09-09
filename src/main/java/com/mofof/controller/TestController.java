package com.mofof.controller;

import com.mofof.dto.SelectOption;
import com.mofof.service.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenerliang on 2017/7/20.
 */
@RestController
@RequestMapping(path = "/test")
public class TestController {

    @Autowired
    InvestorService investorService;

    @GetMapping(path = "/invest")
    public Map<String, Object> investor() {
        Map<String, Object> result = new HashMap<>();
        List<SelectOption> investorList = new ArrayList<>();
        List<SelectOption> platformList = new ArrayList<>();
        investorService.findAllInvestor().forEach(investor -> {
            SelectOption selectOption = new SelectOption(investor.getId(), investor.getChineseName());
            investorList.add(selectOption);
        });
        investorService.findAllPlatform().forEach(investor -> {
            SelectOption selectOption = new SelectOption(investor.getId(), investor.getChineseName());
            platformList.add(selectOption);
        });
        result.put("used", investorList);
        result.put("unused", platformList);
        return result;
    }
}
