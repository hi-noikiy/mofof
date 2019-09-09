package com.mofof.entity.dict.ext;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

@Entity
@SQLDelete(sql = "update dict_ext_bank_name set del = 1 where id = ?")
@SQLDeleteAll(sql = "update dict_ext_bank_name set del = 1 where id = ?")
@Where(clause = "del = 0")
public class DictExtBankName extends BaseEntity {

	private int rank;
	private String bankName;

	@ManyToOne(cascade = CascadeType.MERGE)
	DictExtBankType dictExtBankType;

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public DictExtBankType getDictExtBankType() {
		return dictExtBankType;
	}

	public void setDictExtBankType(DictExtBankType dictExtBankType) {
		this.dictExtBankType = dictExtBankType;
	}

}
