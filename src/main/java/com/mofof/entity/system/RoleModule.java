package com.mofof.entity.system;

import java.util.HashSet;
import java.util.Set;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mofof.entity.common.BaseEntity;


@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
          property = "id")
@SQLDelete(sql = "update role_module set del = 1 where id = ?")
@SQLDeleteAll(sql = "update role_module set del = 1 where id = ?")
@Where(clause = "del = 0")
public class RoleModule extends BaseEntity {

  private String name;    // 名字 (显示使用)
  private String sysKey;  // 组件值(程序使用)
  private String sysGroup;
  
  private int modType;       // 是否tab 
  private String description;
	
	@ManyToOne
	@JoinColumn(name = "menuItemId")
	private MenuItem menuItem;
	
	@ManyToOne
  @JoinColumn(name = "roleId")
  private Role role;
	
	@ManyToOne
  @JoinColumn(name = "parentId")
  private RoleModule parentRoleModule;

  @OneToMany(mappedBy = "parentRoleModule") 
  private Set<RoleModule> subRoleModules = new HashSet<>();
  
  @OneToMany(mappedBy = "roleModule")  
  Set<RoleAction> roleActions;

  public static RoleModule copyFromModule(Module module) {
    RoleModule roleModule = new RoleModule();
    roleModule.setName(module.getName());
    roleModule.setSysKey(module.getSysKey());
    //roleModule.setModType(module.getModType()); 
    return roleModule;
  }
  //parentRoleModule
  public static RoleModule newTab(Role role, String tabName, String sysGroup) {
    RoleModule rm = new RoleModule();
    rm.setRole(role);
    rm.setName(tabName);
    rm.setSysGroup(sysGroup);
    rm.setModType(Module.TAB_TYPE);
    return rm;
  }
  //subRoleModules
  public static RoleModule newTab(RoleModule parent, String tabName) {
    RoleModule rm = new RoleModule();
    if (parent != null) {
      rm.setParentRoleModule(parent);
      rm.setRole(parent.getRole());
      rm.setSysGroup(parent.getSysGroup());
    }  
    rm.setName(tabName);
    rm.setModType(Module.TAB_TYPE);
    return rm;
  }

  public String getSysGroup() {
    return sysGroup;
  }

  public void setSysGroup(String sysGroup) {
    this.sysGroup = sysGroup;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public Set<RoleModule> getSubRoleModules() {
    return subRoleModules;
  }

  public void setSubRoleModules(Set<RoleModule> subRoleModules) {
    this.subRoleModules = subRoleModules;
  }

  public Set<RoleAction> getRoleActions() {
    return roleActions;
  }

  public void setRoleActions(Set<RoleAction> roleActions) {
    this.roleActions = roleActions;
  }

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

  public RoleModule getParentRoleModule() {
    return parentRoleModule;
  }

  public void setParentRoleModule(RoleModule parentRoleModule) {
    this.parentRoleModule = parentRoleModule;
  }

  public MenuItem getMenuItem() {
    return menuItem;
  }

  public void setMenuItem(MenuItem menuItem) {
    this.menuItem = menuItem;
  }

  public int getModType() {
    return modType;
  }

  public void setModType(int modType) {
    this.modType = modType;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}