package com.mofof.controller;

import com.mofof.entity.affiliate.Affiliate;
import com.mofof.entity.affiliate.Affiliation;
import com.mofof.service.AffiliateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ChenErliang on 2017/5/6.
 */
@RestController
@RequestMapping(path = "/affiliate")
public class AffiliateController {

    @Autowired
    private AffiliateService affiliateService;

    @GetMapping(path = "/allAffiliationsForFund")
    public List<Affiliation> allAffiliationsForFund(Long fundId) {
        return affiliateService.allAffiliationForFund(fundId);
    }

    @GetMapping(path = "/allAffiliationsForAffiliate")
    public List<Affiliation> allAffiliationsForAffiliate(Long affiliateId) {
        return affiliateService.allAffiliationForAffiliate(affiliateId);
    }

    @PostMapping(path = "/saveAffiliation")
    public Affiliation saveAffiliation(@RequestBody Affiliation affiliation) {
        return affiliateService.saveAffiliation(affiliation);
    }

    @PostMapping(path = "/saveAffiliate")
    public Affiliate saveAffiliate(@RequestBody Affiliate affiliate) {
        return affiliateService.saveAffiliate(affiliate);
    }

    @PostMapping(path = "/delete")
    public void delete(@RequestBody List<Affiliate> affiliates) {
        affiliateService.delete(affiliates);
    }


    @PostMapping(path = "/deleteAffiliation")
    public void deleteAffiliation(@RequestBody List<Affiliation> affiliations) {
        affiliateService.deleteAffiliation(affiliations);
    }

    @GetMapping(path = "/allAffiliates")
    public Iterable<Affiliate> allAffiliate() {
        return affiliateService.allAffiliate();
    }

    @GetMapping(path = "/affiliate")
    public Affiliate affiliate(Long id) {
        return affiliateService.findOne(id);
    }
}
