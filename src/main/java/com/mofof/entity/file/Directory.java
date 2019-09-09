package com.mofof.entity.file;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.mofof.entity.common.BaseEntity;
import com.mofof.entity.system.User;

@Entity
public class Directory extends BaseEntity {
  public static final String TRASH_BIN = "回收站";
  private String name;
  
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
  
  @ManyToOne
  @JoinColumn(name = "parent_id")
  private Directory parent;
  
  @OneToMany(mappedBy="parent", orphanRemoval = true)
//  private Set<Directory> children;
  private List<Directory> children;
  @Transient
  private Set<File> files;

  @OneToOne(mappedBy="directory", orphanRemoval = true)
  private DirMetadata dirMetadata;

  public static Directory createUserRootDir(User user) {
    Directory d = new Directory();
    d.setName(user.getName());
    d.setUser(user);
    d.setParent(null);
    d.setDirMetadata(DirMetadata.userRootDir());
    return d;
  }

  public static Directory createTrashBin(User user) {
    Directory d = new Directory();
    d.setName(TRASH_BIN);
    d.setUser(user);
    d.setParent(null);
    d.setDirMetadata(DirMetadata.trashBin());
    return d;
  }

  public Directory() {
    //this.dirMetadata = new DirMetadata();
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Directory getParent() {
    return parent;
  }

  public void setParent(Directory parent) {
    this.parent = parent;
  }

  public List<Directory> getChildren() {
    return children;
  }

  public void setChildren(List<Directory> children) {
    this.children = children;
  }

  public Set<File> getFiles() {
    return files;
  }

  public void setFiles(Set<File> files) {
    this.files = files;
  }

  public DirMetadata getDirMetadata() {
    return dirMetadata;
  }

  public void setDirMetadata(DirMetadata dirMetadata) {
    this.dirMetadata = dirMetadata;
  }
}
