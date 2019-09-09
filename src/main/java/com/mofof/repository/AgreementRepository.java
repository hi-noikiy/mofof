package com.mofof.repository;

import com.mofof.entity.fund.Agreement;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author HanWeiFeng 2017年7月1日
 */
public interface AgreementRepository extends CrudRepository<Agreement, Long> {
    List<Agreement> findAllByFundIdOrderByIdDesc(Long fundId);

    List<Agreement> findAllByNameAndFundIdOrderByIdDesc(String name,Long fundId);

    List<Agreement> findAllByFundIdAndAgreementTypeOrderByIdDesc(Long fundId,String type);
}