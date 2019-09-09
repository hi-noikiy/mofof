package com.mofof.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mofof.entity.dict.ext.DictExtProvince;
import com.mofof.repository.DictExtProvinceRepository;

@Service
public class DictExtProvinceService {

	@Autowired
	DictExtProvinceRepository dictExtProvinceRepository;
	
	public Set<DictExtProvince> findByCountryId(long id) {
		return dictExtProvinceRepository.findByDictExtCountryId(id);
	}
}
