package com.mofof.entity.administrator;

import com.mofof.entity.common.BaseEntity;
import com.mofof.entity.common.Individual;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * 团队成员
 */
@Entity
@SQLDelete(sql = "update team_member set del = 1 where id = ?")
@SQLDeleteAll(sql = "update team_member set del = 1 where id = ?")
@Where(clause = "del = 0")
public class TeamMember extends BaseEntity {

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Individual individual; //对应的个人信息
    private String gender; //性别 字典
    private String nationality; //国籍 字典
    private LocalDate birthday; //生日

    private int age; //年龄
    @ElementCollection
    private List<PhotoFile> photoFiles; //照片列表
    @Lob
    @Column(columnDefinition = "MediumText")  //2^24-1, 16777215长度
    private String description; //个人简介


    public Individual getIndividual() {
        return individual;
    }

    public void setIndividual(Individual individual) {
        this.individual = individual;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<PhotoFile> getPhotoFiles() {
        return photoFiles;
    }

    public void setPhotoFiles(List<PhotoFile> photoFiles) {
        this.photoFiles = photoFiles;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
