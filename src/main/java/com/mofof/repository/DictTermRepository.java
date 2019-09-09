package com.mofof.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.mofof.entity.dict.DictTerm;

public interface DictTermRepository extends CrudRepository<DictTerm, Long> {
	
	List<DictTerm> findAllByOrderByIdDesc();
	
	DictTerm findByKeyName(String keyName);
	
	boolean existsByKeyName(String string);
	
	Set<DictTerm> findByCascadeOption(String cascadeOption);

}
