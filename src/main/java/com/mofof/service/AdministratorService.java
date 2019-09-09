package com.mofof.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mofof.entity.administrator.Administrator;
import com.mofof.entity.administrator.AdministratorTeam;
import com.mofof.entity.administrator.TeamMemberWithAgency;
import com.mofof.entity.fund.Fund;
import com.mofof.repository.AdministratorRepository;
import com.mofof.repository.AdministratorTeamRepository;
import com.mofof.repository.FundAdminTeamRepository;
import com.mofof.repository.FundRepository;

/**
 * Created by ChenErliang on 17/6/6.
 */
@Service
@Transactional
public class AdministratorService {
    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private AdministratorTeamRepository administratorTeamRepository;

    @Autowired
    private FundRepository fundRepository;
    
    @Autowired
    private FundAdminTeamRepository fundAdminTeamRepository;

    public Administrator save(Administrator administrator) {
        return administratorRepository.save(administrator);
    }

    public Iterable<Administrator> findAll() {
        return administratorRepository.findAllByOrderByIdDesc();
    }

    public Administrator findById(Long id) {
        return administratorRepository.findOne(id);
    }

    public Iterable<AdministratorTeam> findTeamsByAdminId(Long id) {
        return administratorTeamRepository.findByAdministratorId(id);
    }

    public Iterable<TeamMemberWithAgency> findTeamsNotLinkedToAdminId(Long id) {
    	List<TeamMemberWithAgency> memberAgency = new ArrayList<TeamMemberWithAgency>();
    	//2017/09/01 迭代要求，添加成员列表要同时显示已经link和可供link的所有团队成员
        Iterable<AdministratorTeam> administratorTeams = administratorTeamRepository.findByAdministratorId(id);
        for(AdministratorTeam administratorTeam:administratorTeams){
        	TeamMemberWithAgency ta = new TeamMemberWithAgency();
            ta.setChineseName(administratorTeam.getTeamMember().getIndividual().getChineseName());
            ta.setEnglishName(administratorTeam.getTeamMember().getIndividual().getEnglishName());
            ta.setGender(administratorTeam.getTeamMember().getGender());
            ta.setAge(administratorTeam.getTeamMember().getAge());
            ta.setAgency(administratorTeam.getAdministrator().getOrganization().getChineseName());
            ta.setId(new BigInteger(administratorTeam.getTeamMember().getId().toString()));
            ta.setExistedTeamMember(true);
            ta.setDisabledCheckbox(true);
            ta.setPositionDesc(administratorTeam.getPositionDesc());
            memberAgency.add(ta);
        }
    	Iterable<Object[]> ret = administratorTeamRepository.findTeamsNotLinkedToAdminId(id);
        for (Object[] obj : ret) {
            TeamMemberWithAgency ta = new TeamMemberWithAgency();
            ta.setChineseName((String) obj[0]);
            ta.setEnglishName((String) obj[1]);
            ta.setGender((String) obj[2]);
            ta.setAge((Integer) obj[3]);
            ta.setAgency((String) obj[4]);
            ta.setId((BigInteger) obj[5]);
            memberAgency.add(ta);
        }
        return memberAgency;
    }

    public AdministratorTeam findTeamById(Long id) {
        return administratorTeamRepository.findOne(id);
    }

    public void saveAdministratorTeams(AdministratorTeam[][] administratorTeams) {
        List<AdministratorTeam> mList = Arrays.asList(administratorTeams[0]);
        List<AdministratorTeam> dList = Arrays.asList(administratorTeams[1]);
        administratorTeamRepository.delete(dList);
        administratorTeamRepository.save(mList);
    }
    
    // public void saveEditTeams(AdministratorTeam[] editTeams) {
    //     administratorTeamRepository.save(Arrays.asList(editTeams));
    // }

    public Long addAdministratorTeams(AdministratorTeam[] administratorTeams) {
        Long adminId = 0L;
        for (AdministratorTeam at : administratorTeams) {
            administratorTeamRepository.insertIntoAdminTeam(at.getAdministrator().getId(), at.getTeamMember().getId());
            adminId = at.getAdministrator().getId();
        }
        return adminId;
    }

    public Long addAdministratorGroups(Fund[] funds) {
        Long adminId = 0L;
        List<Fund> fundList = Arrays.asList(funds);
        Iterable<Fund> retList = fundRepository.save(fundList);
        for (Fund fund : retList) {
            adminId = fund.getAdministrator().getId();
            break;
        }
        return adminId;
    }

    public Iterable<Fund> getFundByAdministratorId(Long id) {
        return fundRepository.findByAdministratorId(id);
    }

    public Iterable<Fund> getFundByFundName(String name) {
        return fundRepository.findByOrganizationChineseNameLikeIgnoreCase(name);
    }

    public Iterable<Fund> getFundNotLinkedToAnyAdmin() {
        return fundRepository.getFundNotLinkedToAnyAdmin();
    }

    public Long delinkAdminGroup(Long id) {
        Fund retfund = fundRepository.findOne(id);
        fundRepository.delinkAdminGroup(id);
        return retfund.getAdministrator().getId();
    }
    
    public void delinkAdminTeam(Long id) {
//        AdministratorTeam administratorTeam = administratorTeamRepository.findOne(id);
    	fundAdminTeamRepository.deleteByAdministratorTeamId(id);
        administratorTeamRepository.delete(id);
    }
}
