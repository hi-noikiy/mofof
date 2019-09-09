package com.mofof.entity.system;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import com.mofof.entity.common.BaseEntity;

/**
 * 系统用户 Created by ChenErliang on 2017/5/3.
 */
@Entity
@SQLDelete(sql = "update user_account set del = 1 where id = ?")
@SQLDeleteAll(sql = "update user_account set del = 1 where id = ?")
@Where(clause = "del = 0")
public class UserAccount extends BaseEntity {
  public static final int UNMODIFIED = 0;
  public static final int MODIFIED = 1;

  @Column(unique = true)
  private String username;
//  private String name;
  private String password;
  private String salt;
  @ColumnDefault("0")
  private int passwordStatus;   // 0-未修改 1-已修改
  private LocalDateTime updatedAt;
  
  @OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
  private User user;


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

//  public String getName() {
//    return name;
//  }
//
//  public void setName(String name) {
//    this.name = name;
//  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }
  
  public int getPasswordStatus() {
    return passwordStatus;
  }

  public void setPasswordStatus(int passwordStatus) {
    this.passwordStatus = passwordStatus;
  }
  public void hashPassword(String password) {
    RandomNumberGenerator rng = new SecureRandomNumberGenerator();
    String salt = rng.nextBytes().toString();
    String hashedPasswordBase64 = new Sha256Hash(password, salt, 1024).toBase64();
    setSalt(salt.toString());
    setPassword(hashedPasswordBase64);
  }
  
  public void hashPassword(String password, String salt) {   
    String hashedPasswordBase64 = new Sha256Hash(password, salt, 1024).toBase64();    
    setPassword(hashedPasswordBase64);
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  } 
}
