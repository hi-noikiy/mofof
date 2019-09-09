package com.mofof.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mofof.entity.dict.ext.DictExtCity;
import com.mofof.repository.DictExtCityRepository;

@Service
public class DictExtCityService {
	
	@Autowired
	DictExtCityRepository dictExtCityRepository;
	
	public Set<DictExtCity> findByProvinceId(long id) {
		return dictExtCityRepository.findByDictExtProvinceId(id);
	}
}
