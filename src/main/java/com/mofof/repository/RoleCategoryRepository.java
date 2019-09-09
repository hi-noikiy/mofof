package com.mofof.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.mofof.entity.system.RoleCategory;

/**
 * Created by Qian, Weliang on 2017/8/16.
 */
public interface RoleCategoryRepository extends CrudRepository<RoleCategory, Long> {
    Iterable<RoleCategory> findAllByOrderById();
    
    Set<RoleCategory> findAll();

    RoleCategory findByName(String name);
}
