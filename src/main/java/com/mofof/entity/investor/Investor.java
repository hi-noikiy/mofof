package com.mofof.entity.investor;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import com.mofof.entity.common.BaseEntity;
import com.mofof.entity.common.Individual;
import com.mofof.entity.common.Organization;
import com.mofof.entity.system.Department;

/**
 * 投资人
 */
@Entity
@SQLDelete(sql = "update investor set del = 1 where id = ?")
@SQLDeleteAll(sql = "update investor set del = 1 where id = ?")
@Where(clause = "del = 0")
public class Investor extends BaseEntity {
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Organization organization; //相关的组织
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Individual individual; //相关的个人
    private int type; //0-个人 1-组织（境内）2-组织（境外）
    private boolean platform; //是否出资平台
    @Lob
    @Column(columnDefinition = "text")
    private String note;//备注或简介

//    @ManyToMany(mappedBy = "investors")
//    @JsonIgnore
    @Transient
    private List<Department> departmentList;

    @Transient
    public boolean isManagedByDept;


    @Transient
    public String getChineseName() {
        if (type == 0 && individual != null) {
            return individual.getChineseName();
        } else if (type == 1 && organization != null) {
            return organization.getChineseName();
        } else {
            return null;
        }
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Individual getIndividual() {
        return individual;
    }

    public void setIndividual(Individual individual) {
        this.individual = individual;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean getPlatform() {
        return platform;
    }

    public void setPlatform(boolean platform) {
        this.platform = platform;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public boolean isManagedByDept() {
        return isManagedByDept;
    }

    public void setManagedByDept(boolean isManagedByDept) {
        this.isManagedByDept = isManagedByDept;
    }

}