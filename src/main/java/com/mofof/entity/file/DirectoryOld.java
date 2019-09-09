package com.mofof.entity.file;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mofof.entity.common.BaseEntity;

/**
 * Created by ChenErliang on 17/6/9.
 */
// @Entity
@SQLDelete(sql = "update directory set del = 1 where id = ?")
@SQLDeleteAll(sql = "update directory set del = 1 where id = ?")
@Where(clause = "del = 0")
public class DirectoryOld extends BaseEntity {

    @ManyToOne
    @JsonIgnore
    private DirectoryOld parent;
    private String name;
    private int fileType; //0-个人 1-共享 2-系统
    private int authority; //0-A类 1-B类 2-C类 3-D类
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DirectoryOld> subDirectorys;
    @OneToMany(mappedBy = "directory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FileOld> files;

    public DirectoryOld getParent() {
        return parent;
    }

    public void setParent(DirectoryOld parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public List<DirectoryOld> getSubDirectorys() {
        return subDirectorys;
    }

    public void setSubDirectorys(List<DirectoryOld> subDirectorys) {
        this.subDirectorys = subDirectorys;
    }

    public List<FileOld> getFiles() {
        return files;
    }

    public void setFiles(List<FileOld> files) {
        this.files = files;
    }
}
