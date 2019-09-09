package com.mofof.entity.system;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.google.gson.annotations.Expose;
import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;

// @Table(
// 	uniqueConstraints = {@UniqueConstraint(columnNames = {"value", "role_id"})}
// )
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, 
				  property = "id")
@SQLDelete(sql = "update menu_item set del = 1 where id = ?")
@SQLDeleteAll(sql = "update menu_item set del = 1 where id = ?")
@Where(clause = "del = 0")
public class MenuItem extends BaseEntity {
  
	private String name;
	private String icon;
	private String href;
	private String sysGroup;
	
	

	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "parent_id")
	private MenuItem parentMenuItem;
	
	 @OneToMany(mappedBy="parentMenuItem")
	 private Set<MenuItem> subMenuItems = new HashSet<MenuItem>();

	@ManyToOne(cascade = CascadeType.MERGE)
	private Role role;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public String getSysGroup() {
    return sysGroup;
  }

  public void setSysGroup(String moduleGroup) {
    this.sysGroup = moduleGroup;
  }

  public MenuItem getParentMenuItem() {
    return parentMenuItem;
  }

  public void setParentMenuItem(MenuItem parentMenuItem) {
    this.parentMenuItem = parentMenuItem;
  }

  public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

  public Set<MenuItem> getSubMenuItems() {
    return subMenuItems;
  }

  public void setSubMenuItems(Set<MenuItem> subMenuItems) {
    this.subMenuItems = subMenuItems;
  }
}