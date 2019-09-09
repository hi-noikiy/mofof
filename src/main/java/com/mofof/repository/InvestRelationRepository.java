package com.mofof.repository;

import com.mofof.entity.relation.InvestRelation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ChenErliang on 17/6/11.
 */
public interface InvestRelationRepository extends CrudRepository<InvestRelation, Long> {
    Iterable<InvestRelation> findAllByInvestorPlatformOrderByIdDesc(boolean platform);

    List<InvestRelation> findAllByInvestorIdOrderByIdDesc(Long platformId);
}
