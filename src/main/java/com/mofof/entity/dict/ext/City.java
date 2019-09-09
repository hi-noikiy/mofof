package com.mofof.entity.dict.ext;

import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 城市
 * Created by hzh on 2018/12/20.
 */

@Entity
@Table(name = "ext_city")
@SQLDelete(sql = "update ext_city set del = 1 where id = ?")
@SQLDeleteAll(sql = "update ext_city set del = 1 where id = ?")
@Where(clause = "del = 0")
public class City extends BaseEntity {
    private String provinceNo;//省编号
    private String cityNo;//市编号
    private String cityCode;//市代码
    private String fullName;//市全称
    private String shortName;//市简称

    public String getProvinceNo() {
        return provinceNo;
    }
    public void setProvinceNo(String provinceNo) {
        this.provinceNo = provinceNo;
    }

    public String getCityNo() {
        return cityNo;
    }
    public void setCityNo(String cityNo) {
        this.cityNo = cityNo;
    }
    public String getCityCode() {
        return cityCode;
    }
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
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
