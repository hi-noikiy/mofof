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
@SQLDelete(sql = "update dict_ext_country set del = 1 where id = ?")
@SQLDeleteAll(sql = "update dict_ext_country set del = 1 where id = ?")
@Where(clause = "del = 0")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
				  property = "id")
public class DictExtCountry extends BaseEntity {

	private int rank;
	private String nameCn;
	private String nameEn;

	@ManyToOne(cascade = CascadeType.MERGE)
	DictExtContinent dictExtContinent;

//	@OneToMany(mappedBy = "dictExtCountry",cascade = CascadeType.ALL)
//	Set<DictExtProvince> dixtDictExtProvinces;

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getNameCn() {
		return nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public DictExtContinent getDictExtContinent() {
		return dictExtContinent;
	}

	public void setDictExtContinent(DictExtContinent dictExtContinent) {
		this.dictExtContinent = dictExtContinent;
	}

//	public Set<DictExtProvince> getDixtDictExtProvinces() {
//		return dixtDictExtProvinces;
//	}
//
//	public void setDixtDictExtProvinces(Set<DictExtProvince> dixtDictExtProvinces) {
//		this.dixtDictExtProvinces = dixtDictExtProvinces;
//	}

}
