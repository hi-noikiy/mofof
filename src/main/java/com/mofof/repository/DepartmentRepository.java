package com.mofof.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mofof.entity.system.Department;

/**
 * Created by Qian, Weliang on 2017/8/16.
 */
public interface DepartmentRepository extends CrudRepository<Department, Long> {
    Iterable<Department> findAllByOrderByIdDesc();

    List<Department> findAllByParentIdIsNullOrderByIdDesc();

    Department findByName(String name);
    
}
