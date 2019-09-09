package com.mofof.service;

import com.mofof.dto.AllocationInfo;
import com.mofof.entity.fund.Fund;
import com.mofof.entity.relation.Allocation;
import com.mofof.entity.fund.TimeLine;
import com.mofof.entity.relation.InvestRelation;
import com.mofof.repository.AllocationRepository;
import com.mofof.repository.InvestRelationRepository;
import com.mofof.repository.TimelineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzh on 17/6/18.
 */
@Service
@Transactional
public class AllocationService {
    @Autowired
    AllocationRepository allocationRepository;
    @Autowired
    InvestRelationRepository investRelationRepository;
    @Autowired
    TimelineRepository timelineRepository;

    //统计
    public AllocationInfo census(Long investRelationId) {
        InvestRelation ir = investRelationRepository.findOne(investRelationId);
        TimeLine timeLine = timelineRepository.findByFundId(ir.getFund().getId());
        List<Allocation> contributions = allocationRepository.findByInvestRelationIdAndAllocationTypeAndActualDateIsNotNullOrderByActualDateDesc(investRelationId, Allocation.ALLOCATION_TYPE_CZ);
        List<Allocation> allocations = allocationRepository.findByInvestRelationIdAndAllocationTypeAndActualDateIsNotNullOrderByActualDateDesc(investRelationId, Allocation.Allocation_TYPE_FP);
        Fund fund = ir.getFund();

        //JSON.toJSONString(lists);
        AllocationInfo ai = new AllocationInfo();

        ai.setFoundDate(fund.getFoundDate()); //等同基金成立日期
        if(timeLine!=null){
            ai.setInvestDate(timeLine.getInvestDate()); // 等同管理费的投资日期
        }
        ai.setFundStatus(fund.getFundStatus()); // 同基金状态
        ai.setFundAmount(String.valueOf(fund.getFundAmount())); //同基金规模
        ai.setInvestAmount(String.valueOf(fund.getFundAmount()));//同基金规模

        if (ai.getInvestAmount()!=null&&!"".equals(ai.getFundAmount()) &&!"null".equals(ai.getFundAmount()) && ai.getInvestAmount()!=null&&!"".equals(ai.getInvestAmount())&&!"null".equals(ai.getInvestAmount())) {
            Float ratio = 0f;
            if (!"0".equals(ai.getFundAmount())) {
                ratio = Float.valueOf(ai.getInvestAmount()) / Float.valueOf(ai.getFundAmount());
            }
            ratio = (float) (Math.round(ratio * 10000)) / 10000;
            ai.setInvestRatio(String.valueOf(ratio));
        }
        ai.setInvestNum(allocations.size() + contributions.size());  //投资数量  ？？？
        ai.setInvestCurrency(fund.getCurrency()); //同基金币种
        if (contributions.size() > 0) {
            ai.setLastContributionDate(contributions.get(0).getActualDate());  // 最后出资日期
        }
        ai.setContributionTime(String.valueOf(contributions.size())); // 出资次数

        BigDecimal actualContributionAmount = BigDecimal.ZERO;
        BigDecimal reckonAmount = BigDecimal.ZERO;
        for (Allocation contribution : contributions) {
            actualContributionAmount = actualContributionAmount.add(contribution.getActualAmount());
            reckonAmount = reckonAmount.add(contribution.getPlanAmount());
        }
        ai.setActualContributionAmount(actualContributionAmount.toString());  //实际出资金额
        ai.setReckonAmount(reckonAmount.toString()); //记入承诺金额

        if (!"null".equals(ai.getInvestAmount()) &&ai.getInvestAmount() !=null&&!"".equals(ai.getInvestAmount()) && ai.getReckonAmount()!=null&&!"".equals(ai.getReckonAmount())) {
            Float ratio = 0f;
            if (!"0".equals(ai.getInvestAmount())) {
                ratio = Float.valueOf(ai.getReckonAmount()) / Float.valueOf(ai.getInvestAmount());
            }
            ratio = (float) (Math.round(ratio * 10000)) / 10000;
            ai.setContributionRatio(String.valueOf(ratio));//出资比例
        }

        if (allocations.size() > 0) {
            ai.setLastAllocationDate(allocations.get(0).getActualDate());  // 最后分配日期
        }
        ai.setAllocationTime(String.valueOf(allocations.size())); // 分配次数

        BigDecimal actualAllocationAmount = BigDecimal.ZERO;
        BigDecimal gainAmount = BigDecimal.ZERO;
        for (Allocation allocation : allocations) {
            actualAllocationAmount = actualAllocationAmount.add(allocation.getActualAmount());
            gainAmount = gainAmount.add(allocation.getPlanAmount());
        }
        ai.setActualAllocationAmount(actualAllocationAmount.toString());  //实际分配金额
        ai.setGainAmount(gainAmount.toString());// 记入收益金额

        if (!"null".equals(ai.getInvestAmount()) &&ai.getInvestAmount()!=null&&!"".equals(ai.getInvestAmount()) &&ai.getGainAmount()!=null&& !"".equals(ai.getGainAmount())) {
            Float ratio = 0f;
            if (!"0".equals(ai.getInvestAmount())) {
                ratio = Float.valueOf(ai.getGainAmount()) / Float.valueOf(ai.getInvestAmount());
            }
            ratio = (float) (Math.round(ratio * 10000)) / 10000;
            ai.setAllocationRatio(String.valueOf(ratio));//分配比例
        }

        // foundDate:'成立日期',
        //   investDate:'起始投资人日期',
        //   fundStatus: '基金状态',
        //   fundAmount: '基金总规模',
        //   investAmount: '投资总金额',
        //   investRatio: '投资比例',
        //   investNum:'投资数量',
        //   investCurrency:'投资币种',
        //   //出资统计
        //   lastContributionDate: '最后出资日期',
        //   contributionTime: '出资次数',
        //   actualContributionAmount: '实际出资金额',
        //   reckonAmount: '计入承诺金额',
        //   contributionRatio: '出资比例',
        //   //分配统计
        //   lastAllocationDate: '最后分配日期',
        //   allocationTime: '分配次数',
        //   actualAllocationAmount: '实际分配金额',
        //   gainAmount: '计入收益金额',
        //   allocationRatio:'分配比例'

        return ai;
    }

