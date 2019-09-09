package com.mofof.service;

import com.mofof.entity.administrator.*;
import com.mofof.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Qian, Wenliang on 17/5/27.
 */
@Service
@Transactional
public class TeamMemberService {

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ProfessionalRepository professionalRepository;

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private AdministratorTeamRepository administratorTeamRepository;

    public TeamMember save(TeamMember teamMember) {
        return teamMemberRepository.save(teamMember);
    }

    public TeamMember updateDescription(Long id, String description) {
        TeamMember tm = teamMemberRepository.findOne(id);
        tm.setDescription(description);
        return teamMemberRepository.save(tm);
    }

    public Iterable<TeamMember> findAll() {
        return teamMemberRepository.findAllByOrderByIdDesc();
    }

    public TeamMember findById(Long id) {
        return teamMemberRepository.findOne(id);
    }

    public Contact findContactById(Long id) {
        return contactRepository.findByTeamMemberId(id);
    }

    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public Iterable<Professional> findProfessionalById(Long id) {
        return professionalRepository.findTop2ByTeamMemberIdOrderByEndTimeDesc(id);
    }

    public Iterable<Professional> saveProfessionals(Professional[] professionals) {
        List<Professional> pList = Arrays.asList(professionals);
        Iterable<Professional> retList = professionalRepository.save(pList);
        Long memberId = 0L;
        for (Professional p : retList) {
            memberId = p.getTeamMember().getId();
            break;
        }
        return professionalRepository.findTop2ByTeamMemberIdOrderByEndTimeDesc(memberId);
    }

    public Iterable<Education> findEducationById(Long id) {
        return educationRepository.findTop2ByTeamMemberIdOrderByEndTimeDesc(id);
    }

    public Iterable<AdministratorTeam> findAgencyById(Long id) {
        return administratorTeamRepository.findByTeamMemberId(id);
    }

    public Iterable<Education> saveEducations(Education[] educations) {
        List<Education> pList = Arrays.asList(educations);
        Iterable<Education> retList = educationRepository.save(pList);
        Long memberId = 0L;
        for (Education p : retList) {
            memberId = p.getTeamMember().getId();
            break;
        }
        return educationRepository.findTop2ByTeamMemberIdOrderByEndTimeDesc(memberId);
    }
}
