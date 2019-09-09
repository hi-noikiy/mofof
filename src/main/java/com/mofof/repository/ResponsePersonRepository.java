package com.mofof.repository;

import com.mofof.entity.relation.ResponsePerson;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by hzh on 17/7/15.
 */
public interface ResponsePersonRepository extends CrudRepository<ResponsePerson, Long> {
    List<ResponsePerson> findAllByInvestRelationId(Long id);
}
