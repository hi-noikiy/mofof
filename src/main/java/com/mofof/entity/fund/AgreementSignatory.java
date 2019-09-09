package com.mofof.entity.fund;

import javax.persistence.Embeddable;

/**
 * Created by ChenErliang on 17/6/8.
 * 协议签署方
 */
@Embeddable
public class AgreementSignatory {

    private String signatoryType; //签署方类型
    private String name; //名称
    private String note; //备注

    public String getSignatoryType() {
        return signatoryType;
    }

    public void setSignatoryType(String signatoryType) {
        this.signatoryType = signatoryType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
