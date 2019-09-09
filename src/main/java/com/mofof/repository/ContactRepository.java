package com.mofof.repository;


import com.mofof.entity.administrator.Contact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Qian, Wenliang on 17/6/13.
 */
public interface ContactRepository extends CrudRepository<Contact, Long> {
    Contact findByTeamMemberId(Long id);

    //    @Query("SELECT c from Contact c,FundAdminTeam fat where c.teamMember.id=fat.administrator.teamMember.id and fat.fund.id=?1")
    @Query(value = "SELECT c.* from contact c,fund_admin_team fat,administrator_team at where c.team_member_id=at.team_member_id and at.id=fat.administrator_team_id and fat.contact_person=1 and fat.fund_id=?1", nativeQuery = true)
    Iterable<Contact> findContactByFundId(Long fundId);
}
