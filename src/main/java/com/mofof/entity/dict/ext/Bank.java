package com.mofof.entity.dict.ext;

import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 银行
 * Created by hzh on 2018/12/23.
 */

@Entity
@Table(name = "ext_bank")
@SQLDelete(sql = "update ext_bank set del = 1 where id = ?")
@SQLDeleteAll(sql = "update ext_bank set del = 1 where id = ?")
@Where(clause = "del = 0")
public class Bank extends BaseEntity {
    private String bankName;//银行名称
    private String bankType;//银行类型

    public String getBankName() {
        return bankName;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public String getBankType() {
        return bankType;
    }
    public void setBankType(String bankType) {
        this.bankType = bankType;
    }
}