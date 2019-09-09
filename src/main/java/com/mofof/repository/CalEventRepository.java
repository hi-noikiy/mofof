package com.mofof.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mofof.entity.mywork.CalEvent;

public interface CalEventRepository extends CrudRepository<CalEvent, Long>,JpaSpecificationExecutor<CalEvent> {

  @Query(value = "select * from cal_event where creator_user_id= :id order by started_at", nativeQuery = true)
	Set<CalEvent> findAllByCreatorUserId(@Param("id") long id);

	@Query(value = "select * from cal_event where (started_at <= :end) and (ended_at >= :start) "
	    + " and id in (:ids) order by started_at", nativeQuery = true)
  Set<CalEvent> findByStartAt(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, @Param("ids") List<Object> ids);
	
	Set<CalEvent> findAll();

  List<CalEvent> findAll(Specification<CalEvent> specification);

  CalEvent findByTitle(String title);
	
}
