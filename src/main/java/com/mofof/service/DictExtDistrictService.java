package com.mofof.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mofof.entity.dict.ext.DictExtDistrict;
import com.mofof.repository.DictExtDistrictRepository;

@Service
public class DictExtDistrictService {

	@Autowired
	DictExtDistrictRepository dictExtDistrictRepository;
	
	public Set<DictExtDistrict> findByCityId(long id) {
		return dictExtDistrictRepository.findByDictExtCityId(id);
	}
}