    //投资平台统计
    public AllocationInfo platformCensus(Long platformId) {
        AllocationInfo ai = new AllocationInfo();
        BigDecimal investAmount = BigDecimal.ZERO;//投资金额
        BigDecimal fundAmount = BigDecimal.ZERO;//基金规模
        int contributionTime = 0;
        BigDecimal reckonAmount = BigDecimal.ZERO;
        BigDecimal actualContributionAmount = BigDecimal.ZERO;
        int allocationTime = 0;
        BigDecimal gainAmount = BigDecimal.ZERO;
        BigDecimal actualAllocationAmount = BigDecimal.ZERO;
        List<InvestRelation> investRelations = investRelationRepository.findAllByInvestorIdOrderByIdDesc(platformId);
        if (investRelations.size() > 0) {
            ai.setInvestDate(investRelations.get(0).getInvestDate());
            ai.setInvestNum(investRelations.size());

        } else {
            ai.setInvestNum(0);
        }
        for (int i = 0; i < investRelations.size(); i++) {
            InvestRelation ir = investRelations.get(0);
            investAmount = investAmount.add(ir.getInvestAmount());
            fundAmount = fundAmount.add(new BigDecimal(ir.getFund().getFundAmount()));
            List<Allocation> lists = allocationRepository.findByInvestRelationId(ir.getId());
            for (Allocation allocation : lists) {
                if (allocation.getAllocationType() == 0) {//出资
                    if (i == 0 && ai.getLastContributionDate() == null) {
                        ai.setLastContributionDate(allocation.getActualDate());
                    }
                    contributionTime += allocation.getTimeNumber();
                    reckonAmount = reckonAmount.add(allocation.getPlanAmount());
                    actualContributionAmount = actualContributionAmount.add(allocation.getActualAmount());
                } else {
                    if (i == 0 && ai.getLastAllocationDate() == null) {
                        ai.setLastAllocationDate(allocation.getActualDate());
                    }
                    allocationTime += allocation.getTimeNumber();
                    gainAmount = gainAmount.add(allocation.getPlanAmount());
                    actualAllocationAmount = actualAllocationAmount.add(allocation.getActualAmount());
                }
            }
        }
        ai.setInvestAmount(investAmount.toString());
        ai.setFundAmount(fundAmount.toString());
        BigDecimal allocationRatio = BigDecimal.ZERO;
        BigDecimal contributionRatio = BigDecimal.ZERO;
        if (reckonAmount.compareTo(BigDecimal.ZERO) != 0) {
            contributionRatio = actualContributionAmount.multiply(new BigDecimal("100")).divide(reckonAmount, 2, BigDecimal.ROUND_HALF_UP);
        }
        if (gainAmount.compareTo(BigDecimal.ZERO) != 0) {
            allocationRatio = actualAllocationAmount.multiply(new BigDecimal("100")).divide(gainAmount, 2, BigDecimal.ROUND_HALF_UP);
        }
        ai.setActualContributionAmount(actualContributionAmount.toString());
        ai.setContributionTime(contributionTime + "");
        ai.setReckonAmount(reckonAmount.toString());
        ai.setContributionRatio(contributionRatio + "%");
        ai.setActualAllocationAmount(actualAllocationAmount.toString());
        ai.setAllocationRatio(allocationRatio + "%");
        ai.setAllocationTime(allocationTime + "");
        ai.setGainAmount(gainAmount.toString());
        ai.setInvestCurrency("人民币");//默认人民币
        return ai;
    }

    public Iterable<InvestRelation> findAll() {
        return investRelationRepository.findAll();
    }

    public List<Allocation> findPlatformAllocations(Long platformId, int allocationType, LocalDate startDateForSearch, LocalDate endDateForSearch) {
        List<Allocation> list = new ArrayList<>();
        List<InvestRelation> investRelations = investRelationRepository.findAllByInvestorIdOrderByIdDesc(platformId);
        for (InvestRelation iRelation : investRelations) {
            List<Allocation> allocations = allocationRepository.find(iRelation.getId(), allocationType, startDateForSearch, endDateForSearch);
            list.addAll(allocations);
        }
        return list;
    }

    public List<Allocation> findInvestRelationAllocations(Long investRelationId) {
        return allocationRepository.findByInvestRelationId(investRelationId);
    }

    public List<Allocation> findAllocations(Long investRelationId, int allocationType, LocalDate startDateForSearch, LocalDate endDateForSearch) {
//        System.err.println(startDateForSearch);
//        System.err.println(endDateForSearch);
        return allocationRepository.find(investRelationId, allocationType, startDateForSearch, endDateForSearch);
    }

    public Allocation save(Allocation allocation) {
        return allocationRepository.save(allocation);
    }

}
