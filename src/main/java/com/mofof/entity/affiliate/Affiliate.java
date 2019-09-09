package com.mofof.entity.affiliate;

import com.mofof.entity.common.BaseEntity;
import com.mofof.entity.common.Organization;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * 关联机构
 * Created by ChenErliang on 17/4/12.
 */
@Entity
@SQLDelete(sql = "update affiliate set del = 1 where id = ?")
@SQLDeleteAll(sql = "update affiliate set del = 1 where id = ?")
@Where(clause = "del = 0")
public class Affiliate extends BaseEntity {

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    private Organization organization; //组织机构
    private String organizeForm; // 组织形式
    private String institutionProperty; //机构性质
    private String currency; //币种 字典

    private String regProvince; // 注册省
    private String regCity; // 注册市
    private String regDistrict; // 注册区
    private String regAddress; //注册地址
    private String regCapital; // 注册资本
    private String officeAddress; //办公地址
    private LocalDate foundDate; // 成立日期

    private String uniformCode; //社会信用代码
    private String recordCode; //协会备案号
    private LocalDate recordDate;//备案日期
    @Lob
    @Column(columnDefinition = "text")
    private String note; //备注


    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
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

    public String getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(String regCapital) {
        this.regCapital = regCapital;
    }

    public LocalDate getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(LocalDate foundDate) {
        this.foundDate = foundDate;
    }

    public String getOrganizeForm() {
        return organizeForm;
    }

    public void setOrganizeForm(String organizeForm) {
        this.organizeForm = organizeForm;
    }

    public String getUniformCode() {
        return uniformCode;
    }

    public void setUniformCode(String uniformCode) {
        this.uniformCode = uniformCode;
    }

    public String getRegAddress() {
        return regAddress;
    }

    public void setRegAddress(String regAddress) {
        this.regAddress = regAddress;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getInstitutionProperty() {
        return institutionProperty;
    }

    public void setInstitutionProperty(String institutionProperty) {
        this.institutionProperty = institutionProperty;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRecordCode() {
        return recordCode;
    }

    public void setRecordCode(String recordCode) {
        this.recordCode = recordCode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }
}
