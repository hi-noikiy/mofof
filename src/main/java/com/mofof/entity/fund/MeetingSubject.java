package com.mofof.entity.fund;

import javax.persistence.Embeddable;

/**
 * Created by ChenErliang on 17/6/8.
 * 会议议题
 */
@Embeddable
public class MeetingSubject {

    private String content; //议题内容
    private String subjectRelated;//涉及内容
    private String note;//说明

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubjectRelated() {
        return subjectRelated;
    }

    public void setSubjectRelated(String subjectRelated) {
        this.subjectRelated = subjectRelated;
    }
}
