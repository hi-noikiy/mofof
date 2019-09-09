package com.mofof.entity.dict.ext;

import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 省份
 * Created by hzh on 2018/12/20.
 */
@Entity
@Table(name = "ext_province")
@SQLDelete(sql = "update ext_province set del = 1 where id = ?")
@SQLDeleteAll(sql = "update ext_province set del = 1 where id = ?")
@Where(clause = "del = 0")
public class Province extends BaseEntity {
    private String provinceNo;//省编号
    private String provinceCode;//省代码
    private String fullName;//省全称
    private String shortName;//省简称

    public String getProvinceNo() {
        return provinceNo;
    }
    public void setProvinceNo(String provinceNo) {
        this.provinceNo = provinceNo;
    }
    public String getProvinceCode() {
        return provinceCode;
    }
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getShortName() {
        return shortName;
    }
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }


}
