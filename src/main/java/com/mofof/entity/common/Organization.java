package com.mofof.entity.common;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.Embedded;
import javax.persistence.Entity;

/**
 * 组织机构
 */
@Entity
@SQLDelete(sql = "update organization set del = 1 where id = ?")
@SQLDeleteAll(sql = "update organization set del = 1 where id = ?")
@Where(clause = "del = 0")
public class Organization extends BaseEntity {

    private String chineseName; //中文简称
    private String englishName; //英文简称
    private String fullName; //组织机构全称
    private String organizationType;//机构类型
    @Embedded
    private License license;//证照信息

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    public String getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType;
    }

}
