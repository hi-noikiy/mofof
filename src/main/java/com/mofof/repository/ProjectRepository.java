package com.mofof.repository;

import com.mofof.entity.project.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by hzh on 17/6/23.
 */
public interface ProjectRepository extends CrudRepository<Project, Long> {
    public List<Project> findAll();
}
