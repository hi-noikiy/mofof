package com.mofof.repository;

import com.mofof.entity.dict.DictItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

/**
 * Created by ChenErliang on 2017/5/3.
 */
public interface DictItemRepository extends CrudRepository<DictItem, Long> {

    DictItem findOneByKeyName(String keyName);

    List<DictItem> findByKeyNameIn(List<String> keyNames);

    List<DictItem> findAllByOrderByIdDesc();
    
    DictItem findById(long id);
    
    Set<DictItem> findByCascadeKey(String cascadeKey);
    
    boolean existsByKeyName(String key);
    
    Set<DictItem> findByDictType(int dictType);
}
