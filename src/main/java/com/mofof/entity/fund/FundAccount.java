package com.mofof.entity.fund;

import com.mofof.entity.common.Account;
import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by ChenErliang on 17/6/9.
 * 基金账户
 */
@Entity
@SQLDelete(sql = "update fund_account set del = 1 where id = ?")
@SQLDeleteAll(sql = "update fund_account set del = 1 where id = ?")
@Where(clause = "del = 0")
public class FundAccount extends BaseEntity {
    @ManyToOne
    private Fund fund;
    @Embedded
    private Account account; //银行账户
    @Column(name = "is_default", columnDefinition = "bit default 0")
    private boolean isDefault; //是否默认
    private boolean isTrusteeship;//是否托管
    private boolean isRaise; //是否集资

	public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }
    public boolean isTrusteeship() {
		return isTrusteeship;
	}

	public void setTrusteeship(boolean isTrusteeship) {
		this.isTrusteeship = isTrusteeship;
	}

    public boolean isRaise() {
        return isRaise;
    }

    public void setRaise(boolean raise) {
        isRaise = raise;
    }
}
