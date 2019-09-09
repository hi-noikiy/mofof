package com.mofof.entity.fund;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by ChenErliang on 17/6/8.
 * 协议
 */
@Entity
@SQLDelete(sql = "update agreement set del = 1 where id = ?")
@SQLDeleteAll(sql = "update agreement set del = 1 where id = ?")
@Where(clause = "del = 0")
public class Agreement extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
    private Fund fund; //相关基金
    private String agreementType; //协议类型
    private String name; //协议名称
    private LocalDate signDate; //签署日期
    @ElementCollection
    private List<AgreementTerm> agreementTerms; //协议条款
    @ElementCollection
    private List<AgreementSignatory> agreementSignatorys; //签署方
    private String note;//备注


    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAgreementType() {
        return agreementType;
    }

    public void setAgreementType(String agreementType) {
        this.agreementType = agreementType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getSignDate() {
        return signDate;
    }

    public void setSignDate(LocalDate signDate) {
        this.signDate = signDate;
    }

    public List<AgreementTerm> getAgreementTerms() {
        return agreementTerms;
    }

    public void setAgreementTerms(List<AgreementTerm> agreementTerms) {
        this.agreementTerms = agreementTerms;
    }

    public List<AgreementSignatory> getAgreementSignatorys() {
        return agreementSignatorys;
    }

    public void setAgreementSignatorys(List<AgreementSignatory> agreementSignatorys) {
        this.agreementSignatorys = agreementSignatorys;
    }
}
