package com.mofof.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mofof.entity.system.Role;

/**
 * Created by Qian, Weliang on 2017/8/16.
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
    Iterable<Role> findAllByOrderByIdDesc();
    Set<Role> findByRoleCategoryId(long id);
    
    @Query(value = "select * from role WHERE name = :name ", nativeQuery = true)
    Role findByName(@Param("name") String name);
}
