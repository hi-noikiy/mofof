package com.mofof.entity.file;

import javax.persistence.Entity;

import com.mofof.entity.common.BaseEntity;

@Entity
public class FileMetadata extends BaseEntity {
  private String name;
  private String author;
  private String keywords;
  private String fileType;
  private String fileSize;
  private String outLink;
  private String contentType;
  private String checksum;
  
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getAuthor() {
    return author;
  }
  public void setAuthor(String author) {
    this.author = author;
  }
  public String getKeywords() {
    return keywords;
  }
  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }
  public String getFileType() {
    return fileType;
  }
  public void setFileType(String fileType) {
    this.fileType = fileType;
  }
  public String getFileSize() {
    return fileSize;
  }
  public void setFileSize(String fileSize) {
    this.fileSize = fileSize;
  }
  public String getOutLink() {
    return outLink;
  }
  public void setOutLink(String outLink) {
    this.outLink = outLink;
  }
  public String getContentType() {
    return contentType;
  }
  public void setContentType(String contentType) {
    this.contentType = contentType;
  }
  public String getChecksum() {
    return checksum;
  }
  public void setChecksum(String checksum) {
    this.checksum = checksum;
  }  
}
