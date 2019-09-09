package com.mofof.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.mofof.entity.dict.ext.DictExtCountry;
import com.mofof.entity.dict.ext.DictExtProvince;

public interface DictExtCountryRepository extends CrudRepository<DictExtCountry, Long> {
	Set<DictExtCountry> findByDictExtContinentId(long id);

  DictExtCountry findByNameEn(String nameEn);
}
