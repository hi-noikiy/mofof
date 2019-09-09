package com.mofof.repository;

import com.mofof.entity.fund.TimeLine;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by hzh on 17/6/12.
 */
public interface TimelineRepository extends CrudRepository<TimeLine, Long> {
    TimeLine findByFundId(Long id);
}
