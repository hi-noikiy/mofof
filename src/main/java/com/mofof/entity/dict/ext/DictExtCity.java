package com.mofof.entity.dict.ext;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

@Entity
@SQLDelete(sql = "update dict_ext_city set del = 1 where id = ?")
@SQLDeleteAll(sql = "update dict_ext_city set del = 1 where id = ?")
@Where(clause = "del = 0")
public class DictExtCity extends BaseEntity {

	private String shortName;
	private String fullName;
	private String cityCode;
	private String cityNo;

	@ManyToOne(cascade = CascadeType.MERGE)
	DictExtProvince dictExtProvince;

//	@OneToMany(mappedBy = "dictExtCity",cascade = CascadeType.ALL)
//	Set<DictExtDistrict> dictExtDistricts;

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityNo() {
		return cityNo;
	}

	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}

	public DictExtProvince getDictExtProvince() {
		return dictExtProvince;
	}

	public void setDictExtProvince(DictExtProvince dictExtProvince) {
		this.dictExtProvince = dictExtProvince;
	}

//	public Set<DictExtDistrict> getDictExtDistricts() {
//		return dictExtDistricts;
//	}
//
//	public void setDictExtDistricts(Set<DictExtDistrict> dictExtDistricts) {
//		this.dictExtDistricts = dictExtDistricts;
//	}

}
