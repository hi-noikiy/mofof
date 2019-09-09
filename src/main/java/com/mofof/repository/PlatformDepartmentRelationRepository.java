package com.mofof.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.mofof.entity.relation.PlatformDepartmentRelation;

public interface PlatformDepartmentRelationRepository  extends CrudRepository<PlatformDepartmentRelation,Long>{

  PlatformDepartmentRelation findByNote(String note);
  
  List<PlatformDepartmentRelation> findAll();
  
  List<PlatformDepartmentRelation> findByDepartmentId(Long id);
  
  @Transactional
  @Modifying
  @Query(value = "delete from platform_department_relation where department_id = :id", nativeQuery = true)
  int deleteByDepartmentId(@Param("id") long id);
}
