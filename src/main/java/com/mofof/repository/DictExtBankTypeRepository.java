package com.mofof.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.mofof.entity.dict.ext.DictExtBankType;

public interface DictExtBankTypeRepository extends CrudRepository<DictExtBankType, Long>{

	Set<DictExtBankType> findAll();
}
