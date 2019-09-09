package com.mofof.entity.fund;

import com.mofof.entity.administrator.Administrator;
import com.mofof.entity.common.BaseEntity;
import com.mofof.entity.common.Organization;
import com.mofof.entity.relation.InvestRelation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * 基金
 * Created by ChenErliang on 17/4/12.
 */
@Entity
@ApiModel(value = "基金")
@SQLDelete(sql = "update fund set del = 1 where id = ?")
@SQLDeleteAll(sql = "update fund set del = 1 where id = ?")
@Where(clause = "del = 0")
public class Fund extends BaseEntity {
    @ApiModelProperty(value = "对应的组织机构")
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Organization organization;//对应的组织机构
    
    @ApiModelProperty(value = "管理人")
    @ManyToOne
    private Administrator administrator; //管理人

    @ApiModelProperty(value = "基金管理团队")
    @OneToMany
    private Set<FundAdminTeam> fundAdminTeams;

    @ApiModelProperty(value = "投资主体")
    @OneToMany
    private Set<InvestRelation> investRelations;

    @ApiModelProperty(value = "基金类型")
    private String fundType; //基金类型
    @ApiModelProperty(value = "币种")
    private String currency; //币种
    @ApiModelProperty(value = "基金规模")
    private String fundAmount; //基金规模
    @ApiModelProperty(value = "组织形式")
    private String organizeForm; //组织形式
    @ApiModelProperty(value = "机构性质")
    private String institutionProperty; //机构性质
    @ApiModelProperty(value = "投资状态")
    private String investStatus;
    @ApiModelProperty(value = "基金状态")
    private String fundStatus; //基金状态
    @ApiModelProperty(value = "省")
    private String regProvince; //省
    @ApiModelProperty(value = "市")
    private String regCity;  //市
    @ApiModelProperty(value = "区")
    private String regDistrict;  //区
    @ApiModelProperty(value = "注册地址")
    private String regAddress;
    @ApiModelProperty(value = "运营地址")
    private String officeAddress;
    @ApiModelProperty(value = "成立日期")
    private LocalDate foundDate;
    @ApiModelProperty(value = "组织代码")
    private String uniformCode;
    @ApiModelProperty(value = "登记日期")
    private LocalDate recordDate;
    @ApiModelProperty(value = "登记代码")
    private String recordCode;
    @ApiModelProperty(value = "备注")
    @Lob
    @Column(columnDefinition = "text")
    private String note; //备注

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Set<InvestRelation> getInvestRelations() {
        return this.investRelations;
    }

    public void setInvestRelations(Set<InvestRelation> investRelations) {
        this.investRelations = investRelations;
    }

    public Set<FundAdminTeam> getFundAdminTeams() {
        return this.fundAdminTeams;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getFundAmount() {
        return fundAmount;
    }

    public void setFundAmount(String fundAmount) {
        this.fundAmount = fundAmount;
    }

    public String getOrganizeForm() {
        return organizeForm;
    }

    public void setOrganizeForm(String organizeForm) {
        this.organizeForm = organizeForm;
    }

    public String getInstitutionProperty() {
        return institutionProperty;
    }

    public void setInstitutionProperty(String institutionProperty) {
        this.institutionProperty = institutionProperty;
    }

    public String getInvestStatus() {
        return investStatus;
    }

    public void setInvestStatus(String investStatus) {
        this.investStatus = investStatus;
    }

    public String getFundStatus() {
        return fundStatus;
    }

    public void setFundStatus(String fundStatus) {
        this.fundStatus = fundStatus;
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

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRegDistrict() {
        return regDistrict;
    }

    public void setRegDistrict(String regDistrict) {
        this.regDistrict = regDistrict;
    }
}
