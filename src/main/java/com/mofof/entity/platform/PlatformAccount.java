package com.mofof.entity.platform;

import com.mofof.entity.common.Account;
import com.mofof.entity.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

/**
 * Created by ChenErliang on 17/6/9.
 * 投资平台账户
 */
@Entity
@ApiModel(value="投资平台/银行账户")
@SQLDelete(sql = "update platform_account set del = 1 where id = ?")
@SQLDeleteAll(sql = "update platform_account set del = 1 where id = ?")
@Where(clause = "del = 0")
public class PlatformAccount extends BaseEntity {

    @ApiModelProperty(value = "对应的投资平台")
    @ManyToOne
    private Platform platform;

    @ApiModelProperty(value = "对应的银行账户基类")
    @Embedded
    private Account account; //银行账户

    @ApiModelProperty(value = "默认账户")
    @Column(name = "is_default", columnDefinition = "bit default 0")
    private boolean isDefault; //是否默认

    @ApiModelProperty(value = "是否托管账户")
    private boolean isTrusteeship;//是否托管

    @ApiModelProperty(value = "是否募集账户")
    private boolean isRaise; //是否集资

	public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
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
