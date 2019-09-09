package com.mofof.controller;

import com.mofof.entity.project.InvestmentBehaviorInfo;
import com.mofof.entity.project.*;
import com.mofof.service.InvestmentBehaviorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hzh on 17/7/6.
 */
@RestController
@RequestMapping(path = "/investment-behavior")
public class InvestmentBehaviorController {

    @Autowired
    InvestmentBehaviorService investmentBehaviorService;

    @GetMapping(path = "/investment-behavior-info")
    public InvestmentBehaviorInfo info(Long projectInvestId) {
        return investmentBehaviorService.investmentBehaviorInfo(projectInvestId);
    }

    @GetMapping(path = "/investment-behavior")
    public List<InvestmentBehavior> investmentBehaviors(Long projectInvestId) {
        return investmentBehaviorService.findAllInvestmentBehavior(projectInvestId);
    }

    @GetMapping(path = "/equity-investment")
    public List<EquityInvestment> equityInvestments(Long projectInvestId) {
        return investmentBehaviorService.findAllEquityInvestment(projectInvestId);
    }

    @GetMapping(path = "/equity-withdrawal")
    public List<EquityWithdrawal> equityWithdrawals(Long projectInvestId) {
        return investmentBehaviorService.findAllEquityWithdrawal(projectInvestId);
    }

    @GetMapping(path = "/debt-investment")
    public List<DebtInvestment> debtInvestments(Long projectInvestId) {
        return investmentBehaviorService.findAllDebtInvestment(projectInvestId);
    }

    @GetMapping(path = "/debt-withdrawal")
    public List<DebtWithdrawal> debtWithdrawals(Long projectInvestId) {
        return investmentBehaviorService.findAllDebtWithdrawal(projectInvestId);
    }

    @PostMapping(path = "/save-ei")
    public EquityInvestment saveEI(@RequestBody EquityInvestment equityInvestment) {
        return investmentBehaviorService.saveEI(equityInvestment);
    }

    @PostMapping(path = "/save-ew")
    public EquityWithdrawal saveEW(@RequestBody EquityWithdrawal equityWithdrawal) {
        return investmentBehaviorService.saveEW(equityWithdrawal);
    }

    @PostMapping(path = "/save-di")
    public DebtInvestment saveDI(@RequestBody DebtInvestment debtInvestment) {
        return investmentBehaviorService.saveDI(debtInvestment);
    }

    @PostMapping(path = "/save-dw")
    public DebtWithdrawal saveDW(@RequestBody DebtWithdrawal debtWithdrawal) {
        return investmentBehaviorService.saveDW(debtWithdrawal);
    }

    @PostMapping(path = "/delete")
    public void delete(@RequestBody List<InvestmentBehavior> investmentBehaviors) {
        investmentBehaviorService.delete(investmentBehaviors);
    }

    @PostMapping(path = "/deleteEI")
    public void deleteEI(@RequestBody List<EquityInvestment> equityInvestments) {
        investmentBehaviorService.deleteEI(equityInvestments);
    }

    @PostMapping(path = "/deleteEW")
    public void deleteEW(@RequestBody List<EquityWithdrawal> equityWithdrawals) {
        investmentBehaviorService.deleteEW(equityWithdrawals);
    }

    @PostMapping(path = "/deleteDI")
    public void deleteDI(@RequestBody List<DebtInvestment> debtInvestments) {
        investmentBehaviorService.deleteDI(debtInvestments);
    }

    @PostMapping(path = "/deleteDW")
    public void deleteDW(@RequestBody List<DebtWithdrawal> debtWithdrawals) {
        investmentBehaviorService.deleteDW(debtWithdrawals);
    }
}
