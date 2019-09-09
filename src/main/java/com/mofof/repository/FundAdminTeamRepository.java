package com.mofof.repository;

import com.mofof.entity.fund.FundAdminTeam;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Qian, Wenliang on 2017/6/20.
 */
public interface FundAdminTeamRepository extends CrudRepository<FundAdminTeam, Long> {
    Iterable<FundAdminTeam> findByFundId(Long id);
    Integer deleteByAdministratorTeamId(Long id);
}
