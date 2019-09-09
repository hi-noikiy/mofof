package com.mofof.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mofof.entity.system.User;
import com.mofof.entity.system.UserAccount;
import com.mofof.repository.UserAccountRepository;

@Service
public class UserAccountService {

  @Autowired
  private UserAccountRepository userAccountRepository;
  
  public Set<UserAccount> findAll() {
    return userAccountRepository.findAllByOrderByIdDesc();
  }

  public UserAccount findByUserId(long id) {
    return userAccountRepository.findByUserId(id);    
  }

  public UserAccount findByName(String username) {
   UserAccount userAccount = userAccountRepository.findByUsername(username);
    return userAccount;
  }
//  public User findByName(String name) {
//    User user = userRepository.findByUsername(name);
////      User user = new User();
////      user.setUsername("aaa");
////      user.setName("张三");
////       user.setPassword("BI1QBDDzzAqqv2O468f4ZNFo1AYRa22DkPFYWKHrmeI=");
////       user.setSalt("RH66G1RqwbbYw7v088k5wg==");
////      user.setPassword("J/ms7qTJtqmysekuY8/v1TAS+VKqXdH5sB7ulXZOWho=");//密码明文是123456
////      user.setSalt("wxKYXuTPST5SG0jMQzVPsg==");//加密密码的盐值       
//      return user;
//  }

  public UserAccount findById(long id) {   
    return userAccountRepository.findById(id);
  }

  public UserAccount findByPassword(String oldPassword,String salt) {
    UserAccount ua = new UserAccount();
    ua.hashPassword(oldPassword,salt);
    String oldPass = ua.getPassword();
    return userAccountRepository.findByPassword(oldPass);
  }

  public UserAccount save(UserAccount userAccount) {
    userAccount.hashPassword(userAccount.getPassword());
    return userAccountRepository.save(userAccount);    
  }
}
