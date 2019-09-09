package com.mofof.entity.dict.ext;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "dict_ext_industry_l3")
@SQLDelete(sql = "update dict_ext_industry_l3 set del = 1 where id = ?")
@SQLDeleteAll(sql = "update dict_ext_industry_l3 set del = 1 where id = ?")
@Where(clause = "del = 0")
public class DictExtIndustryL3 extends BaseEntity {

	private int rank;
	private String nameCn;
	private String nameEn;
	private int insdustryCode;

	@ManyToOne(cascade = CascadeType.MERGE)
	DictExtIndustryL2 dictExtIndustryL2;

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

	public DictExtIndustryL2 getDictExtIndustryL2() {
		return dictExtIndustryL2;
	}

	public void setDictExtIndustryL2(DictExtIndustryL2 dictExtIndustryL2) {
		this.dictExtIndustryL2 = dictExtIndustryL2;
	}

}
