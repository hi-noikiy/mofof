package com.mofof.entity.system;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.mofof.entity.common.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

@Entity
@SQLDelete(sql = "update action_item set del = 1 where id = ?")
@SQLDeleteAll(sql = "update action_item set del = 1 where id = ?")
@Where(clause = "del = 0")
public class ActionItem extends BaseEntity {
	private String sysId; //module/path@edit
	private String name;

	@ManyToOne
	@JoinColumn(name = "moduleId")
	private Module module;

  public String getSysId() {
    return sysId;
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

  public Module getModule() {
    return module;
  }

  public void setModule(Module module) {
    this.module = module;
  }	
}