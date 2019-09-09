package com.mofof.repository;

import com.mofof.entity.administrator.AdministratorTeam;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by Qian, Wenliang on 2017/6/20.
 */
public interface AdministratorTeamRepository extends CrudRepository<AdministratorTeam, Long> {
    Iterable<AdministratorTeam> findByAdministratorId(Long id);

    Iterable<AdministratorTeam> findByTeamMemberId(Long id);

    @Query(nativeQuery = true,
            value = "select i.chinese_name iname,i.english_name,tm.gender,tm.age,o.chinese_name oname,tm.id " +
                    "from team_member tm left join " +
                    "(select administrator_id,max(create_time) create_time,team_member_id,id " +
                    "from administrator_team " +
                    "group by team_member_id) latestAgency left join		 " +
                    "		administrator a " +
                    "		on latestAgency.administrator_id=a.id left join " +
                    "				organization o " +
                    "				on a.organization_id=o.id " +
                    "on tm.id=latestAgency.team_member_id " +

                    "inner join individual i    " +
                    "on tm.individual_id=i.id " +

                    "where not exists	 " +
                    "(select * from administrator_team at where at.administrator_id=?1 and at.team_member_id=tm.id	)"
//					"left join administrator_team at    " +
//					"on tm.id=at.team_member_id"
                    )
    Iterable<Object[]> findTeamsNotLinkedToAdminId(Long id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO administrator_team (administrator_id, team_member_id) VALUES (?1, ?2)")
    void insertIntoAdminTeam(Long administratorId, Long memberId);
}
