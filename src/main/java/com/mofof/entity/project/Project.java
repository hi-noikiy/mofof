package com.mofof.entity.project;

import com.mofof.entity.common.BaseEntity;
import com.mofof.entity.common.Organization;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by ChenErliang on 17/6/9.
 * 项目
 */
@Entity
@SQLDelete(sql = "update project set del = 1 where id = ?")
@SQLDeleteAll(sql = "update project set del = 1 where id = ?")
@Where(clause = "del = 0")
public class Project extends BaseEntity {
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Organization organization; //相应的组织机构
    private String companyType; //公司类型 字典
    private String aliasName; //项目别称
    private String website; //网站
    private String regCapital; //注册资本
    private String currency; //币种 字典
    private String artificialPerson; //法定代表人
    private String projectSource; //项目来源
    private String projectStatus; //项目状态 字典
    private String listedPlace; //挂牌上市地 字典
    private String stockCode; //股票代码
    private String firstCategory1; //一级行业分类1 字典
    private String secondCategory1;//二级行业分类1 字典
    private String firstCategory2;//一级行业分类2 字典
    private String secondCategory2;//二级行业分类2 字典
    private String thirdCategory2;//三级行业分类2 字典
    private String businessProvince; //运营地（省） 字典
    private String businessCity; //运营地（市） 字典
    private String businessDistrict; //运营地（区） 字典
    private String regProvince; //注册地（省） 字典
    private String regCity; //注册地（市） 字典
    private String regDistrict; //注册地（区） 字典
    private String regAddress; //注册地址
    private LocalDate foundDate; //成立日期
    private String uniformCode; //社会信用代码
    @Lob
    @Column(columnDefinition = "text")
    private String description; //企业介绍

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(String regCapital) {
        this.regCapital = regCapital;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getArtificialPerson() {
        return artificialPerson;
    }

    public void setArtificialPerson(String artificialPerson) {
        this.artificialPerson = artificialPerson;
    }

    public String getProjectSource() {
        return projectSource;
    }

    public void setProjectSource(String projectSource) {
        this.projectSource = projectSource;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getListedPlace() {
        return listedPlace;
    }

    public void setListedPlace(String listedPlace) {
        this.listedPlace = listedPlace;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getFirstCategory1() {
        return firstCategory1;
    }

    public void setFirstCategory1(String firstCategory1) {
        this.firstCategory1 = firstCategory1;
    }

    public String getSecondCategory1() {
        return secondCategory1;
    }

    public void setSecondCategory1(String secondCategory1) {
        this.secondCategory1 = secondCategory1;
    }

    public String getFirstCategory2() {
        return firstCategory2;
    }

    public void setFirstCategory2(String firstCategory2) {
        this.firstCategory2 = firstCategory2;
    }

    public String getSecondCategory2() {
        return secondCategory2;
    }

    public void setSecondCategory2(String secondCategory2) {
        this.secondCategory2 = secondCategory2;
    }

    public String getThirdCategory2() {
        return thirdCategory2;
    }

    public void setThirdCategory2(String thirdCategory2) {
        this.thirdCategory2 = thirdCategory2;
    }

    public String getBusinessProvince() {
        return businessProvince;
    }

    public void setBusinessProvince(String businessProvince) {
        this.businessProvince = businessProvince;
    }

    public String getBusinessCity() {
        return businessCity;
    }

    public void setBusinessCity(String businessCity) {
        this.businessCity = businessCity;
    }

    public String getBusinessDistrict() {
        return businessDistrict;
    }

    public void setBusinessDistrict(String businessDistrict) {
        this.businessDistrict = businessDistrict;
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

    public String getRegDistrict() {
        return regDistrict;
    }

    public void setRegDistrict(String regDistrict) {
        this.regDistrict = regDistrict;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
