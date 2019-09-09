package com.mofof.controller;

import com.mofof.entity.affiliate.Affiliate;
import com.mofof.entity.affiliate.Affiliation;
import com.mofof.entity.common.Individual;
import com.mofof.entity.fund.Fund;
import com.mofof.service.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hzh on 2017/8/6.
 */
@RestController
@RequestMapping(path = "/validate")
public class ValidateController {
    @Autowired
    ValidateService validateService;

    @GetMapping(path = "/isOrganizationChineseNameUnique")
    public boolean isOrganizationChineseNameUnique(String value, Long oldId) {
        return validateService.isOrganizationChineseNameUnique(value, oldId);
    }

    @GetMapping(path = "/isOrganizationEnglishNameUnique")
    public boolean isOrganizationEnglishNameUnique(String value, Long oldId) {
        return validateService.isOrganizationEnglishNameUnique(value, oldId);
    }
    @GetMapping(path = "/isPlatformAccountNumberUnique")
    public boolean isAccountNumberUnique(String value, Long oldId) {
        return validateService.isPlatformAccountNumberUnique(value,oldId);
    }
    @GetMapping(path = "/isFundAccountNumberUnique")
    public boolean isFundAccountNumberUnique(String value, Long oldId) {
        return validateService.isFundAccountNumberUnique(value,oldId);
    }
    @GetMapping(path = "/sameIndividualChineseName")
    public List<Individual> sameIndividualChineseName(String value, Long oldId) {
        return validateService.sameIndividualChineseName(value, oldId);
    }

    @GetMapping(path = "/sameIndividualEnglishName")
    public List<Individual> sameIndividualEnglishName(String value, Long oldId) {
        return validateService.sameIndividualEnglishName(value, oldId);
    }

    @GetMapping(path = "/isFundUniqueForAffiliate")
    public boolean isFundUniqueForAffiliate(Long fundId,Long affiliateId, Long oldId) {
        return validateService.isAffiliationUnique(fundId,affiliateId,oldId);
    }

    @GetMapping(path = "/isAffiliateUniqueForFund")
    public boolean isAffiliateUniqueForFund(Long fundId,Long affiliateId, Long oldId) {
        return validateService.isAffiliationUnique(fundId,affiliateId,oldId);
    }

    @GetMapping(path = "/sameAffiliateByFund")
    public List<Affiliate> sameAffiliateByFund(Long fundId,Long oldId) {
        return validateService.sameAffiliateByFund(fundId,oldId);
    }

    @GetMapping(path = "/sameFundByAffiliate")
    public List<Fund> sameFundByAffiliate(Long affiliateId,Long oldId) {
        return validateService.sameFundByAffiliate(affiliateId,oldId);
    }

    @GetMapping(path = "/isAllocationTimeNumberUnique")
    public boolean isAllocationTimeNumberUnique(Integer value, Long investRelationId,Long oldId,Integer allocationType) {
        return validateService.isAllocationTimeNumberUnique(value, investRelationId,oldId,allocationType);
    }

    @GetMapping(path = "/isAgreementNameUnique")
    public boolean isAgreementNameUnique(String value, Long oldId,Long fundId) {
        return validateService.isAgreementNameUnique(value, oldId,fundId);
    }

}
