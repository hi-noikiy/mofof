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
@Table(name = "dict_ext_industry_l1")
@SQLDelete(sql = "update dict_ext_industry_l1 set del = 1 where id = ?")
@SQLDeleteAll(sql = "update dict_ext_industry_l1 set del = 1 where id = ?")
@Where(clause = "del = 0")
public class DictExtIndustryL1 extends BaseEntity {

	private int rank;
	private String nameCn;
	private String nameEn;
	private int insdustryCode;

	@ManyToOne(cascade = CascadeType.MERGE)
	DictExtIndustry dictExtIndustry;

//	@OneToMany(mappedBy = "dictExtIndustryL1",cascade = CascadeType.ALL)
//	Set<DictExtIndustryL2> dictExtIndustryL2s;

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

	public DictExtIndustry getDictExtIndustry() {
		return dictExtIndustry;
	}

	public void setDictExtIndustry(DictExtIndustry dictExtIndustry) {
		this.dictExtIndustry = dictExtIndustry;
	}

//	public Set<DictExtIndustryL2> getDictExtIndustryL2s() {
//		return dictExtIndustryL2s;
//	}
//
//	public void setDictExtIndustryL2s(Set<DictExtIndustryL2> dictExtIndustryL2s) {
//		this.dictExtIndustryL2s = dictExtIndustryL2s;
//	}

}
