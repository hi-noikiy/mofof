package com.mofof.entity.administrator;

import javax.persistence.Embeddable;

/**
 * Created by ChenErliang on 2017/5/26.
 */
@Embeddable
public class PhotoFile {

    private String fileName; //文件名
    private boolean mainPhoto; //是否封面照片

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(boolean mainPhoto) {
        this.mainPhoto = mainPhoto;
    }
}
