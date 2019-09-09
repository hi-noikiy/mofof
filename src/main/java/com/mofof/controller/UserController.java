package com.mofof.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mofof.entity.system.Department;
import com.mofof.entity.system.Role;
import com.mofof.entity.system.User;
import com.mofof.entity.system.UserAccount;
import com.mofof.service.DepartmentService;
import com.mofof.service.DirMetadataService;
import com.mofof.service.DirectoryService;
import com.mofof.service.RoleService;
import com.mofof.service.UserAccountService;
import com.mofof.service.UserService;
import com.mofof.util.GsonBuilderFactory;
import com.mofof.util.Json;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 用户控制 Created by hzh on 2018/10/1.
 */
@RestController
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;
	@Autowired
	UserAccountService userAccountService;
	@Autowired
	DirectoryService directoryService;
	@Autowired
	DirMetadataService dirMetadataService;
	@Autowired 
	RoleService roleService;
	@Autowired
	DepartmentService departmentService;
	
  @ApiOperation(value = "保存/更新用户", notes = "保存/更新用户")
  @PostMapping(path = "/user/save")
  @Transactional
  public String saveRecord(@RequestBody User user) {
    UserAccount userAccount = user.getUserAccount();
    // 生成随机密码
    SecureRandomNumberGenerator rng = new SecureRandomNumberGenerator();
    String pwd = rng.nextBytes().toString();
    userAccount.setPassword(pwd);

    Role role = user.getRole();
    if (role != null) {
      user.setRole(roleService.findById(role.getId()));
    }

    List<Department> departments = user.getDepartments();
    if (departments != null) {
      List<Department> depts = new ArrayList<Department>();
      for (Department department : departments) {
        depts.add(departmentService.findById(department.getId()));
      }
      user.setDepartments(depts);
    }
    List<Department> managedDepartments = user.getManagedDepartments();
    if (managedDepartments != null) {
      List<Department> mdepts = new ArrayList<Department>();
      for (Department department : managedDepartments) {
        mdepts.add(departmentService.findById(department.getId()));
      }
      user.setManagedDepartments(mdepts);
    }
    user = userService.save(user);
    userAccount.setUser(user);
    userAccountService.save(userAccount);
    user.setUserAccount(userAccount);
    return GsonBuilderFactory.createGsonBuilder(
        Arrays.asList("password", "createTime", "salt", "roleCategory", "user",
            "calEvents", "managedByusers", "parent", "subDepartments"))
        .toJson(user);
  }

  @PutMapping("/users/{id}")
  public String update(@PathVariable long id, @RequestBody User user) {
    user.setId(id);
    UserAccount ua = user.getUserAccount();
    UserAccount userAccount = userAccountService.findById(ua.getId());
    userAccount.setUsername(ua.getUsername());
    
    Role role = user.getRole();
    if (role != null) {
      user.setRole(roleService.findById(role.getId()));
    }

    List<Department> departments = user.getDepartments();
    if (departments != null) {
      List<Department> depts = new ArrayList<Department>();
      for (Department department : departments) {
        depts.add(departmentService.findById(department.getId()));
      }
      user.setDepartments(depts);
    }
    List<Department> managedDepartments = user.getManagedDepartments();
    if (managedDepartments != null) {
      List<Department> mdepts = new ArrayList<Department>();
      for (Department department : managedDepartments) {
        mdepts.add(departmentService.findById(department.getId()));
      }
      user.setManagedDepartments(mdepts);
    }

    user = userService.save(user);
    userAccount.setUser(user);
    userAccountService.save(userAccount);
    user.setUserAccount(userAccount);
    return GsonBuilderFactory.createGsonBuilder(
        Arrays.asList("password", "createTime", "salt", "roleCategory", "user",
            "calEvents", "managedByusers", "parent", "subDepartments"))
        .toJson(user);
  }
  
//	@ApiOperation(value = "保存/更新用户账户信息", notes = "保存/更新用户账户信息")
//	@PostMapping(path = "/saveInfo")
//	public UserAccount saveInfo(@RequestBody UserAccount account) {
//		return userService.saveUserAccount(account);
//	}

	@ApiOperation(value = "获取所有用户", notes = "获取所有用户")
	@GetMapping(path = "/user/all")
	public String allUsers() {
	  Iterable<User> users = userService.findAll();
	  for (User user : users) {
      UserAccount userAccount = userAccountService.findByUserId(user.getId());     
      user.setUserAccount(userAccount);
    }
	  return GsonBuilderFactory.createGsonBuilder(
	      Arrays.asList("password", "createTime", "salt", "roleCategory", "user", "calEvents",
	          "managedByusers", "parent", "subDepartments")
	    ).toJson(users);
	}

	@ApiOperation(value = "获取用户", notes = "根据User的id来获取用户")
	@ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long", paramType = "path")
	@GetMapping(path = "/user/user")
	public User getUser(Long id) {
		return userService.findById(id);
	}

	@ApiOperation(value = "删除用户", notes = "根据User的id来删除用户")
	@ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long", paramType = "path")
	@DeleteMapping(path = "/user/user/{id}")
	public void delUser(@PathVariable Long id) {
		userService.delete(id);
	}

	@ApiOperation(value = "删除所有用户", notes = "删除用户")
	@DeleteMapping(path = "/user/user/all")
	public void delAllUser() {
		userService.deleteAll();
	}

	@ApiOperation(value = "获取所有用户账户", notes = "获取所有用户账户")
	@GetMapping(path = "/user/allAccounts")
	public Iterable<UserAccount> allUserAccounts() {
		return userService.findAllAccount();
	}


	@GetMapping("/user/session")
	public Json session() {
		Session session = SecurityUtils.getSubject().getSession();
		long id  = Long.parseLong(session.getAttribute("userAccountId").toString());
		UserAccount userAccount = userAccountService.findById(id);
		Set<String> set = new HashSet<String>();
		set.add("/*");
    return Json.ok("login").data("session_id", session.getId()).data("id", userAccount.getId())
				   			   .data("name", userAccount.getUser().getName())
				   			   .data("role", userAccount.getUser().getRole().getName())
				   			   .data("perms", userService.getPerms(userAccount.getUser().getRole()))
				   			   .data("menuItems", set);
				   			   //.data("menuItems", userService.getMenuItems(userAccount.getUser().getRole()));
	}
	
  
  @GetMapping("/user_accounts")
  public String index() {
    Session session = SecurityUtils.getSubject().getSession();
    long id  = (long) session.getAttribute("userAccountId");
    UserAccount userAccount = userAccountService.findById(id);
    
    return GsonBuilderFactory.createGsonBuilder(
        Arrays.asList("createTime","creator","departments",
            "managedDepartments","role","calEvents","password","salt")
      ).toJson(userAccount);
  }

  @PutMapping("/user_accounts/phonenum")
  public String update(@RequestBody String num) {
    JSONObject jsonNum = (JSONObject)JSON.parse(num);
    String oldPhone = jsonNum.getString("oldPhone");
    String phone = jsonNum.getString("phone");
    Session session = SecurityUtils.getSubject().getSession();
    long id  = (long) session.getAttribute("userAccountId");
    UserAccount userAccount = userAccountService.findById(id);
    User user = userAccount.getUser();
    User u = userService.findByPhone(oldPhone);
    if (u !=null) {   
      user.setPhone(phone);    
      user = userService.save(user);
    }
    return GsonBuilderFactory.createGsonBuilder(
        Arrays.asList("createTime", "creator", "departments", "managedDepartments", "role", "calEvents")
      ).toJson(user);
  } 
  
}
