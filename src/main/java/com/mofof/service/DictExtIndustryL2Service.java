package com.mofof.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mofof.entity.dict.ext.DictExtIndustryL2;
import com.mofof.repository.DictExtIndustryL2Repository;

@Service
public class DictExtIndustryL2Service {

	@Autowired
	DictExtIndustryL2Repository dictExtIndustryL2Repository;
	
	public Set<DictExtIndustryL2> findByIndustryl1Id(long id) {
		return dictExtIndustryL2Repository.findByDictExtIndustryL1Id(id);
	}
}
