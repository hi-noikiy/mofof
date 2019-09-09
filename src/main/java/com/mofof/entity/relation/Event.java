package com.mofof.entity.relation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by ChenErliang on 2017/6/8.
 * 事件
 */
@Entity
@SQLDelete(sql = "update event set del = 1 where id = ?")
@SQLDeleteAll(sql = "update event set del = 1 where id = ?")
@Where(clause = "del = 0")
public class Event extends BaseEntity {
    @ManyToOne
    private InvestRelation investRelation; //相关投资关系
    private String eventType; //事件类型 字典
    private String name; //事件名称
    private LocalDateTime eventDate; //发生时间
    private int importance; //重要性 0-4
    private int eventStatus; //0-待处理 1-不处理 2-已处理
    private String involveCompanys; //涉及公司 多个
    private String involveFunds; //涉及基金 多个
    private String involveThirdParty; //涉及第三方 多个
    private String description; //事件描述
    private LocalDate handleDate;//处理日期
    private String handleResult;//处理结果

    public InvestRelation getInvestRelation() {
        return investRelation;
    }

    public void setInvestRelation(InvestRelation investRelation) {
        this.investRelation = investRelation;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public int getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(int eventStatus) {
        this.eventStatus = eventStatus;
    }

    public String getInvolveCompanys() {
        return involveCompanys;
    }

    public void setInvolveCompanys(String involveCompanys) {
        this.involveCompanys = involveCompanys;
    }

    public String getInvolveFunds() {
        return involveFunds;
    }

    public void setInvolveFunds(String involveFunds) {
        this.involveFunds = involveFunds;
    }

    public String getInvolveThirdParty() {
        return involveThirdParty;
    }

    public void setInvolveThirdParty(String involveThirdParty) {
        this.involveThirdParty = involveThirdParty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getHandleDate() {
        return handleDate;
    }

    public void setHandleDate(LocalDate handleDate) {
        this.handleDate = handleDate;
    }

    public String getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(String handleResult) {
        this.handleResult = handleResult;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }
}
