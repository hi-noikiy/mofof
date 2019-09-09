package com.mofof.entity.dict.ext;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "dict_ext_industry_l2")
@SQLDelete(sql = "update dict_ext_industry_l2 set del = 1 where id = ?")
@SQLDeleteAll(sql = "update dict_ext_industry_l2 set del = 1 where id = ?")
@Where(clause = "del = 0")
public class DictExtIndustryL2 extends BaseEntity {

	private int rank;
	private String nameCn;
	private String nameEn;
	private int insdustryCode;

	@ManyToOne(cascade = CascadeType.MERGE)
	DictExtIndustryL1 dictExtIndustryL1;

//	@OneToMany(mappedBy = "dictExtIndustryL2",cascade = CascadeType.ALL)
//	Set<DictExtIndustryL3> dictExtIndustryL3s;

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

	public int getInsdustryCode() {
		return insdustryCode;
	}

	public void setInsdustryCode(int insdustryCode) {
		this.insdustryCode = insdustryCode;
	}

	public DictExtIndustryL1 getDictExtIndustryL1() {
		return dictExtIndustryL1;
	}

	public void setDictExtIndustryL1(DictExtIndustryL1 dictExtIndustryL1) {
		this.dictExtIndustryL1 = dictExtIndustryL1;
	}

//	public Set<DictExtIndustryL3> getDictExtIndustryL3s() {
//		return dictExtIndustryL3s;
//	}
//
//	public void setDictExtIndustryL3s(Set<DictExtIndustryL3> dictExtIndustryL3s) {
//		this.dictExtIndustryL3s = dictExtIndustryL3s;
//	}

}
