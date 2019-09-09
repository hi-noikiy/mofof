package com.mofof.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mofof.entity.dict.DictTerm;
import com.mofof.repository.DictTermRepository;

@Service
@Transactional
public class DictTermService {
	
	@Autowired
	private DictTermRepository dictTermRepository;
	
	public List<DictTerm> findAllDictTerms() {
		return dictTermRepository.findAllByOrderByIdDesc();
	}
	
	public DictTerm findByKey(String key) {
		return dictTermRepository.findByKeyName(key);
	}
	
	public DictTerm save(DictTerm dictTerm) {
		return dictTermRepository.save(dictTerm);
	}

	public void delete(long id) {
		dictTermRepository.delete(id);
	}
	
	public Set<DictTerm> findByCascadeOption(String cascadeOption) {
		return dictTermRepository.findByCascadeOption(cascadeOption);
	}

}
