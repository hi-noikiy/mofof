package com.mofof.entity.dict.ext;

import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 区
 * Created by hzh on 2018/12/20.
 */
@Entity
@Table(name = "ext_district")
@SQLDelete(sql = "update ext_district set del = 1 where id = ?")
@SQLDeleteAll(sql = "update ext_district set del = 1 where id = ?")
@Where(clause = "del = 0")
public class District extends BaseEntity {
    private String cityNo;//市编号
    private String districtNo;//区编码
    private String districtCode;//区代码
    private String fullName;//全称
    private String shortName;//简称

    public String getDistrictNo() {
        return districtNo;
    }

    public void setDistrictNo(String districtNo) {
        this.districtNo = districtNo;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getCityNo() {
        return cityNo;
    }
    public void setCityNo(String cityNo) {
        this.cityNo = cityNo;
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
