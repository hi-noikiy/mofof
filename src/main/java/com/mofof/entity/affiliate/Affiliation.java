package com.mofof.entity.affiliate;

import com.mofof.entity.common.BaseEntity;
import com.mofof.entity.fund.Fund;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by ChenErliang on 2017/5/6.
 */
@Entity
@SQLDelete(sql = "update affiliation set del = 1 where id = ?")
@SQLDeleteAll(sql = "update affiliation set del = 1 where id = ?")
@Where(clause = "del = 0")
public class Affiliation extends BaseEntity {

    @ManyToOne
    private Fund fund;
    @ManyToOne
    private Affiliate affiliate;
    private String affiliation; //关联关系  普通合伙人 之类 逗号分割
    private String note;

    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

    public Affiliate getAffiliate() {
        return affiliate;
    }

    public void setAffiliate(Affiliate affiliate) {
        this.affiliate = affiliate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }
}
