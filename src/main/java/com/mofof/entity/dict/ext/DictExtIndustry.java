package com.mofof.entity.dict.ext;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

@Entity
@SQLDelete(sql = "update dict_ext_industry set del = 1 where id = ?")
@SQLDeleteAll(sql = "update dict_ext_industry set del = 1 where id = ?")
@Where(clause = "del = 0")
public class DictExtIndustry extends BaseEntity {

	private int rank;
	private String nameCn;
	private String nameEn;
	private boolean custom;
	private boolean used;
	private String tagText;
	private int tagNum;
	private String description;

//	@OneToMany(mappedBy = "dictExtIndustry",cascade = CascadeType.ALL)
//	Set<DictExtIndustryL1> dictExtIndustryL1s;

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

	public boolean isCustom() {
		return custom;
	}

	public void setCustom(boolean custom) {
		this.custom = custom;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public String getTagText() {
		return tagText;
	}

	public void setTagText(String tagText) {
		this.tagText = tagText;
	}

	public int getTagNum() {
		return tagNum;
	}

	public void setTagNum(int tagNum) {
		this.tagNum = tagNum;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public Set<DictExtIndustryL1> getDictExtIndustryL1s() {
//		return dictExtIndustryL1s;
//	}
//
//	public void setDictExtIndustryL1s(Set<DictExtIndustryL1> dictExtIndustryL1s) {
//		this.dictExtIndustryL1s = dictExtIndustryL1s;
//	}

}
