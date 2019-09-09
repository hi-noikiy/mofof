package com.mofof.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.mofof.entity.dict.ext.DictExtContinent;
import com.mofof.entity.dict.ext.DictExtCountry;

public interface DictExtContinentRepository extends CrudRepository<DictExtContinent, Long>{

	Set<DictExtContinent> findAll();

  boolean existsByNameEn(String nameEn);
}
