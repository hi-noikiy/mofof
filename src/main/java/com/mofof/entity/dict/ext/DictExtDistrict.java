package com.mofof.entity.dict.ext;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

@Entity
@SQLDelete(sql = "update dict_ext_district set del = 1 where id = ?")
@SQLDeleteAll(sql = "update dict_ext_district set del = 1 where id = ?")
@Where(clause = "del = 0")
public class DictExtDistrict extends BaseEntity {

	private String shortName;
	private String fullName;
	private String districtCode;
	private String districtNo;

	@ManyToOne(cascade = CascadeType.MERGE)
	DictExtCity dictExtCity;

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

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictNo() {
		return districtNo;
	}

	public void setDistrictNo(String districtNo) {
		this.districtNo = districtNo;
	}

	public DictExtCity getDictExtCity() {
		return dictExtCity;
	}

	public void setDictExtCity(DictExtCity dictExtCity) {
		this.dictExtCity = dictExtCity;
	}

}
