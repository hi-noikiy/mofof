package com.mofof.entity.finance;

import com.mofof.entity.common.BaseEntity;
import com.mofof.entity.fund.Fund;

import javax.persistence.ElementCollection;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

/**
 * 报表数据基类
 * Created by ChenErliang on 2017/8/6.
 */
@MappedSuperclass
public class BaseFinance<T extends BaseFinanceDetail> extends BaseEntity {

    @ManyToOne
    private Fund fund;
    private String year; //年度
    private String financeType;//类型
    private String cycle; //周期
    private String memo; //备注
    private LocalDate financeDate; //报表日期
    @ElementCollection
    private List<T> details; //明细
    private boolean isAudit; //是否审计

    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getFinanceType() {
        return financeType;
    }

    public void setFinanceType(String financeType) {
        this.financeType = financeType;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public LocalDate getFinanceDate() {
        return financeDate;
    }

    public void setFinanceDate(LocalDate financeDate) {
        this.financeDate = financeDate;
    }

    public List<T> getDetails() {
        return details;
    }

    public void setDetails(List<T> details) {
        this.details = details;
    }

    public boolean isAudit() {
        return isAudit;
    }

    public void setAudit(boolean audit) {
        isAudit = audit;
    }

    public LocalDate buildFinanceDate() {
        int month = 0;
        if (this.financeType.equals("季度")) {
            month = Integer.parseInt(this.cycle) * 3;
        } else if (this.financeType.equals("年度")) {
            month = 12;
        } else if (this.financeType.equals("半年度")) {
            month = Integer.parseInt(this.cycle) * 6;
        } else if (this.financeType.equals("月度")) {
            month = Integer.parseInt(this.cycle);
        }
        LocalDate localDate = LocalDate.of(Integer.parseInt(this.year), month, 1);
        return localDate.with(TemporalAdjusters.lastDayOfMonth());
    }

    public String key() {
        StringBuffer key = new StringBuffer();
        key.append(this.year);
        if (this.financeType.equals("季度")) {
            key.append("Q");
        } else if (this.financeType.equals("年度")) {
            key.append("Y");
        } else if (this.financeType.equals("半年度")) {
            key.append("H");
        } else if (this.financeType.equals("月度")) {
            key.append("M");
        }
        if (!this.financeType.equals("年度")) {
            key.append(this.cycle);
        }
        return key.toString();
    }
}
