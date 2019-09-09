package com.mofof.repository;


import com.mofof.entity.administrator.Education;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Qian, Wenliang on 17/6/21.
 */
public interface EducationRepository extends CrudRepository<Education, Long> {
    Iterable<Education> findTop2ByTeamMemberIdOrderByEndTimeDesc(Long id);

}
