package com.mofof.repository;

import com.mofof.entity.relation.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author HanWeiFeng 2017年6月18日
 */
public interface EventRepository extends CrudRepository<Event, Long> {
    List<Event> findAllByInvestRelationIdOrderByIdDesc(Long relationId);
}
