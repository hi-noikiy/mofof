package com.mofof.service;

import com.mofof.dto.ProjectList;
import com.mofof.entity.fund.Fund;
import com.mofof.entity.project.Project;
import com.mofof.entity.project.ProjectInvest;
import com.mofof.entity.project.ProjectResponsePerson;
import com.mofof.entity.relation.InvestRelation;
import com.mofof.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hzh on 17/6/23.
 */
@Service
@Transactional
public class ProjectService {
    @Autowired
    InvestRelationRepository investRelationRepository;
    @Autowired
    ProjectInvestRepository projectInvestRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ProjectResponsePersonRepository projectResponsePersonRepository;
    @Autowired
    FundRepository fundRepository;


    public List<Project> projects(){
        return projectRepository.findAll();
    }

    //这是以项目基础的列表显示，多条同项目的投资关系将被合并(有bug 启用需先修改bug)
//    public List<ProjectList> findAllProjects(){
//        Iterable<InvestRelation> irs = investRelationRepository.findAllByInvestorPlatformOrderByIdDesc(true);
//        Set<Fund> funds = new LinkedHashSet<>();
//        irs.forEach( ir ->{
//            funds.add(ir.getFund());
//        });
//
//        //List<ProjectInvest> pis = new ArrayList<>();
//        List<ProjectList> pls = new ArrayList<ProjectList>();
//
//        funds.forEach(fund->{
//            Iterable<ProjectInvest> pis = projectInvestRepository.findAllByFundId(fund.getId());
//            pis.forEach(pi->{
//                ProjectList pl = new ProjectList();
//                pl.setProject(pi.getProject());
//                pl.getFunds().add(fund);
//                pl.setId(pi.getId());
//                pls.add(pl);
//            });
//        });
//
//        return pls;
//    }

    //这是以投资关系为列表显示，多条项目会应不同投资关系多次显示
    public List<ProjectList> findAllProjects() {
        Iterable<InvestRelation> irs = investRelationRepository.findAllByInvestorPlatformOrderByIdDesc(true);
        Set<Fund> funds = new LinkedHashSet<>();
        irs.forEach(ir -> {
            funds.add(ir.getFund());
        });

        //List<ProjectInvest> pis = new ArrayList<>();
        List<ProjectList> pls = new ArrayList<ProjectList>();

        funds.forEach(fund -> {
            Iterable<ProjectInvest> pis = projectInvestRepository.findAllByFundId(fund.getId());
            pis.forEach(pi -> {
                ProjectList pl = new ProjectList();
                pl.setProject(pi.getProject());
                pl.getFunds().add(fund);
                pl.setId(pi.getId());
                pls.add(pl);
            });
        });

        return pls;
    }

    public Project findOne(Long id) {
        return projectRepository.findOne(id);
    }

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public ProjectInvest saveProjectInvest(ProjectInvest projectInvest) {
        projectRepository.save(projectInvest.getProject());

        projectInvest.setInvestAmount(BigDecimal.ZERO);
        projectInvest.setInvestStatus(ProjectInvest.INVEST_STATUS_BEFORE);
        //projectInvest.setProject(project);

        return projectInvestRepository.save(projectInvest);
    }

    public Project saveProjectAndProjectInvest(Project project, Long fundId) {
        Fund fund = fundRepository.findOne(fundId);
        ProjectInvest pi = new ProjectInvest();
        pi.setFund(fund);
        pi.setInvestAmount(BigDecimal.ZERO);
        pi.setInvestStatus(ProjectInvest.INVEST_STATUS_BEFORE);
        pi.setProject(project);

        projectInvestRepository.save(pi);
        return projectRepository.save(project);
    }

    public Iterable<ProjectInvest> findProjectInvests(Long id) {
        return projectInvestRepository.findAllByFundId(id);
    }

    public ProjectInvest findProjectInvest(Long id) {
        return projectInvestRepository.findOne(id);
    }

    public ProjectResponsePerson findProjectResponsePerson(Long id) {
        return projectResponsePersonRepository.findOne(id);
    }

    public List<ProjectResponsePerson> findAllProjectResponsePersons(Long projectInvestId) {
        return projectResponsePersonRepository.findAllByProjectInvestId(projectInvestId);
    }

    public void deleteProjectResponsePerson(Long id) {
        projectResponsePersonRepository.delete(id);
    }

    public ProjectResponsePerson saveProjectResponsePerson(ProjectResponsePerson projectResponsePerson) {
        return projectResponsePersonRepository.save(projectResponsePerson);
    }

    public void saveProjectResponsePersons(List<ProjectResponsePerson> prps) {
        if (prps != null) {
            System.err.println(prps.size());
            prps.forEach(prp -> {
                if (prp.getId() != null) {
                    this.deleteProjectResponsePerson(prp.getId());
                } else {
                    this.saveProjectResponsePerson(prp);
                }

            });
        }
        ;
    }
}
