package com.mofof.repository;

import com.mofof.entity.project.ProjectResponsePerson;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by hzh on 17/6/25.
 */
public interface ProjectResponsePersonRepository extends CrudRepository<ProjectResponsePerson, Long> {
    List<ProjectResponsePerson> findAllByProjectInvestId(Long id);
}
