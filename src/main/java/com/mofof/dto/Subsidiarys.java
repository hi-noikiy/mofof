package com.mofof.dto;

import com.mofof.entity.fund.Subsidiary;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by hzh on 17/6/15.
 */
public class Subsidiarys {
    private LinkedHashSet<String> orgNames; //机构名称集合 去重
    private List<LocalDate> changeDates; //变更时间 和机构名称关联
    private List<String> termNumbers;//届次  和机构名称关联
//    private LocalDate foundDate;//成立时间
//    private LocalDate termEndDate;//任期
//    private LocalDate lastUpdateTime;//最后更新时间

    private Subsidiary subsidiary;//当前选中截面


    public LinkedHashSet<String> getOrgNames() {
        return orgNames;
    }

    public void setOrgNames(LinkedHashSet<String> orgNames) {
        this.orgNames = orgNames;
    }

    public List<LocalDate> getChangeDates() {
        return changeDates;
    }

    public void setChangeDates(List<LocalDate> changeDates) {
        this.changeDates = changeDates;
    }

    public List<String> getTermNumbers() {
        return termNumbers;
    }

    public void setTermNumbers(List<String> termNumbers) {
        this.termNumbers = termNumbers;
    }

    public Subsidiary getSubsidiary() {
        return subsidiary;
    }

    public void setSubsidiary(Subsidiary subsidiary) {
        this.subsidiary = subsidiary;
    }
}
