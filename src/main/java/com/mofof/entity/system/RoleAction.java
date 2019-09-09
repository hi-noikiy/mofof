package com.mofof.entity.system;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
          property = "id")
@SQLDelete(sql = "update role_action set del = 1 where id = ?")
@SQLDeleteAll(sql = "update role_action set del = 1 where id = ?")
@Where(clause = "del = 0")
public class RoleAction extends BaseEntity {

  private String sysId;
  private String name;
//    @ManyToOne
//    @JoinColumn(name = "role_id")
//    private Role role;
  @ManyToOne
  @JoinColumn(name = "menuItemId")
  private MenuItem menuItem;

  @ManyToOne
  @JoinColumn(name = "roleModuleId")
  private RoleModule roleModule;

//    public Role getRole() {
//      return role;
//    }
  //
//    public void setRole(Role role) {
//      this.role = role;
//    }
  public static RoleAction copyActionItem(ActionItem actionItem) {
    RoleAction r = new RoleAction();
    r.setName(actionItem.getName());
    r.setSysId(actionItem.getSysId());
    return r;
  }

  public String getSysId() {
    return sysId;
  }

  public MenuItem getMenuItem() {
    return menuItem;
  }

  public void setMenuItem(MenuItem menuItem) {
    this.menuItem = menuItem;
  }

  public void setSysId(String sysId) {
    this.sysId = sysId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public RoleModule getRoleModule() {
    return roleModule;
  }

  public void setRoleModule(RoleModule roleModule) {
    this.roleModule = roleModule;
  }
}
