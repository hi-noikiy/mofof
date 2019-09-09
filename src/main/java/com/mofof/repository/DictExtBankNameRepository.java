package com.mofof.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.mofof.entity.dict.ext.DictExtBankName;

public interface DictExtBankNameRepository extends CrudRepository<DictExtBankName, Long>{

	Set<DictExtBankName> findAllByDictExtBankTypeId(long id);
}
