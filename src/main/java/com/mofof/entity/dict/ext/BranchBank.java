package com.mofof.entity.dict.ext;

import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 银行网点(暂时不用,网点数据从data服务器上去取)
 * Created by hzh on 2018/12/20.
 */
@Entity
@Table(name = "ext_branch_bank")
@SQLDelete(sql = "update ext_branch_bank set del = 1 where id = ?")
@SQLDeleteAll(sql = "update ext_branch_bank set del = 1 where id = ?")
@Where(clause = "del = 0")
public class BranchBank extends BaseEntity {

    private String bankNo;//银联号
    private String bankType;//银行类型
    private String bankName;//银行名称
    private String province ;//省信息
    private String city;//市
    private String branchName;//支行名称
    private String branchAddress;//支行地址
    private String branchPhone;//支行电话
    private String keyword;//关键字

    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public String getBankNo() {
        return bankNo;
    }
    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }
    public String getBankType() {
        return bankType;
    }
    public void setBankType(String bankType) {
        this.bankType = bankType;
    }
    public String getBankName() {
        return bankName;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public String getBranchName() {
        return branchName;
    }
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
    public String getBranchAddress() {
        return branchAddress;
    }
    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }
    public String getBranchPhone() {
        return branchPhone;
    }
    public void setBranchPhone(String branchPhone) {
        this.branchPhone = branchPhone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}