package com.mofof.entity.investor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mofof.entity.common.Account;
import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Created by ChenErliang on 17/6/9.
 * 投资人账户
 */
@Entity
@SQLDelete(sql = "update investor_account set del = 1 where id = ?")
@SQLDeleteAll(sql = "update investor_account set del = 1 where id = ?")
@Where(clause = "del = 0")
public class InvestorAccount extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private Investor investor;
    @Embedded
    private Account account; //银行账户
    @Column(name = "is_default", columnDefinition = "bit default 0")
    private boolean isDefault; //是否默认
    private boolean isTrusteeship;//是否托管
    private boolean isRaise; //是否集资

    public boolean isTrusteeship() {
		return isTrusteeship;
	}

	public void setTrusteeship(boolean isTrusteeship) {
		this.isTrusteeship = isTrusteeship;
	}

	public Investor getInvestor() {
        return investor;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
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

    public boolean isRaise() {
        return isRaise;
    }

    public void setRaise(boolean raise) {
        isRaise = raise;
    }
}
