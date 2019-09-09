package com.mofof.repository;


import com.mofof.entity.administrator.Professional;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Qian, Wenliang on 17/6/13.
 */
public interface ProfessionalRepository extends CrudRepository<Professional, Long> {
    Iterable<Professional> findTop2ByTeamMemberIdOrderByEndTimeDesc(Long id);

//    @Query("SELECT c from Contact c JOIN c.teamMember t WITH t.id = ?1")
//    @Query("SELECT c from Contact c where c.teamMember.id=?1")
//    List<Contact> find(Long memberId);
}
