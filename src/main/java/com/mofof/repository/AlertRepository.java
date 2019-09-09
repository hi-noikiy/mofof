package com.mofof.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.mofof.entity.mywork.Alert;

public interface AlertRepository extends CrudRepository<Alert, Long>{

	Set<Alert> findAll();

	Alert findByCalEventId(long id);
	
	void deleteByCalEventId(long id);
}
