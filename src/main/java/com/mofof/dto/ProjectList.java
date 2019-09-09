package com.mofof.dto;

import com.mofof.entity.fund.Fund;
import com.mofof.entity.project.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzh on 17/6/24.
 */
public class ProjectList {
    Long id; //对应投资关系的id
    Project project;

    //String[]  fundNames;//项目、简称、行业1、行业2、注册地、注册资本、相关基金
    List<Fund> funds = new ArrayList<>();

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Fund> getFunds() {
        return funds;
    }

    public void setFunds(List<Fund> funds) {
        this.funds = funds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
