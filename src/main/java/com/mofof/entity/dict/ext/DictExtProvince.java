package com.mofof.entity.dict.ext;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
				  property = "id")
@SQLDelete(sql = "update dict_ext_province set del = 1 where id = ?")
@SQLDeleteAll(sql = "update dict_ext_province set del = 1 where id = ?")
@Where(clause = "del = 0")
public class DictExtProvince extends BaseEntity {

	private String shortName;
	private String fullName;
	private String provinceCode;
	private String provinceNo;

	@ManyToOne(cascade = CascadeType.MERGE)
	DictExtCountry dictExtCountry;
	
//	@OneToMany(mappedBy = "dictExtProvince",cascade = CascadeType.ALL)
//	Set<DictExtCity> dictExtCities;

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
	
	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceNo() {
		return provinceNo;
	}

	public void setProvinceNo(String provinceNo) {
		this.provinceNo = provinceNo;
	}

	public DictExtCountry getDictExtCountry() {
		return dictExtCountry;
	}

	public void setDictExtCountry(DictExtCountry dictExtCountry) {
		this.dictExtCountry = dictExtCountry;
	}

//	public Set<DictExtCity> getDictExtCities() {
//		return dictExtCities;
//	}
//
//	public void setDictExtCities(Set<DictExtCity> dictExtCities) {
//		this.dictExtCities = dictExtCities;
//	}

}
