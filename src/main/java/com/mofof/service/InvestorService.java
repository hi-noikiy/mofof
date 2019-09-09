package com.mofof.service;

import com.mofof.dto.InvestorInfos;
import com.mofof.entity.investor.Investor;
import com.mofof.entity.investor.InvestorAccount;
import com.mofof.entity.relation.Allocation;
import com.mofof.entity.relation.InvestRelation;
import com.mofof.repository.AllocationRepository;
import com.mofof.repository.InvestRelationRepository;
import com.mofof.repository.InvestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HanWeiFeng 2017年6月4日
 */
@Service
@Transactional
public class InvestorService {

    @Autowired
    private InvestorRepository investorRepository;
    @Autowired
    private AllocationRepository allocationRepository;
    @Autowired
    private InvestRelationRepository investRelationRepository;

    public Investor save(Investor investor) {
        return investorRepository.save(investor);
    }

    public Iterable<Investor> findAllInvestor() {
        return investorRepository.findAllByOrderByIdDesc();
    }

    public Iterable<Investor> findAllPlatform() {
        return investorRepository.findAllByPlatformOrderByIdDesc(true);
    }

    public List<InvestorInfos> findAllPlatformInvestorInfos() {
        List<InvestorInfos> infos = new ArrayList<InvestorInfos>();
        List<Investor> list = investorRepository.findAllByPlatformOrderByIdDesc(true);
        for (Investor investor : list) {
            InvestorInfos investorInfos = new InvestorInfos();
            investorInfos.setInvestType(investor.getOrganization().getOrganizationType());
            investorInfos.setId(investor.getId());
            BigDecimal reckonAmount = BigDecimal.ZERO;
            BigDecimal actualContributionAmount = BigDecimal.ZERO;
            BigDecimal gainAmount = BigDecimal.ZERO;
            BigDecimal actualAllocationAmount = BigDecimal.ZERO;
            List<InvestRelation> relations = investRelationRepository.findAllByInvestorIdOrderByIdDesc(investor.getId());
            BigDecimal allocationRatio = BigDecimal.ZERO;
            BigDecimal contributionRatio = BigDecimal.ZERO;
            int investNum = 0;
            if (relations.size() > 0) {
                for (InvestRelation relation : relations) {
                    List<Allocation> allocations = allocationRepository.findByInvestRelationId(relation.getId());
                    investNum += allocations.size();
                    for (Allocation allocation : allocations) {
                        if (allocation.getAllocationType() == 0) {//出资
                            reckonAmount = reckonAmount.add(allocation.getPlanAmount() == null ? BigDecimal.ZERO : allocation.getPlanAmount());
                            actualContributionAmount = actualContributionAmount.add(allocation.getActualAmount() == null ? BigDecimal.ZERO : allocation.getActualAmount());
                        } else {
                            gainAmount = gainAmount.add(allocation.getPlanAmount() == null ? BigDecimal.ZERO : allocation.getPlanAmount());
                            actualAllocationAmount = actualAllocationAmount.add(allocation.getActualAmount() == null ? BigDecimal.ZERO : allocation.getActualAmount());
                        }
                    }
                    if (reckonAmount.compareTo(BigDecimal.ZERO) != 0) {
                        contributionRatio = actualContributionAmount.multiply(new BigDecimal("100")).divide(reckonAmount, 2, BigDecimal.ROUND_HALF_UP);
                    }
                    if (gainAmount.compareTo(BigDecimal.ZERO) != 0) {
                        allocationRatio = actualAllocationAmount.multiply(new BigDecimal("100")).divide(gainAmount, 2, BigDecimal.ROUND_HALF_UP);
                    }
                }
            }
            investorInfos.setActualAllocationAmount(actualAllocationAmount);
            investorInfos.setActualContributionAmount(actualContributionAmount);
            investorInfos.setGainAmount(gainAmount);
            investorInfos.setReckonAmount(reckonAmount);
            investorInfos.setPlatformName(investor.getOrganization().getChineseName());
            investorInfos.setContributionRatio(contributionRatio + "%");
            investorInfos.setAllocationRatio(allocationRatio + "%");
            investorInfos.setInvestNum(investNum + "*基金");
            infos.add(investorInfos);
        }
        return infos;
    }

    public Investor findById(Long id) {
        return investorRepository.findOne(id);
    }

    public Iterable<Investor> findByName(String chineseName) {
        return investorRepository.findAllByIndividualChineseNameLike(chineseName);
    }
    
    public Iterable<Investor> findOrganizationByName(String chineseName) {
        return investorRepository.findAllByOrganizationChineseNameLike(chineseName);
    }
    public void delete(List<Investor> investors) throws Exception {
    	investorRepository.delete(investors);
    }
}
