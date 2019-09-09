package com.mofof.entity.fund;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by ChenErliang on 17/6/8.
 * 会议
 */
@Entity
@SQLDelete(sql = "update meeting set del = 1 where id = ?")
@SQLDeleteAll(sql = "update meeting set del = 1 where id = ?")
@Where(clause = "del = 0")
public class Meeting extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
    private Fund fund;
    private String meetingName; //会议名称
    private String meetingType; //会议类型 字典
    private String meetingProperty; //会议性质 字典
    private String meetingForm;//会议形式

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime meetingTime; //会议时间
    private String location; //会议地点
    private String attendees; //参加人员
    private String otherAttendees; //其他人员
    private String content; //会议记录
    private String decisionForm; //决议形式
    private boolean decisionMake; //是否形成决议
    private String decisionContent; //决议内容
    @ElementCollection
    private List<MeetingSubject> meetingSubjects;

    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(String meetingType) {
        this.meetingType = meetingType;
    }

    public String getMeetingProperty() {
        return meetingProperty;
    }

    public void setMeetingProperty(String meetingProperty) {
        this.meetingProperty = meetingProperty;
    }


    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(LocalDateTime meetingTime) {
        this.meetingTime = meetingTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAttendees() {
        return attendees;
    }

    public void setAttendees(String attendees) {
        this.attendees = attendees;
    }

    public String getOtherAttendees() {
        return otherAttendees;
    }

    public void setOtherAttendees(String otherAttendees) {
        this.otherAttendees = otherAttendees;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDecisionForm() {
        return decisionForm;
    }

    public void setDecisionForm(String decisionForm) {
        this.decisionForm = decisionForm;
    }

    public boolean isDecisionMake() {
        return decisionMake;
    }

    public void setDecisionMake(boolean decisionMake) {
        this.decisionMake = decisionMake;
    }

    public String getDecisionContent() {
        return decisionContent;
    }

    public void setDecisionContent(String decisionContent) {
        this.decisionContent = decisionContent;
    }

    public List<MeetingSubject> getMeetingSubjects() {
        return meetingSubjects;
    }

    public void setMeetingSubjects(List<MeetingSubject> meetingSubjects) {
        this.meetingSubjects = meetingSubjects;
    }

    public String getMeetingForm() {
        return meetingForm;
    }

    public void setMeetingForm(String meetingForm) {
        this.meetingForm = meetingForm;
    }
}
