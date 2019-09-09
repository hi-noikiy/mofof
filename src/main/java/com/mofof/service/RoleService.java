package com.mofof.service;

import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mofof.entity.system.Role;
import com.mofof.entity.system.RoleCategory;
import com.mofof.repository.RoleCategoryRepository;
import com.mofof.repository.RoleRepository;

/**
 * Created by Qian,Wenliang on 2017/8/16.
 */
@Service
@Transactional
public class RoleService {
  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private RoleCategoryRepository roleCategoryRepository;

  public Role save(Role role) {
    return roleRepository.save(role);
  }

  public Iterable<Role> findAll() {
    return roleRepository.findAllByOrderByIdDesc();
  }

  public Iterable<RoleCategory> findAllCategory() {
    Iterable<RoleCategory> roleCategorys = roleCategoryRepository.findAllByOrderById();
    for (RoleCategory roleCategory : roleCategorys) {
      long id = roleCategory.getId();
      Set<Role> roles = roleRepository.findByRoleCategoryId(id);
      // roleCategory.getRoles().addAll(roles);
      setRoles(roleCategory, roles);
    }
    return roleCategorys;
  }

  // 替换roleCategory.getRoles().addAll(roles);
  public RoleCategory setRoles(RoleCategory roleCategory, Set<Role> roles) {
    for (Role role : roles) {
      role.setRoleCategory(roleCategory);
    }
    return roleCategory;
  }

  public RoleCategory newCategory(Long id) {
    Iterable<RoleCategory> categorys = roleCategoryRepository.findAllByOrderById();
    String newCategory = "新的分组";
    for (int i = 0; i < 10000; i++) {
      String fileName = newCategory + (i == 0 ? "" : "(" + i + ")");
      boolean nameExists = false;
      for (Iterator<RoleCategory> it = categorys.iterator(); it.hasNext();) {
        RoleCategory roleCategory = it.next();
        if (fileName.equals(roleCategory.getName())) {
          nameExists = true;
          break;
        }
      }
      if (!nameExists) {
        newCategory = fileName;
        break;
      }
    }
    RoleCategory roleCategory = new RoleCategory();
    roleCategory.setName(newCategory);
    roleCategory.setType(2);
    return roleCategoryRepository.save(roleCategory);
  }

  public RoleCategory deleteCategory(Long id) {
    RoleCategory roleCategory = roleCategoryRepository.findOne(id);
    roleCategoryRepository.delete(roleCategory);
    return roleCategory;
  }

  public RoleCategory renameCategory(Long id, String name) {
    RoleCategory roleCategory = roleCategoryRepository.findOne(id);
    roleCategory.setName(name);
    return roleCategoryRepository.save(roleCategory);
  }

  public Role findById(Long id) {
    return roleRepository.findOne(id);
  }

  public Role deleteRole(Long id) {
    roleRepository.delete(id);
    Role role = roleRepository.findOne(id);   
    return role;
  }
  
//  public Role deleteRole(Long id) {
//    Role roleCategory = roleRepository.findOne(id);
//    roleRepository.delete(roleCategory);
//    return roleCategory;
//  }

  public Role renameRole(Long id, String name) {
    Role role = roleRepository.findOne(id);
    role.setName(name);
    return roleRepository.save(role);
  }

  public Role newRole(Long id) {
    Iterable<Role> roles = roleRepository.findAllByOrderByIdDesc();
    RoleCategory roleCategory = roleCategoryRepository.findOne(id);
    String newRole = "新的角色";
    for (int i = 0; i < 10000; i++) {
      String fileName = newRole + (i == 0 ? "" : "(" + i + ")");
      boolean nameExists = false;
      for (Iterator<Role> it = roles.iterator(); it.hasNext();) {
        Role role = it.next();
        if (fileName.equals(role.getName())) {
          nameExists = true;
          break;
        }
      }
      if (!nameExists) {
        newRole = fileName;
        break;
      }
    }
    Role role = new Role();
    role.setName(newRole);
    role.setRoleCategory(roleCategory);
    return roleRepository.save(role);
  }

  public Set<Role> findByRoleCategoryId(Long id) {
    return roleRepository.findByRoleCategoryId(id);   
  }

  public Role findByName(String name) {
    return roleRepository.findByName(name);
    
  }

  public void save(Set<Role> roles) {   
    roleRepository.save(roles);
  }

  public void delete(Set<Role> roles) {
   roleRepository.delete(roles);
  }

}
