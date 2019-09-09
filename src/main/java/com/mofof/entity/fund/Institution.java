package com.mofof.entity.fund;

import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by ChenErliang on 17/6/8.
 * 下属机构
 */
@Entity
@SQLDelete(sql = "update institution set del = 1 where id = ?")
@SQLDeleteAll(sql = "update institution set del = 1 where id = ?")
@Where(clause = "del = 0")
public class Institution extends BaseEntity {

    @ManyToOne
    private Fund fund;//相关基金
    private String institutionType; //机构类别
    private int termNumber; //届次
    private LocalDate changeDate; //变更日期
    private LocalDate foundDate; //成立日期
    private LocalDate termEndDate; //任期结束日期

    @ElementCollection
    private List<InstitutionMember> institutionMembers; //机构成员

    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

    public String getInstitutionType() {
        return institutionType;
    }

    public void setInstitutionType(String institutionType) {
        this.institutionType = institutionType;
    }

    public int getTermNumber() {
        return termNumber;
    }

    public void setTermNumber(int termNumber) {
        this.termNumber = termNumber;
    }

    public LocalDate getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDate changeDate) {
        this.changeDate = changeDate;
    }

    public LocalDate getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(LocalDate foundDate) {
        this.foundDate = foundDate;
    }

    public LocalDate getTermEndDate() {
        return termEndDate;
    }

    public void setTermEndDate(LocalDate termEndDate) {
        this.termEndDate = termEndDate;
    }

    public List<InstitutionMember> getInstitutionMembers() {
        return institutionMembers;
    }

    public void setInstitutionMembers(List<InstitutionMember> institutionMembers) {
        this.institutionMembers = institutionMembers;
    }

}
