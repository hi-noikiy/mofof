package com.mofof.repository;

import com.mofof.entity.project.ProjectInvest;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by hzh on 17/6/25.
 */
public interface ProjectInvestRepository extends CrudRepository<ProjectInvest, Long> {

    Iterable<ProjectInvest> findAllByFundId(Long id);

    //List<ProjectInvest> projectInvests ();
}
