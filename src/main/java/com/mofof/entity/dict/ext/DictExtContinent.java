package com.mofof.entity.dict.ext;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
@SQLDelete(sql = "update dict_ext_continent set del = 1 where id = ?")
@SQLDeleteAll(sql = "update dict_ext_continent set del = 1 where id = ?")
@Where(clause = "del = 0")
public class DictExtContinent extends BaseEntity {
	private int rank;
	private String nameCn;
	private String nameEn;

//	@OneToMany(mappedBy = "dictExtContinent",cascade = CascadeType.ALL)
//	Set<DictExtCountry> dictExtCountries;

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

//	public Set<DictExtCountry> getDictExtCountries() {
//		return dictExtCountries;
//	}
//
//	public void setDictExtCountries(Set<DictExtCountry> dictExtCountries) {
//		this.dictExtCountries = dictExtCountries;
//	}

}
