package com.mofof.repository;

import com.mofof.entity.affiliate.Affiliation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ChenErliang on 2017/5/6.
 */
public interface AffiliationRepository extends CrudRepository<Affiliation, Long> {

    List<Affiliation> findByFundIdOrderByIdDesc(Long fundId);

    List<Affiliation> findByAffiliateIdOrderByIdDesc(Long affiliateId);

    List<Affiliation> findAllByFundIdAndAffiliateIdOrderByIdDesc(Long fundId,Long affiliateId);

    List<Affiliation> findAllByFundIdOrderByIdDesc(Long fundId);

    List<Affiliation> findAllByAffiliateIdOrderByIdDesc(Long affiliateId);


}
