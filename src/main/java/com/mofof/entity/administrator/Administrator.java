package com.mofof.entity.administrator;

import com.mofof.entity.common.BaseEntity;
import com.mofof.entity.common.Organization;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Set;
import java.util.List;

/**
 * 管理人
 * Created by ChenErliang on 17/5/3.
 */

@ApiModel(value = "管理人")
@Entity
@SQLDelete(sql = "update administrator set del = 1 where id = ?")
@SQLDeleteAll(sql = "update administrator set del = 1 where id = ?")
@Where(clause = "del = 0")
public class Administrator extends BaseEntity {

    @ApiModelProperty(value = "相关的机构")
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Organization organization; //相关的机构

    @ApiModelProperty(value = "管理团队")
    @OneToMany
    private Set<AdministratorTeam> adminstratorTeam;

    @ApiModelProperty(value = "管理人类型")
    private String types; //管理人类型

    @ApiModelProperty(value = "网站")
    private String website; //网站

    @ApiModelProperty(value = "注册地(省)")
    private String regProvince; //注册地（省）

    @ApiModelProperty(value = "注册地(市)")
    private String regCity; //注册地（市）

    @ApiModelProperty(value = "注册地(区)")
    private String regDistrict; //注册地（区）

    @ApiModelProperty(value = "注册地址")
    private String regAddress; //注册地址

    @ApiModelProperty(value = "成立日期")
    private LocalDate foundDate; //成立日期

    @ApiModelProperty(value = "社会信用代码")
    private String uniformCode; //社会信用代码

    @ApiModelProperty(value = "备案日期")
    private LocalDate recordDate; //备案日期

    @ApiModelProperty(value = "备案号")
    private String recordCode; //备案号

    @ApiModelProperty(value = "logo")
    private String logo; //logo

    @ApiModelProperty(value = "照片列表")
    //2017/09/11 迭代 Diction 要求支持多图
    @ElementCollection
    private List<PhotoFile> photoFiles; //照片列表

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Set<AdministratorTeam> getAdminstratorTeam() {
        return adminstratorTeam;
    }

    public void setAdminstratorTeam(Set<AdministratorTeam> adminstratorTeam) {
        this.adminstratorTeam = adminstratorTeam;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getRegProvince() {
        return regProvince;
    }

    public void setRegProvince(String regProvince) {
        this.regProvince = regProvince;
    }

    public String getRegCity() {
        return regCity;
    }

    public void setRegCity(String regCity) {
        this.regCity = regCity;
    }

    public String getRegAddress() {
        return regAddress;
    }

    public void setRegAddress(String regAddress) {
        this.regAddress = regAddress;
    }

    public LocalDate getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(LocalDate foundDate) {
        this.foundDate = foundDate;
    }

    public String getUniformCode() {
        return uniformCode;
    }

    public void setUniformCode(String uniformCode) {
        this.uniformCode = uniformCode;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    public String getRecordCode() {
        return recordCode;
    }

    public void setRecordCode(String recordCode) {
        this.recordCode = recordCode;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

	public String getRegDistrict() {
		return regDistrict;
	}

	public void setRegDistrict(String regDistrict) {
		this.regDistrict = regDistrict;
	}

	public List<PhotoFile> getPhotoFiles() {
		return photoFiles;
	}

	public void setPhotoFiles(List<PhotoFile> photoFiles) {
		this.photoFiles = photoFiles;
	}
    
    
}
