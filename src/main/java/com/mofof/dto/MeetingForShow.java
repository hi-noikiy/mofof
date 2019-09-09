package com.mofof.dto;

import com.mofof.entity.fund.Fund;
import com.mofof.entity.fund.Meeting;
import com.mofof.entity.fund.MeetingSubject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by hzh on 2017/10/2.
 */
public class MeetingForShow {

    @JsonIgnore
    private Fund fund;
    private Long meetingId;
    private String meetingName; //会议名称
    private String meetingType; //会议类型 字典
    private String meetingProperty; //会议性质 字典
    private String meetingForm;//会议形式
    private LocalDateTime meetingTime; //会议时间
    private String location; //会议地点
    private String attendees; //参加人员
    private String otherAttendees; //其他人员
    private String content; //会议记录
    private String decisionForm; //决议形式
    private boolean decisionMake; //是否形成决议
    private String decisionContent; //决议内容
    private List<MeetingSubject> meetingSubjects;

    //dto
    private String attendeesForShow; //参加人员


    public MeetingForShow(Meeting meeting){
        if(meeting!=null){
            this.setFund(meeting.getFund());
            this.setMeetingId(meeting.getId());
            this.setMeetingName(meeting.getMeetingName());
            this.setMeetingType(meeting.getMeetingType());
            this.setMeetingProperty(meeting.getMeetingProperty());
            this.setMeetingForm(meeting.getMeetingForm());
            this.setMeetingTime(meeting.getMeetingTime());
            this.setLocation(meeting.getLocation());
            this.setAttendees(meeting.getAttendees());
            this.setOtherAttendees(meeting.getOtherAttendees());
            this.setContent(meeting.getContent());
            this.setDecisionForm(meeting.getDecisionForm());
            this.setDecisionMake(meeting.isDecisionMake());
            this.setDecisionContent(meeting.getDecisionContent());
            this.setMeetingSubjects(meeting.getMeetingSubjects());
        }
    }

    public MeetingForShow(){

    }

    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

    public Long getMeetingId() {
        return this.meetingId;
    }

    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
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

    public String getMeetingForm() {
        return meetingForm;
    }

    public void setMeetingForm(String meetingForm) {
        this.meetingForm = meetingForm;
    }

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

    public String getAttendeesForShow() {
        return attendeesForShow;
    }

    public void setAttendeesForShow(String attendeesForShow) {
        this.attendeesForShow = attendeesForShow;
    }
}
