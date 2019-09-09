package com.mofof.entity.administrator;

import java.math.BigInteger;

public class TeamMemberWithAgency {
    private String chineseName; //中文名
    private String englishName; //英文名
    private String gender; //性别 字典
    private int age; //年龄
    private String agency; //个人简介
    private BigInteger id;
    private BigInteger fullTimeWorkForOtherAdminId;
    private boolean existedTeamMember;
    private boolean disabledCheckbox;
    private String positionDesc;

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPositionDesc() {
		return positionDesc;
	}

	public void setPositionDesc(String positionDesc) {
		this.positionDesc = positionDesc;
	}

	public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getFullTimeWorkForOtherAdminId() {
        return fullTimeWorkForOtherAdminId;
    }

    public void setFullTimeWorkForOtherAdminId(BigInteger fullTimeWorkForOtherAdminId) {
        this.fullTimeWorkForOtherAdminId = fullTimeWorkForOtherAdminId;
    }

	public boolean isExistedTeamMember() {
		return existedTeamMember;
	}

	public void setExistedTeamMember(boolean existedTeamMember) {
		this.existedTeamMember = existedTeamMember;
	}

	public boolean isDisabledCheckbox() {
		return disabledCheckbox;
	}

	public void setDisabledCheckbox(boolean disabledCheckbox) {
		this.disabledCheckbox = disabledCheckbox;
	}
    
}
