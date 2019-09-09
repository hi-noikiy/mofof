package com.mofof.service;

import com.mofof.entity.affiliate.Affiliate;
import com.mofof.entity.affiliate.Affiliation;
import com.mofof.repository.AffiliateRepository;
import com.mofof.repository.AffiliationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ChenErliang on 2017/5/6.
 */
@Service
@Transactional
public class AffiliateService {

    @Autowired
    private AffiliateRepository affiliateRepository;
    @Autowired
    private AffiliationRepository affiliationRepository;

    public List<Affiliation> allAffiliationForFund(Long fundId) {
        return affiliationRepository.findByFundIdOrderByIdDesc(fundId);
    }

    public List<Affiliation> allAffiliationForAffiliate(Long affiliateId) {
        return affiliationRepository.findByAffiliateIdOrderByIdDesc(affiliateId);
    }

    public Affiliation saveAffiliation(Affiliation affiliation) {
        return affiliationRepository.save(affiliation);
    }


    public Iterable<Affiliate> allAffiliate() {
        return affiliateRepository.findAll();
    }

    public Affiliate findOne(Long id) {
        return affiliateRepository.findOne(id);
    }

    public Affiliate saveAffiliate(Affiliate affiliate) {
        return affiliateRepository.save(affiliate);
    }

    public void delete(List<Affiliate> affiliates) {
        affiliateRepository.delete(affiliates);
    }

    public void deleteAffiliation(List<Affiliation> affiliations) {
        affiliationRepository.delete(affiliations);
    }
}
