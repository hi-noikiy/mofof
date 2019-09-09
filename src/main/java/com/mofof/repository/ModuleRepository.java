package com.mofof.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.mofof.entity.system.Module;

public interface ModuleRepository extends CrudRepository<Module, Long>{

  Set<Module> findAll();

  Module findBySysKey(String sysKey);

  boolean existsBySysKey(String sysKey);
}
