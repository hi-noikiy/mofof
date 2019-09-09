package com.mofof.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.mofof.entity.dict.ext.DictExtIndustryL2;

public interface DictExtIndustryL2Repository extends CrudRepository<DictExtIndustryL2, Long> {

	Set<DictExtIndustryL2> findByDictExtIndustryL1Id(long id);

  DictExtIndustryL2 findByNameEn(String nameEn);
}
