package com.mofof.repository;

import com.mofof.entity.fund.Institution;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by hzh on 17/6/14.
 */
public interface InstitutionRepository extends CrudRepository<Institution, Long> {
    List<Institution> findAllByFundIdOrderByChangeDateDesc(Long fundId);
    List<Institution> findAllByFundIdAndInstitutionTypeOrderByChangeDateDesc(Long fundId,String type);
}
