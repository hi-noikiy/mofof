package com.mofof.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mofof.entity.dict.ext.DictExtIndustryL3;
import com.mofof.repository.DictExtIndustryL3Repository;

@Service
public class DictExtIndustryL3Service {

	@Autowired
	DictExtIndustryL3Repository dictExtIndustryL3Repository;
	
	public Set<DictExtIndustryL3> findByIndustryl2Id(long id) {
		return dictExtIndustryL3Repository.findByDictExtIndustryL2Id(id);
	}
}
