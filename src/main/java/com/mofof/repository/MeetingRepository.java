package com.mofof.repository;

import com.mofof.entity.fund.Meeting;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author HanWeiFeng 2017年6月25日
 */
public interface MeetingRepository extends CrudRepository<Meeting, Long> {
    List<Meeting> findAllByFundIdOrderByIdDesc(Long fundId);
}
