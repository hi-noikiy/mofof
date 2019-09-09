package com.mofof.entity.system;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import com.mofof.entity.common.BaseEntity;
import com.mofof.entity.platform.Platform;

/**
 * 
 */
@Entity
@SQLDelete(sql = "update department set del = 1 where id = ?")
@SQLDeleteAll(sql = "update department set del = 1 where id = ?")
@Where(clause = "del = 0")
public class Department extends BaseEntity {

	private String name;
	@ManyToOne
//	@JsonIgnore
	private Department parent;

//	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
	@OneToMany(mappedBy = "parent", orphanRemoval = true)
	@Where(clause = "del = 0")
	private List<Department> subDepartments;

//	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
//	private List<Investor> investors;

	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
  @Where(clause = "del = 0")
  @JoinTable(                                
      name="user_department",                    
      joinColumns= {@JoinColumn(name="department_id")},        
      inverseJoinColumns= {@JoinColumn(name="user_id")})  
//	@JsonIgnore
	private List<User> user;
	
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE })
	@Where(clause = "del = 0")
	@JoinTable(                                
      name="user_managed_department",                    
      joinColumns= {@JoinColumn(name="managed_department_id")},        
      inverseJoinColumns= {@JoinColumn(name="user_id")}) 
//	@JsonIgnore
	private List<User> managedByusers;

	@Transient
  private Set<Platform> platforms;
	

	public List<User> getUser() {
    return user;
  }

  public void setUser(List<User> user) {
    this.user = user;
  }
  
	public Set<Platform> getPlatforms() {  
    return platforms;
  }

  public void setPlatforms(Set<Platform> platforms) {
    this.platforms = platforms;
  }

  public List<User> getManagedByusers() {
    return managedByusers;
  }

  public void setManagedByusers(List<User> managedByusers) {
    this.managedByusers = managedByusers;
  }

  public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Department getParent() {
		return parent;
	}

	public void setParent(Department parent) {
		this.parent = parent;
	}

	public List<Department> getSubDepartments() {
		return subDepartments;
	}

	public void setSubDepartments(List<Department> subDepartments) {
		this.subDepartments = subDepartments;
	}

//	public List<Investor> getInvestors() {
//		return investors;
//	}
//
//	public void setInvestors(List<Investor> investors) {
//		this.investors = investors;
//	}
}
