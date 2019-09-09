package com.mofof.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.mofof.entity.dict.ext.DictExtCity;

public interface DictExtCityRepository extends CrudRepository<DictExtCity, Long>{

	Set<DictExtCity> findByDictExtProvinceId(long id);

  DictExtCity findByFullName(String fullName);
}
