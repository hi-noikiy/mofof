package com.mofof.repository;

import com.mofof.entity.project.InvestmentBehavior;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by hzh on 17/7/6.
 */
public interface InvestmentBehaviorRepository extends CrudRepository<InvestmentBehavior, Long> {

    List<InvestmentBehavior> findAllByProjectInvestId(Long id);
}
