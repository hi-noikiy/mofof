package com.mofof.entity.platform;

// import com.fasterxml.jackson.annotation.JsonIgnore;  //不确定用途，暂注释
import com.mofof.entity.common.BaseEntity;
import com.mofof.entity.common.Organization;
// import com.mofof.entity.system.Department;  //platform归属于那个部门管理也不在该类体现

import javax.persistence.*;
import java.util.List;

import com.mofof.entity.relation.PlatformDepartmentRelation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

/**
 * 投资平台
 */
@Entity
@ApiModel(value="投资平台")
@SQLDelete(sql = "update platform set del = 1 where id = ?")
@SQLDeleteAll(sql = "update platform set del = 1 where id = ?")
@Where(clause = "del = 0")
public class Platform extends BaseEntity {
    @ApiModelProperty(value = "对应的组织机构")
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Organization organization; //相关的组织

    //多对多关系拆成两个一对多,维护中间关系表,根据约定,统一维护多的那端关系
//    @OneToMany
//    private List<PlatformDepartmentRelation> platformDepartmentRelations;

    @ApiModelProperty(value = "类型")
    private String platformType; //出资平台类型

    @ApiModelProperty(value = "简介")
    @Lob
    @Column(columnDefinition = "text")
    private String description;//备注或简介

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}