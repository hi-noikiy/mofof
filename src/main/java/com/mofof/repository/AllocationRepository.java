package com.mofof.repository;

import com.mofof.entity.relation.Allocation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by hzh on 17/6/16.
 */


public interface AllocationRepository extends CrudRepository<Allocation, Long> {
    List<Allocation> findByInvestRelationId(Long id);

    @Query(value = "select * from allocation where invest_relation_id=?1 and allocation_type=?2 and (?3 is null||actual_date >= ?3) and (?4 is null||actual_date <= ?4) order by id desc", nativeQuery = true)
    List<Allocation> find(Long investRelationId, int allocationType, LocalDate startDateForSearch, LocalDate endDateForSearch);

    List<Allocation> findAllByTimeNumberAndInvestRelationIdAndAllocationTypeOrderByIdDesc(Integer value,Long investRelationId,Integer allocationType);


//    @Query(value = "select * from allocation where invest_relation_id=?1 and allocation_type=?2 and (actual_date not null&&actual_date != '') order by id desc", nativeQuery = true)
    List<Allocation> findByInvestRelationIdAndAllocationTypeAndActualDateIsNotNullOrderByActualDateDesc(Long investRelationId, int allocationType);

}
