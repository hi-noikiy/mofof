package com.mofof.repository;

import com.mofof.entity.administrator.TeamMember;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Qian, Wenliang on 17/5/27.
 */
public interface TeamMemberRepository extends CrudRepository<TeamMember, Long> {
    Iterable<TeamMember> findAllByOrderByIdDesc();

    Iterable<TeamMember> findByIdIn(List<Long> list);


}
