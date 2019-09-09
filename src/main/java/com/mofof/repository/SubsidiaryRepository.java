package com.mofof.repository;

import com.mofof.entity.fund.Subsidiary;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by hzh on 17/6/14.
 */
public interface SubsidiaryRepository extends CrudRepository<Subsidiary, Long> {
    List<Subsidiary> findAllByFundIdOrderByChangeDateDesc(Long fundId);
    Subsidiary findByFundIdAndSubsidiaryTypeOrderByChangeDateDesc(Long fundId, String type);
}
