package com.mofof.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mofof.entity.dict.ext.DictExtIndustryL1;
import com.mofof.repository.DictExtIndustryL1Repository;

@Service
public class DictExtIndustryL1Service {

	@Autowired
	private DictExtIndustryL1Repository dictExtIndustryL1Repository;
	
	public Set<DictExtIndustryL1> findByIndustryId(long id) {
		return dictExtIndustryL1Repository.findByDictExtIndustryId(id);
	}
}
