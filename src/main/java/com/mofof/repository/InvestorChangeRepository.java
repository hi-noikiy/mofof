package com.mofof.repository;

import com.mofof.entity.relation.InvestorChange;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by hzh on 17/7/10.
 */
public interface InvestorChangeRepository extends CrudRepository<InvestorChange, Long> {

    List<InvestorChange> findAllByFundIdOrderByChangeDateDesc(Long id);
    List<InvestorChange> findAllByFundIdAndChangeDateLessThanEqualOrderByChangeDateDesc(Long id,LocalDate changeDate);
    List<InvestorChange> findAllByFundIdAndChangeDateGreaterThanOrderByChangeDateDesc(Long id,LocalDate changeDate);
}
