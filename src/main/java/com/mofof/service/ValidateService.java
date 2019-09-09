package com.mofof.service;

import com.mofof.entity.affiliate.Affiliate;
import com.mofof.entity.affiliate.Affiliation;
import com.mofof.entity.common.Individual;
import com.mofof.entity.common.Organization;
import com.mofof.entity.fund.Agreement;
import com.mofof.entity.fund.Fund;
import com.mofof.entity.fund.FundAccount;
import com.mofof.entity.investor.InvestorAccount;
import com.mofof.repository.AffiliateRepository;
import com.mofof.repository.AffiliationRepository;
import com.mofof.repository.FundAccountRepository;
import com.mofof.repository.IndividualRepository;
import com.mofof.repository.InvestorAccountRepository;
import com.mofof.repository.OrganizationRepository;
import com.mofof.entity.relation.Allocation;
import com.mofof.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzh on 2017/8/6.
 */
@Service
@Transactional
public class ValidateService {

    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    IndividualRepository individualRepository;
    @Autowired
    AffiliateRepository affiliateRepository;
    @Autowired
    AffiliationRepository affiliationRepository;
    @Autowired
    InvestorAccountRepository investorAccountRepository;
    @Autowired
    FundAccountRepository fundAccountRepository;
    @Autowired
    AllocationRepository allocationRepository;
    @Autowired
    AgreementRepository agreementRepository;

    /**
     * OrganizationChineseName是否唯一
     *
     * @param value
     * @param oldId
     * @return boolean
     */
    public Boolean isOrganizationChineseNameUnique(String value, Long oldId) {
        List<Organization> list = organizationRepository.findAllByChineseNameOrderByIdDesc(value);
        for (Organization item : list) {
            if (item.getId().equals(oldId)) return true;
        }
        return list.size() == 0;
    }

    public Boolean isOrganizationEnglishNameUnique(String value, Long oldId) {
        List<Organization> list = organizationRepository.findAllByEnglishNameOrderByIdDesc(value);
        for (Organization item : list) {
            if (item.getId().equals(oldId)) return true;
        }
        return list.size() == 0;
    }
    public Boolean isPlatformAccountNumberUnique(String value, Long oldId) {
        List<InvestorAccount> list = investorAccountRepository.findAllByAccountAccountNumberOrderByIdDesc(value);
        for (InvestorAccount item : list) {
            if (item.getId().equals(oldId)) return true;
        }
        return list.size() == 0;
    }
    public Boolean isFundAccountNumberUnique(String value, Long oldId) {
        List<FundAccount> list = fundAccountRepository.findAllByAccountAccountNumberOrderByIdDesc(value);
        for (FundAccount item : list) {
            if (item.getId().equals(oldId)) return true;
        }
        return list.size() == 0;
    }
    public List<Individual> sameIndividualChineseName(String value, Long oldId) {
        List<Individual> list = individualRepository.findAllByChineseNameLikeOrderByIdDesc("%" + value + "%");
        Individual old = null;
        for (Individual item : list) {
            if (item.getId().equals(oldId)) old = item;
        }
        if (old != null) list.remove(old);
        return list;
    }

    public List<Individual> sameIndividualEnglishName(String value, Long oldId) {
        List<Individual> list = individualRepository.findAllByEnglishNameLikeOrderByIdDesc("%" + value + "%");
        Individual old = null;
        for (Individual item : list) {
            if (item.getId().equals(oldId)) old = item;
        }
        if (old != null) list.remove(old);
        return list;
    }

    public Boolean isAffiliationUnique(Long fundId, Long affiliateId,Long oldId) {
        List<Affiliation> list = affiliationRepository.findAllByFundIdAndAffiliateIdOrderByIdDesc(fundId,affiliateId);
        for (Affiliation item : list) {
            if (item.getId().equals(oldId)) return true;
        }
        return list.size() == 0;
    }

    public List<Affiliate> sameAffiliateByFund(Long fundId,Long oldId) {
        List<Affiliate> affiliates = new ArrayList<>();
        List<Affiliation> affiliations = affiliationRepository.findAllByFundIdOrderByIdDesc(fundId);
        for (Affiliation item:affiliations) {
            if(!item.getId().equals(oldId))  affiliates.add(item.getAffiliate());
        }
        return  affiliates;
    }

    public List<Fund> sameFundByAffiliate(Long affiliateId,Long oldId) {
        List<Fund> funds = new ArrayList<>();
        List<Affiliation> affiliations = affiliationRepository.findAllByAffiliateIdOrderByIdDesc(affiliateId);
        for (Affiliation item:affiliations) {
            if(!item.getId().equals(oldId)) funds.add(item.getFund());
        }
        return  funds;
    }


    /**
     * 出资/分配次数是否唯一
     * @param value
     * @param oldId
     * @param allocationType
     * @return
     */
    public Boolean isAllocationTimeNumberUnique(Integer value, Long investRelationId,Long oldId,Integer allocationType) {
        List<Allocation> list = allocationRepository.findAllByTimeNumberAndInvestRelationIdAndAllocationTypeOrderByIdDesc(value,investRelationId,allocationType);
        for (Allocation item : list) {
            if (item.getId().equals(oldId)) return true;
        }
        return list.size() == 0;
    }

    /**
     * 一个基金中协议名称是否唯一
     * @param value
     * @param oldId
     * @param fundId
     * @return
     */
    public Boolean isAgreementNameUnique(String value, Long oldId,Long fundId) {
        List<Agreement> list = agreementRepository.findAllByNameAndFundIdOrderByIdDesc(value,fundId);
        for (Agreement item : list) {
            if (item.getId().equals(oldId)) return true;
        }
        return list.size() == 0;
    }


}
