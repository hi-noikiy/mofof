package com.mofof.repository;

import com.mofof.entity.project.EquityInvestment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by hzh on 17/7/6.
 */
public interface EquityInvestmentRepository extends CrudRepository<EquityInvestment, Long> {
    List<EquityInvestment> findAllByProjectInvestId(Long id);
}
