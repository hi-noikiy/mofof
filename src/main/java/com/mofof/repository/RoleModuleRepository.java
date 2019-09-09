package com.mofof.repository;



import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.google.gson.JsonElement;
import com.mofof.entity.system.RoleModule;



public interface RoleModuleRepository extends CrudRepository<RoleModule, Long> {

  boolean existsBySysKey(String sysKey);

  RoleModule findBySysKey(String sysKey);

  Set<RoleModule> findByRoleId(long id);

  @Query(value = "select * from role_module where parent_id IS NULL and role_id = :id and sys_group = :sysGroup", nativeQuery = true)
  Set<RoleModule> findByRoleIdAndSysGroup(@Param("id") long id, @Param("sysGroup") String sysGroup);

//  RoleModule findByParentIdAndTabName(long id, String tabName);
  @Query(value = "select * from role_module where parent_id = :id and name = :tabName", nativeQuery = true)
  RoleModule findByParentIdAndTabName(@Param("id") long id, @Param("tabName")String tabName);
  
  @Query(value = "select * from role_module where parent_id IS NULL and name = :tabName", nativeQuery = true)
  RoleModule findByTabName(@Param("tabName")String tabName);

//  Set<RoleModule> findByRoleIdAndSysGroup(long id, String moduleGroup);

  Set<RoleModule> findByRoleIdAndSysGroupAndParentRoleModuleIsNotNull(long id, String moduleGroup);
}
