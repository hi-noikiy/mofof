package com.mofof.repository;

import com.mofof.entity.project.DebtWithdrawal;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by hzh on 17/7/6.
 */
public interface DebtWithdrawalRepository extends CrudRepository<DebtWithdrawal, Long> {

    List<DebtWithdrawal> findAllByProjectInvestId(Long id);
}
