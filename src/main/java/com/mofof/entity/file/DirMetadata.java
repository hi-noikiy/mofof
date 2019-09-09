package com.mofof.entity.file;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.mofof.entity.common.BaseEntity;

@Entity(name = "DirMetadata")
public class DirMetadata extends BaseEntity{
  public static final String SYSTEM_DIR = "system";
  public static final String TRASH_DIR = "trash";
  public static final String IN_TRASH_DIR = "trash-sub";
  public static final String USER_ROOT_DIR = "user";
  public static final String SHARED_DIR = "share";
  public static final String CHAIN_DIR = "chain";
  public static final String FOLDER = "folder";

  private String clazz; // 文件夹类别
  private boolean editable;
  private boolean movable;

  @OneToOne(cascade = CascadeType.ALL)
  @MapsId
  private Directory directory;
  
  public DirMetadata() {
    setClazz(FOLDER);
    setEditable(true);
    setMovable(true);
  }

  public static DirMetadata userRootDir() {
    DirMetadata meta = new DirMetadata();
    meta.setClazz(USER_ROOT_DIR);
    meta.setEditable(false);
    meta.setMovable(false);
    return meta;
  }

  public static DirMetadata trashBin() {
    DirMetadata meta = new DirMetadata();
    meta.setClazz(TRASH_DIR);
    meta.setEditable(false);
    meta.setMovable(false);
    return meta;
  }

  public String getClazz() {
    return clazz;
  }

  public void setClazz(String clazz) {
    this.clazz = clazz;
  }

  public Directory getDirectory() {
    return directory;
  }

  public void setDirectory(Directory directory) {
    this.directory = directory;
  }

  public boolean isEditable() {
    return editable;
  }

  public void setEditable(boolean editable) {
    this.editable = editable;
  }

  public boolean isMovable() {
    return movable;
  }

  public void setMovable(boolean movable) {
    this.movable = movable;
  }
}
