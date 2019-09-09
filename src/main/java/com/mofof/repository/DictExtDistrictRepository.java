package com.mofof.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.mofof.entity.dict.ext.DictExtDistrict;

public interface DictExtDistrictRepository extends CrudRepository<DictExtDistrict, Long> {

	Set<DictExtDistrict> findByDictExtCityId(long id);
}
