package com.mofof.entity.system;


import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import java.util.Set;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.InheritanceType;

@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SQLDelete(sql = "update module set del = 1 where id = ?")
@SQLDeleteAll(sql = "update module set del = 1 where id = ?")
@Where(clause = "del = 0")
public class Module extends BaseEntity {
  public static final int MOD_TYPE = 1;
  public static final int TAB_TYPE = 2;

  private String name;   // 名称 (显示使用)
  private String sysKey; // 组件值(程序使用)

  private int modType;      // tab还是module? 

  @ManyToOne
  @JoinColumn(name="parentId")
  private Module parentModule;

//  @OneToMany(mappedBy="parentModule")
//  private Set<Module> subModules = new HashSet<>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSysKey() {
    return sysKey;
  }

  public void setSysKey(String sysKey) {
    this.sysKey = sysKey;
  }

  public int getModType() {
    return modType;
  }

  public void setModType(int type) {
    this.modType = type;
  }

  public Module getParentModule() {
    return this.parentModule;
  }
  
  public void setParentModule(Module parent) {
    this.parentModule = parent;
  }

//  public Set getSubModules() {
//   return subModules;
//  }
//
//  public void setSubModules(Set subModules) {
//   this.subModules = subModules;
//  }
//  
}