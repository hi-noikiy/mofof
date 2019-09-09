package com.mofof.entity.system;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import com.mofof.entity.common.BaseEntity;

/**
 * 角色 Created by Qian, Wenliang on 17/8/16.
 */
@Entity
@SQLDelete(sql = "update role set del = 1 where id = ?")
@SQLDeleteAll(sql = "update role set del = 1 where id = ?")
@Where(clause = "del = 0")
public class Role extends BaseEntity {

  public static final String SUPER = "超级管理员";
  public static final String ADMIN = "管理员";
  public static final String USER = "用户";
  public static final String TEST = "测试";
  
  @Column(unique = true)
  private String name;
  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "roleCategoryId")
  private RoleCategory roleCategory;

//    @OneToMany(mappedBy="role", cascade = CascadeType.ALL)
  @Transient
  private Set<User> users;
//
//    @OneToMany(mappedBy="role", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Set<Perm> perms = new HashSet<>();
//    
//    @OneToMany(mappedBy="role", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Set<MenuItem> menuItems = new HashSet<>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public RoleCategory getRoleCategory() {
    return roleCategory;
  }

  public void setRoleCategory(RoleCategory roleCategory) {
    this.roleCategory = roleCategory;
  }

//	public Set<User> getUsers() {
//		return users;
//	}
//
//	public void setUsers(Set<User> users) {
//		this.users = users;
//	}
//
//	public Set<Perm> getPerms() {
//		return perms;
//	}
//
//	public void setPerms(Set<Perm> perms) {
//		this.perms = perms;
//	}
//
//    public void addPerms(Iterable<Perm> iter) {
//        for (Perm perm : iter) {
//            this.perms.add(perm);
//        }
//    }
//
//	public Set<MenuItem> getMenuItems() {
//		return menuItems;
//	}
//
//	public void setMenuItems(Set<MenuItem> menuItems) {
//		this.menuItems = menuItems;
//	}
//
//    public void addMenuItems(Iterable<MenuItem> iter) {
//        for (MenuItem item : iter) {
//            this.menuItems.add(item);
//        }
//    }

}
