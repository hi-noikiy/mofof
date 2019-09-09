package com.mofof.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.mofof.entity.system.MenuItem;
import com.mofof.entity.system.Perm;
import com.mofof.entity.system.Role;
import com.mofof.entity.system.User;
import com.mofof.entity.system.UserAccount;
import com.mofof.repository.DepartmentRepository;
import com.mofof.repository.MenuItemRepository;
import com.mofof.repository.PermRepository;
import com.mofof.repository.UserAccountRepository;
import com.mofof.repository.UserRepository;
import com.mofof.util.GsonBuilderFactory;

/**
 * Created by ChenErliang on 2017/6/10.
 */
@Service
@Transactional
public class UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserAccountRepository userAccountRepository;

  @Autowired
  private DepartmentRepository departmentRepository;

  @Autowired
  private MenuItemRepository menuItemRepository;

  @Autowired
  private PermRepository permRepository;

  public void delete(Long id){ userRepository.delete(id);}

  public void deleteAll(){ userRepository.deleteAll();}

  public UserAccount save(UserAccount userAccount) {
    String pass = userAccount.getPassword();
    userAccount.hashPassword(pass);
    return userAccountRepository.save(userAccount);
  }

  public Iterable<User> findAll() {
    return userRepository.findAllByOrderByIdDesc();
  }

  public User findById(Long id) {
    return userRepository.findOne(id);
  }

  public User save(User user) {
    return userRepository.save(user);
  }

  public Iterable<UserAccount> findAllAccount() {
    return userAccountRepository.findAllByOrderByIdDesc();
  }

  public User findByPhone(String oldPhone) {
    return userRepository.findByPhone(oldPhone);
  }

  public List<MenuItem> getMenuItems(Role role) {
//      Set<MenuItem> set = new HashSet<>();
//      if (role != null) {
    Set<MenuItem> set = menuItemRepository.findByRoleId(role.getId());
//      }
    String menuJson = GsonBuilderFactory.createGsonBuilder(
       Arrays.asList("role","createTime","creator","role","parentMenuItem")
     ).toJson(set);
    List<MenuItem> menuitems = JSON.parseArray(menuJson, MenuItem.class);
    return menuitems;
  }

  public Set<String> getPerms(Role role) {
    Set<String> set = new HashSet<>();
    if (role != null) {
      Set<Perm> perms = permRepository.findPermsByRoleId(role.getId());
      for (Perm perm : perms) {
        set.add(perm.getValue());
      }
    }
    return set;
  }
//    public UserAccount saveUserAccount(UserAccount userAccount) {
//        List<Department>  depts = new ArrayList<Department>();	
//    	for(Iterator<Department> it = userAccount.getDepartments().iterator();it.hasNext();){
//    		Department dept = it.next();
//    		depts.add(departmentRepository.findOne(dept.getId()));
//    	}
//    	List<Department> managedDepts = new ArrayList<Department>();	
//    	for(Iterator<Department> it = userAccount.getManagedDepartments().iterator();it.hasNext();){
//    		Department dept = it.next();
//    		managedDepts.add(departmentRepository.findOne(dept.getId()));
//    	}
//    	userAccount.setDepartments(depts);
//    	userAccount.setManagedDepartments(managedDepts);
//        User user = userAccount.getUser();
//        user.hashPassword();
//        userAccount.setUser(user);
//    	return userAccountRepository.save(userAccount);
//    }

  public Set<User> findByRoleId(long id) {
    return userRepository.findByRoleId(id);   
  }

  public void save(Set<User> users) {
    userRepository.save(users);   
  }
}
