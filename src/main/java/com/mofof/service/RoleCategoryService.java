package com.mofof.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mofof.entity.system.RoleCategory;
import com.mofof.repository.RoleCategoryRepository;
import com.mofof.repository.RoleRepository;

@Service
public class RoleCategoryService {
  
  @Autowired
  private RoleCategoryRepository roleCategoryRepository;
  @Autowired
  RoleRepository roleRepository;
  
  public Set<RoleCategory> findAll(){
    return roleCategoryRepository.findAll();
  }

  public RoleCategory save(RoleCategory roleCategory){
    return roleCategoryRepository.save(roleCategory);
  }
  
  public RoleCategory findById(long id){
    return roleCategoryRepository.findOne(id);
  }

  public RoleCategory findByName(String name) {    
    return roleCategoryRepository.findByName(name);
  }

  public RoleCategory delete(long id) {
    roleRepository.delete(roleRepository.findByRoleCategoryId(id));
    roleCategoryRepository.delete(id);   
    return roleCategoryRepository.findOne(id);   
  }
}
