package com.mofof.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.mofof.entity.dict.ext.DictExtCountry;
import com.mofof.entity.dict.ext.DictExtProvince;

public interface DictExtProvinceRepository extends CrudRepository<DictExtProvince, Long> {

	Set<DictExtProvince> findByDictExtCountryId(long id);

  DictExtProvince findByFullName(String fullName);
}
