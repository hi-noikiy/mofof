package com.mofof.controller;

import com.mofof.entity.fund.Agreement;
import com.mofof.service.AgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author HanWeiFeng 2017年7月1日
 */
@RestController
@RequestMapping(path = "/agreement")
public class AgreementController {
    @Autowired
    AgreementService agreementService;

    @GetMapping(path = "/all")
    public List<Agreement> allAgreements(Long fundId) {
        return agreementService.findAllAgreements(fundId);
    }

    @PostMapping(path = "/save")
    public Agreement newRecord(@RequestBody Agreement agreement) {
        return agreementService.save(agreement);
    }

    @GetMapping(path = "/agreement")
    public Agreement getAgreement(Long id) {
        return agreementService.findById(id);
    }

    @PostMapping(path = "/delete")
    public void delete(@RequestBody Agreement agreement) {
         agreementService.delete(agreement);
    }

}
