package com.mofof.entity.system;


import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import com.mofof.entity.common.BaseEntity;
import com.mofof.entity.mywork.CalEvent;

/**
 * 系统用户 Created by ChenErliang on 2017/5/3.
 */
@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
@SQLDelete(sql = "update user set del = 1 where id = ?")
@SQLDeleteAll(sql = "update user set del = 1 where id = ?")
@Where(clause = "del = 0")
public class User extends BaseEntity {
  public static final int SUSPEND = 0;
  public static final int NORMAL = 1;

  private int jobNumber;
  private int status;           // 0-停用 1-正常;
  private String emailAddress;  // 工作邮箱
  private String phone;         // 手机号码
  private String name;

//  @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
  @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
  @Where(clause = "del = 0")
  @JoinTable(                                
      name="user_department",                    
      joinColumns= {@JoinColumn(name="user_id")},        
      inverseJoinColumns= {@JoinColumn(name="department_id")})  
  private List<Department> departments; // 所属部门

  @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE })
  @Where(clause = "del = 0")
  @JoinTable(                                
      name="user_managed_department",                    
      joinColumns= {@JoinColumn(name="user_id")},        
      inverseJoinColumns= {@JoinColumn(name="managed_department_id")})  
  private List<Department> managedDepartments; // 分管部门

  @ManyToOne(cascade = CascadeType.MERGE)
  @Where(clause = "del = 0")
  private Role role;
//  @OneToMany(mappedBy = "creatorUser", cascade = CascadeType.ALL)
//  Set<CalEvent> calEvents;

//  @OneToOne(mappedBy="user")
  @Transient
  private UserAccount userAccount;
  
//  @Transient
  @ManyToMany(mappedBy = "attendees")
  @Where(clause = "del = 0")
  Set<CalEvent> calEvents;

  
  
  public User() {
    setStatus(User.NORMAL);
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public List<Department> getDepartments() {
    return departments;
  }

  public void setDepartments(List<Department> departments) {
    this.departments = departments;
  }

  public List<Department> getManagedDepartments() {
    return managedDepartments;
  }

  public void setManagedDepartments(List<Department> managedDepartments) {
    this.managedDepartments = managedDepartments;
  }

  public void setJobNumber(int jobNumber) {
    this.jobNumber = jobNumber;
  }

  public int getJobNumber() {
    return this.jobNumber;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public UserAccount getUserAccount() {
    return userAccount;
  }

  public void setUserAccount(UserAccount userAccount) {
    this.userAccount = userAccount;
  }

  public Set<CalEvent> getCalEvents() {
    return calEvents;
  }

  public void setCalEvents(Set<CalEvent> calEvents) {
    this.calEvents = calEvents;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


//  public Set<String> getPerms() {
//    Role role = this.getRole();
//    Set<String> set = new HashSet<>();
//
//    if (role != null) {
//      Set<Perm> perms = role.getPerms();
//      for (Perm perm : perms) {
//        set.add(perm.getValue());
//      }
//    }
//    return set;
//  }
//
//  public Set<String> getMenuItems() {
//    Role role = this.getRole();
//    Set<String> set = new HashSet<>();
//
//    if (role != null) {
//      Set<MenuItem> menuItems = role.getMenuItems();
//      for (MenuItem item : menuItems) {
//        set.add(item.getValue());
//      }
//    }
//    return set;
//  }

//  public Set<CalEvent> getCalEvents() {
//    return calEvents;
//  }
//
//  public void setCalEvents(Set<CalEvent> calEvents) {
//    this.calEvents = calEvents;
//  }
  
}
