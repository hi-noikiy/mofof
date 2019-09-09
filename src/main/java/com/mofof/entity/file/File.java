package com.mofof.entity.file;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.mofof.entity.common.BaseEntity;

@Entity
public class File extends BaseEntity {
  @ManyToOne
  private Directory directory;
  @OneToOne
  private FileMetadata fileMetadata;
  private String dirPath; // 文件夹路径
  private String path; // 磁盘文件位置 /user/local/xxx.pdf
  
  public Directory getDirectory() {
    return directory;
  }
  public void setDirectory(Directory directory) {
    this.directory = directory;
  }
  public FileMetadata getFileMetadata() {
    return fileMetadata;
  }
  public void setFileMetadata(FileMetadata fileMetadata) {
    this.fileMetadata = fileMetadata;
  }
  public String getPath() {
    return path;
  }
  public void setPath(String path) {
    this.path = path;
  }
  public String getDirPath() {
    return dirPath;
  }
  public void setDirPath(String dirPath) {
    this.dirPath = dirPath;
  } 
}
