package com.mofof.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.mofof.entity.dict.ext.DictExtIndustryL1;

public interface DictExtIndustryL1Repository extends CrudRepository<DictExtIndustryL1, Long>{

	Set<DictExtIndustryL1> findByDictExtIndustryId(long id);

  DictExtIndustryL1 findByNameEn(String nameEn);
}
