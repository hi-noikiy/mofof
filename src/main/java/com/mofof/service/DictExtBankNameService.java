package com.mofof.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mofof.entity.dict.ext.DictExtBankName;
import com.mofof.repository.DictExtBankNameRepository;

@Service
public class DictExtBankNameService {

	@Autowired
	private DictExtBankNameRepository dictExtBankNameRepository;
	
	public Set<DictExtBankName> findAllByBankTypeId(long id) {
		return dictExtBankNameRepository.findAllByDictExtBankTypeId(id);
	}
}
