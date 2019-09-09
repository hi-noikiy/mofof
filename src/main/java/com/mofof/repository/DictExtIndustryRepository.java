package com.mofof.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.mofof.entity.dict.ext.DictExtIndustry;

public interface DictExtIndustryRepository extends CrudRepository<DictExtIndustry, Long> {

	Set<DictExtIndustry> findAll();
}
