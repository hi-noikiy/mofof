package com.mofof.entity.fund;

import com.mofof.entity.administrator.AdministratorTeam;
import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by ChenErliang on 17/6/9.
 * 基金管理团队
 */
@Entity
@SQLDelete(sql = "update fund_admin_team set del = 1 where id = ?")
@SQLDeleteAll(sql = "update fund_admin_team set del = 1 where id = ?")
@Where(clause = "del = 0")
public class FundAdminTeam extends BaseEntity {
    @ManyToOne
    private Fund fund; //相关基金
    @ManyToOne
    private AdministratorTeam administratorTeam; //相关管理团队
    private boolean keyPerson; //是否关键人
    private boolean contactPerson; //是否联系人
    private String note; //备注

    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

    public AdministratorTeam getAdministratorTeam() {
        return administratorTeam;
    }

    public void setAdministratorTeam(AdministratorTeam administratorTeam) {
        this.administratorTeam = administratorTeam;
    }

    public boolean isKeyPerson() {
        return keyPerson;
    }

    public void setKeyPerson(boolean keyPerson) {
        this.keyPerson = keyPerson;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(boolean contactPerson) {
        this.contactPerson = contactPerson;
    }
}
