package com.mofof.entity.fund;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

/**
 * Created by ChenErliang on 17/6/8.
 */
@Embeddable
public class AgreementTerm {

    private String termName;//条款名称 字典 根据协议类型筛选
    @Lob
    @Column(columnDefinition = "MediumText")  //2^24-1, 16777215长度
    private String termContent; //条款内容

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getTermContent() {
        return termContent;
    }

    public void setTermContent(String termContent) {
        this.termContent = termContent;
    }
}
