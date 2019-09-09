package com.mofof.entity.file;

import javax.persistence.ManyToOne;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mofof.entity.common.BaseEntity;

/**
 * Created by ChenErliang on 17/6/9.
 */
// @Entity
@SQLDelete(sql = "update file set del = 1 where id = ?")
@SQLDeleteAll(sql = "update file set del = 1 where id = ?")
@Where(clause = "del = 0")
public class FileOld extends BaseEntity {

    @ManyToOne
    @JsonIgnore
    private DirectoryOld directory;
    private String filename;
    private String filetype;
    private Long filesize;

    public DirectoryOld getDirectory() {
        return directory;
    }

    public void setDirectory(DirectoryOld directory) {
        this.directory = directory;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public Long getFilesize() {
        return filesize;
    }

    public void setFilesize(Long filesize) {
        this.filesize = filesize;
    }

}
