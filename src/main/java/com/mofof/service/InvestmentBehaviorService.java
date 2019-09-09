package com.mofof.service;

import com.mofof.entity.project.InvestmentBehaviorInfo;
import com.mofof.entity.project.*;
import com.mofof.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by hzh on 17/7/6.
 */
@Service
@Transactional
public class InvestmentBehaviorService {
    @Autowired
    InvestmentBehaviorRepository investmentBehaviorRepository;
    @Autowired
    EquityInvestmentRepository equityInvestmentRepository;
    @Autowired
    EquityWithdrawalRepository equityWithdrawalRepository;
    @Autowired
    DebtInvestmentRepository debtInvestmentRepository;
    @Autowired
    DebtWithdrawalRepository debtWithdrawalRepository;
    @Autowired
    InvestmentBehaviorInfoRepository investmentBehaviorInfoRepository;
    @Autowired
    ProjectInvestRepository projectInvestRepository;

    public InvestmentBehaviorInfo investmentBehaviorInfo(Long projectInvestId) {
        InvestmentBehaviorInfo ibi = null;
        if(projectInvestId!=null){
            ibi = investmentBehaviorInfoRepository.findByProjectInvestOrderByIdDesc(projectInvestId);
            if(ibi==null){
                ProjectInvest projectInvest = projectInvestRepository.findOne(projectInvestId);
                ibi = new InvestmentBehaviorInfo();
                ibi.setProjectInvest(projectInvest);
            }

            ibi.setAccumulatedCost(BigDecimal.TEN);
            ibi.setAccumulatedValue(BigDecimal.ONE);
            ibi.setAdjCurrentValue(BigDecimal.ZERO);
            ibi.setAdjProfitRatio(40.12f);
            ibi.setCurrentCost(BigDecimal.ZERO);
            ibi.setCurrentStatus("未知");
            ibi.setCurrentValue(BigDecimal.TEN);
            ibi.setEstimateMethod("L3");
            ibi.setInvestTurns("28");
            ibi.setNote("调整说明：暂时是造的数据");
            ibi.setPayValue(BigDecimal.ZERO);
            ibi.setAdjProfitTimes(1.80f);
            ibi.setProfitRatio(19.54f);
            ibi.setProfitTimes(1.99f);
            ibi.setProjectSource("未知");
            ibi.setWithdrawalCost(BigDecimal.ONE);

//            private String investTurns; //投资轮次
//            private String projectSource; // 项目来源
//            private String currentStatus; // 当前状态
//            private BigDecimal accumulatedCost; //累计成本
//            private BigDecimal currentCost; // 现有成本
//            private BigDecimal withdrawalCost; // 退出成本
//            private BigDecimal accumulatedValue;// 累计价值
//            private BigDecimal currentValue; //现有价值
//            private BigDecimal payValue; // 实现价值
//            private String estimateMethod; //估值方法
//            private float profitTimes; //收益倍数
//            private float profitRatio;//内部收益率
//            private BigDecimal adjCurrentValue; //现有价值调整
//            private float adjProfitTimes; //收益倍数调整
//            private float adjProfitRatio;//内部收益率调整

            investmentBehaviorInfoRepository.save(ibi);

            return ibi;
        }else{
            return  ibi;
        }
    }

    public InvestmentBehavior save(InvestmentBehavior investmentBehavior) {
        return investmentBehaviorRepository.save(investmentBehavior);
    }

    public List<InvestmentBehavior> findAllInvestmentBehavior(Long projectInvestId) {
        return investmentBehaviorRepository.findAllByProjectInvestId(projectInvestId);
    }


    public EquityInvestment saveEI(EquityInvestment equityInvestment) {
        return equityInvestmentRepository.save(equityInvestment);
    }

    public EquityWithdrawal saveEW(EquityWithdrawal equityWithdrawal) {
        return equityWithdrawalRepository.save(equityWithdrawal);
    }

    public DebtInvestment saveDI(DebtInvestment debtInvestment) {
        return debtInvestmentRepository.save(debtInvestment);
    }

    public DebtWithdrawal saveDW(DebtWithdrawal debtWithdrawal) {
        return debtWithdrawalRepository.save(debtWithdrawal);
    }

    public List<EquityInvestment> findAllEquityInvestment(Long projectInvestId) {
        return equityInvestmentRepository.findAllByProjectInvestId(projectInvestId);
    }

    public List<EquityWithdrawal> findAllEquityWithdrawal(Long projectInvestId) {
        return equityWithdrawalRepository.findAllByProjectInvestId(projectInvestId);
    }

    public List<DebtInvestment> findAllDebtInvestment(Long projectInvestId) {
        return debtInvestmentRepository.findAllByProjectInvestId(projectInvestId);
    }

    public List<DebtWithdrawal> findAllDebtWithdrawal(Long projectInvestId) {
        return debtWithdrawalRepository.findAllByProjectInvestId(projectInvestId);
    }

    public void delete(List<InvestmentBehavior> investmentBehaviors) {
        investmentBehaviorRepository.delete(investmentBehaviors);
    }


    public void deleteEI(List<EquityInvestment> equityInvestments) {
        equityInvestmentRepository.delete(equityInvestments);
    }

    public void deleteEW(List<EquityWithdrawal> equityWithdrawals) {
        equityWithdrawalRepository.delete(equityWithdrawals);
    }

    public void deleteDI(List<DebtInvestment> debtInvestments) {
        debtInvestmentRepository.delete(debtInvestments);
    }

    public void deleteDW(List<DebtWithdrawal> debtWithdrawals) {
        debtWithdrawalRepository.delete(debtWithdrawals);
    }


}
