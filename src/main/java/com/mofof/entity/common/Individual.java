package com.mofof.entity.common;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;

/**
 * 个人
 */
@Entity
@SQLDelete(sql = "update individual set del = 1 where id = ?")
@SQLDeleteAll(sql = "update individual set del = 1 where id = ?")
@Where(clause = "del = 0")
public class Individual extends BaseEntity {

    private String chineseName; //中文名
    private String englishName; //英文名
    private String certificateType; //证件类别 字典
    private String certificateNum; //证件编号

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

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNum() {
        return certificateNum;
    }

    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum;
    }
}
