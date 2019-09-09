package com.mofof.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.mofof.entity.dict.ext.DictExtIndustryL3;

public interface DictExtIndustryL3Repository extends CrudRepository<DictExtIndustryL3, Long> {

	Set<DictExtIndustryL3> findByDictExtIndustryL2Id(long id);
}
