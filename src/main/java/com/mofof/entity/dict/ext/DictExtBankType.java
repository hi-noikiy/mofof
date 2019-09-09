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
@SQLDelete(sql = "update dict_ext_bank_type set del = 1 where id = ?")
@SQLDeleteAll(sql = "update dict_ext_bank_type set del = 1 where id = ?")
@Where(clause = "del = 0")
public class DictExtBankType extends BaseEntity {

	private int rank;
	private String bankType;

//	@OneToMany(mappedBy = "dictExtBankType",cascade = CascadeType.ALL)
//	Set<DictExtBankName> dictExtBankNames;

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

//	public Set<DictExtBankName> getDictExtBankNames() {
//		return dictExtBankNames;
//	}

//	public void setDictExtBankNames(Set<DictExtBankName> dictExtBankNames) {
//		this.dictExtBankNames = dictExtBankNames;
//	}

}
