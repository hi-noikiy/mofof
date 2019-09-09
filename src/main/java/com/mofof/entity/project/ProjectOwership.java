package com.mofof.entity.project;

import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by ChenErliang on 17/6/9.
 * 项目股权变更
 */
@Entity
@SQLDelete(sql = "update project_owership set del = 1 where id = ?")
@SQLDeleteAll(sql = "update project_owership set del = 1 where id = ?")
@Where(clause = "del = 0")
public class ProjectOwership extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project; //相关项目
    private LocalDate changeDate;//变更日期
    @ElementCollection
    private List<ProjectOwershipDetail> projectOwershipDetails; //详细变更明细（变更后的所有基金或投资人信息）

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public LocalDate getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDate changeDate) {
        this.changeDate = changeDate;
    }

    public List<ProjectOwershipDetail> getProjectOwershipDetails() {
        return projectOwershipDetails;
    }

    public void setProjectOwershipDetails(List<ProjectOwershipDetail> projectOwershipDetails) {
        this.projectOwershipDetails = projectOwershipDetails;
    }
}
