package com.mofof.controller;

import java.util.Arrays;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mofof.entity.system.Role;
import com.mofof.entity.system.RoleCategory;
import com.mofof.entity.system.User;
import com.mofof.service.RoleCategoryService;
import com.mofof.service.RoleService;
import com.mofof.service.UserService;
import com.mofof.util.GsonBuilderFactory;

@RestController
public class RoleCategoryController {
  
  @Autowired
  private RoleCategoryService roleCategoryService;
  @Autowired
  private RoleService roleService;
  @Autowired
  UserService userService;
  
  @GetMapping("/role_categories")
  public String index() {
    Set<RoleCategory> roleCategories = roleCategoryService.findAll();
    for (RoleCategory roleCategory : roleCategories) {
      Set<Role> roles = roleService.findByRoleCategoryId(roleCategory.getId());
      roleCategory.setRoles(roles);
    }
    return GsonBuilderFactory.createGsonBuilder(
        Arrays.asList("roleCategory", "createTime", "creator")
      ).toJson(roleCategories);
  }

  @PostMapping("/role_categories")
  public String create(@RequestBody RoleCategory roleCategory) {
    
    return GsonBuilderFactory.createGsonBuilder(
        Arrays.asList("roleCategory", "createTime", "creator")
      ).toJson(roleCategoryService.save(roleCategory));
  }
  
  @PostMapping("/role_categories/{id}/roles")
  public String createRole(@PathVariable long id, @RequestBody Role role) {
    RoleCategory roleCategory = roleCategoryService.findById(id);
    Role r = roleService.findByName(role.getName());
    if (roleCategory != null) {
      if (r != null) {
        if (r.getDel() == 1) {
          r.setDel(0);
        }
        role = r;
      }
      role.setRoleCategory(roleCategory);
      role = roleService.save(role);
      return GsonBuilderFactory
          .createGsonBuilder(
              Arrays.asList("roleCategory", "createTime", "creator"))
          .toJson(role);
    }
    return null;
  }

  @DeleteMapping("/role_categories/{id}")
  public Long delete(@PathVariable long id) {
    Set<Role> roles = roleService.findByRoleCategoryId(id);
    for (Role role : roles) {
      Set<User> users = userService.findByRoleId(role.getId());
      for (User user : users) {
        user.setRole(null);
      }
      userService.save(users);
      role.setRoleCategory(null);
    }
    roleService.save(roles);
    roleService.delete(roles);

    RoleCategory roleCategory = roleCategoryService.delete(id);
    if (roleCategory == null) {
      return id;
    } else {
      return 0l;
    }
  }
  
  @PostMapping("/role_categories/{id}/rename")
  public String rename(@PathVariable long id, @RequestBody RoleCategory rc) {
    RoleCategory roleCategory = roleCategoryService.findById(id);
    if (roleCategory != null) {
      roleCategory.setName(rc.getName());
    }
    return GsonBuilderFactory
        .createGsonBuilder(
            Arrays.asList("roleCategory", "createTime", "creator"))
        .toJson(roleCategoryService.save(roleCategory));
  }

}
