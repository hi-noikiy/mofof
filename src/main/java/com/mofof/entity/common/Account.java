package com.mofof.entity.common;

import javax.persistence.Embeddable;

/**
 * 银行账户
 * Created by ChenErliang on 17/4/12.
 */
@Embeddable
public class Account {

    private String accountName; //账户全称
    private String accountNumber; //账户号码
    private String accountType;  //账户类型 基本户，一般户等
    private String bankType; //银行类型   大型商业银行、股份制商业银行、邮政储蓄银行、外资银行、民营银行、城市商业银行、农商行、农合行、境外银行
    private String bank; //银行名称
    private String branch; //支行
    private String currency;  //币种 人民币 美元等
    private String province;//开户省
    private String city;//开户市
    private String address; //银行地址

    private boolean swift;//额外信息

    private String note; //备注
    private String swiftCode;  //swift码
    private String pathCode; //路径代码
    private String agentBank;  //代理银行名称
    private String agentSwiftCode;  //代理银行swift码
    private String agentAddress; //代理银行地址

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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    public String getPathCode() {
        return pathCode;
    }

    public void setPathCode(String pathCode) {
        this.pathCode = pathCode;
    }

    public String getAgentBank() {
        return agentBank;
    }

    public void setAgentBank(String agentBank) {
        this.agentBank = agentBank;
    }

    public String getAgentSwiftCode() {
        return agentSwiftCode;
    }

    public void setAgentSwiftCode(String agentSwiftCode) {
        this.agentSwiftCode = agentSwiftCode;
    }

    public String getAgentAddress() {
        return agentAddress;
    }

    public void setAgentAddress(String agentAddress) {
        this.agentAddress = agentAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public boolean isSwift() {
        return swift;
    }

    public void setSwift(boolean swift) {
        this.swift = swift;
    }
}
