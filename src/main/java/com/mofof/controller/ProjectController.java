package com.mofof.controller;

import com.alibaba.fastjson.JSON;
import com.mofof.dto.ProjectList;
import com.mofof.entity.project.Project;
import com.mofof.entity.project.ProjectInvest;
import com.mofof.entity.project.ProjectResponsePerson;
import com.mofof.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hzh on 17/6/23.
 */
@RestController
@RequestMapping(path = "/project")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @GetMapping(path = "/all")
    public List<ProjectList> allProjectList() {
        return projectService.findAllProjects();
    }

    @GetMapping(path = "/project-invests")
    public Iterable<ProjectInvest> projectInvests(Long fundId) {
        return projectService.findProjectInvests(fundId);
    }

    @GetMapping(path = "/project")
    public Project project(Long id) {
        return projectService.findOne(id);
    }

    /**
     * 获取所有project,不附带任何筛选参数
     * @return
     */
    @GetMapping(path = "/projects")
    public List<Project> projects() {
        return projectService.projects();
    }

    @PostMapping(path = "/save")
    public Project save(@RequestBody Project project) {
        return projectService.save(project);
    }

    @PostMapping(path = "/save-project-invest")
    public ProjectInvest saveProjectInvest(@RequestBody ProjectInvest projectInvest) {
        return projectService.saveProjectInvest(projectInvest);
    }

    @GetMapping(path = "/project-invest")
    public ProjectInvest projectInvest(Long id) {
        return projectService.findProjectInvest(id);
    }

    @GetMapping(path = "/project-response-persons")
    public List<ProjectResponsePerson> projectResponsePersons(Long id) {
        return projectService.findAllProjectResponsePersons(id);
    }

    @GetMapping(path = "/project-response-person")
    public ProjectResponsePerson projectResponsePerson(Long id) {
        return projectService.findProjectResponsePerson(id);
    }

    @PostMapping(path = "/save-project-response-persons")
    public String saveProjectResponsePersons(@RequestBody List<ProjectResponsePerson> prps) {
        System.out.print(JSON.toJSONString(prps));

        projectService.saveProjectResponsePersons(prps);
        return " ";
    }
}
