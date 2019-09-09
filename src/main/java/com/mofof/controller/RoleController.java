package com.mofof.controller;

import java.util.Arrays;
import java.util.List;
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
import com.mofof.repository.RoleCategoryRepository;
import com.mofof.repository.RoleRepository;
import com.mofof.service.RoleCategoryService;
import com.mofof.service.RoleService;
import com.mofof.service.UserService;
import com.mofof.util.GsonBuilderFactory;

/**
 * Created by Qian, Wenliang on 2017/8/17.
 */
@RestController
public class RoleController extends APIController {

	@Override
	protected List<String> gsonIgnoreFields() {
		String[] ignores = new String[] {"users", "perms","menuItems","createTime","roleCategory"};
		return Arrays.asList(ignores);
	}
	@Autowired
	RoleService roleService;
	@Autowired
	UserService userService;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	RoleCategoryRepository roleCategoryRepository;
	@Autowired
	RoleCategoryService roleCategoryService;

  @PostMapping(path = "/role/save")
  public Role saveRecord(@RequestBody Role role) {
    Role r = roleService.findByName(role.getName());
    if (r != null) {
      if (r.getDel() == 1) {
        r.setDel(0);
        return roleService.save(r);
      } else {
        return r;
      }
    }
    return roleService.save(role);
  }

	@GetMapping(path = "/role/all")
	public Iterable<Role> allRoles() {
		return roleService.findAll();
	}
	
	@PostMapping("/role_categories/roles/mv/{id}")
	public String move(@PathVariable long id, @RequestBody Role role) {
	  RoleCategory roleCategory = roleCategoryService.findById(id);
	  Role r = roleService.findById(role.getId());
	  if(roleCategory != null) {    
	    r.setRoleCategory(roleCategory);
	    r = roleService.save(r);
	  }
	  return GsonBuilderFactory.createGsonBuilder(
        Arrays.asList("roles", "createTime", "creator"))
        .toJson(r);
	}
	
  @PostMapping("/roles/{id}/rename")
  public String rename(@PathVariable long id, @RequestBody Role r) {
    Role role = roleService.findById(id);
    Role role2 = roleService.findByName(r.getName());
    if (role2 != null) {
      return "name is exist";
    }
    if (role != null) {
      role.setName(r.getName());
    }
    roleService.save(role);

    return GsonBuilderFactory
        .createGsonBuilder(
            Arrays.asList("roleCategory", "createTime", "creator"))
        .toJson(role);
  }
	
	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
  @DeleteMapping("/roles/{id}")
  public Long delete(@PathVariable long id) {
    Set<User> users = userService.findByRoleId(id);
    for (User user : users) {
      user.setRole(null);
    }
    userService.save(users);
    Role role = roleService.deleteRole(id);
    
    if (role == null) {
      return id;
    } else {
      return 0l;
    }
  }	

  
	@PostMapping(path = "/role/roleCategorys")
	public String findAllCategory() {
		Iterable<RoleCategory> roleCategorys = roleCategoryRepository.findAll();
		for (RoleCategory roleCategory : roleCategorys) {
      Set<Role> roles = roleService.findByRoleCategoryId(roleCategory.getId());
      roleCategory.setRoles(roles);
    }
		return this.gson().toJson(roleCategorys);
	}

	@GetMapping(path = "/role/role")
	public Role getRole(Long id) {
		return roleService.findById(id);
	}

	@GetMapping(path = "/role/newCategory")
	public RoleCategory newCategory(Long id) {
		return roleService.newCategory(id);
	}

	@GetMapping(path = "/role/deleteCategory")
	public RoleCategory deleteCategory(Long id) {
		return roleService.deleteCategory(id);
	}

	@GetMapping(path = "/role/renameCategory")
	public RoleCategory renameCategory(Long id, String name) {
		return roleService.renameCategory(id, name);
	}

	@GetMapping(path = "/role/newRole")
	public Role newRole(Long id) {
		return roleService.newRole(id);
	}

	@GetMapping(path = "/role/deleteRole")
	public Role deleteRole(Long id) {
		return roleService.deleteRole(id);
	}

	@GetMapping(path = "/role/renameRole")
	public Role renameRole(Long id, String name) {
		return roleService.renameRole(id, name);
	}

}
