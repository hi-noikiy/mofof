package com.mofof.entity.common;

import javax.persistence.Embeddable;
import javax.persistence.Lob;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 证照信息
 * Created by ChenErliang on 17/4/12.
 */
@Embeddable
public class License {

    private String licenseName; //全称
    private String uniformCode; //统一社会信用代码
    private String type; //类型
    private String artificialPerson; //法人代表
    private String regCapital; //注册资本
    private LocalDate foundDate; //成立日期
    private String regAddress; //注册地址
    private LocalDate businessStartDate; //经营期限(起)
    private LocalDate businessEndDate; //经营期限(止)
    @Lob
    private String businessScope; //经营范围
    private String regAuthority; //登记机关
    private String regStatus; //登记状态
    private LocalDate approvalDate; //核准日期
    private LocalDate revokeDate; //吊销日期
    private LocalDateTime lastUpdateTime;//最后更新时间

    public String getLicenseName() {
        return licenseName;
    }

    public void setLicenseName(String licenseName) {
        this.licenseName = licenseName;
    }

    public String getUniformCode() {
        return uniformCode;
    }

    public void setUniformCode(String uniformCode) {
        this.uniformCode = uniformCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArtificialPerson() {
        return artificialPerson;
    }

    public void setArtificialPerson(String artificialPerson) {
        this.artificialPerson = artificialPerson;
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

    public String getRegAddress() {
        return regAddress;
    }

    public void setRegAddress(String regAddress) {
        this.regAddress = regAddress;
    }

    public LocalDate getBusinessStartDate() {
        return businessStartDate;
    }

    public void setBusinessStartDate(LocalDate businessStartDate) {
        this.businessStartDate = businessStartDate;
    }

    public LocalDate getBusinessEndDate() {
        return businessEndDate;
    }

    public void setBusinessEndDate(LocalDate businessEndDate) {
        this.businessEndDate = businessEndDate;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getRegAuthority() {
        return regAuthority;
    }

    public void setRegAuthority(String regAuthority) {
        this.regAuthority = regAuthority;
    }

    public String getRegStatus() {
        return regStatus;
    }

    public void setRegStatus(String regStatus) {
        this.regStatus = regStatus;
    }

    public LocalDate getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDate approvalDate) {
        this.approvalDate = approvalDate;
    }

    public LocalDate getRevokeDate() {
        return revokeDate;
    }

    public void setRevokeDate(LocalDate revokeDate) {
        this.revokeDate = revokeDate;
    }

    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
