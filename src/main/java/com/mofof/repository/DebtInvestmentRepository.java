package com.mofof.repository;

import com.mofof.entity.project.DebtInvestment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by hzh on 17/7/6.
 */
public interface DebtInvestmentRepository extends CrudRepository<DebtInvestment, Long> {

    List<DebtInvestment> findAllByProjectInvestId(Long id);
}
