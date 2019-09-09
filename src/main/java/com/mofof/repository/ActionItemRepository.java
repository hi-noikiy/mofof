package com.mofof.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mofof.entity.system.ActionItem;

public interface ActionItemRepository extends CrudRepository<ActionItem, Long>{

  List<ActionItem> findBySysId(String sysId);

}
