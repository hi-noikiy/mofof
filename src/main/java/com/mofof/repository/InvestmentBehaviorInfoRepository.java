package com.mofof.repository;

import com.mofof.entity.project.InvestmentBehaviorInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by hzh on 2017/10/15.
 */
public interface InvestmentBehaviorInfoRepository extends CrudRepository<InvestmentBehaviorInfo, Long> {
    public InvestmentBehaviorInfo findByProjectInvestOrderByIdDesc(Long projectInvestId);
}
