package com.mofof.entity.fund;

import javax.persistence.Embeddable;
import java.time.LocalDate;

/**
 * Created by ChenErliang on 17/6/8.
 * 下属机构成员
 */
@Embeddable
public class InstitutionMember {

    private String name; //姓名
    private String representAgency; //代表机构
    private LocalDate termStartDate; //起始担任日期
    private String job;//职务
    private String note;//备注

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRepresentAgency() {
        return representAgency;
    }

    public void setRepresentAgency(String representAgency) {
        this.representAgency = representAgency;
    }

    public LocalDate getTermStartDate() {
        return termStartDate;
    }

    public void setTermStartDate(LocalDate termStartDate) {
        this.termStartDate = termStartDate;
    }

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
    
}
