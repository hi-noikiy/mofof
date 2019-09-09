package com.mofof.entity.system;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import com.mofof.entity.common.BaseEntity;

/**
 * 角色分组 Created by Qian, Wenliang on 17/8/16.
 */
@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
//                  property = "id")
@SQLDelete(sql = "update role_category set del = 1 where id = ?")
@SQLDeleteAll(sql = "update role_category set del = 1 where id = ?")
@Where(clause = "del = 0")
public class RoleCategory extends BaseEntity {
  public static final String CONVENTIONAL = "常规";

  private String name;

//  @OneToMany(mappedBy = "roleCategory", orphanRemoval = true)
  @Transient
  private Set<Role> roles;
  private int type;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "RoleCategory [name=" + name + ", type=" + type + "]";
  }

}
