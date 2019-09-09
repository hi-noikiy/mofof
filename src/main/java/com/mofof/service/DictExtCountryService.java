package com.mofof.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mofof.entity.dict.ext.DictExtCountry;
import com.mofof.repository.DictExtCountryRepository;

@Service
public class DictExtCountryService {

	@Autowired
	DictExtCountryRepository dictExtCountryRepository;
	
	public Set<DictExtCountry> findByContinentId(long id) {
		return dictExtCountryRepository.findByDictExtContinentId(id);
	}
}
