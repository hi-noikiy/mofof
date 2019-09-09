package com.mofof.repository;

import com.mofof.entity.project.EquityWithdrawal;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by hzh on 17/7/6.
 */
public interface EquityWithdrawalRepository extends CrudRepository<EquityWithdrawal, Long> {

    List<EquityWithdrawal> findAllByProjectInvestId(Long id);
}
