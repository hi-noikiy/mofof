package com.mofof.controller;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mofof.entity.system.UserAccount;
import com.mofof.service.UserAccountService;
import com.mofof.service.UserService;
import com.mofof.util.GsonBuilderFactory;
import com.mofof.util.Json;



@RestController
public class UserAccountController {
  private static final Logger log = LoggerFactory.getLogger(UserController.class);
  
  @Autowired
  private UserAccountService userAccountService;
  @Autowired
  private UserService userService;
  
  @PostMapping("/user/login")
  public Json login(@RequestBody String body) {
    String oper = "user login";
    log.info("{}, body: {}", oper, body);

    JSONObject json = JSON.parseObject(body);
    String uname = json.getString("username");
    String pwd = json.getString("password");
    if (StringUtils.isEmpty(uname)) {
      return Json.error("username must not empty");
    }
    if (StringUtils.isEmpty(pwd)) {
      return Json.error("password must not empty");
    }

    Subject currentUser = SecurityUtils.getSubject();
    try {
      // Login
      currentUser.login(new UsernamePasswordToken(uname, pwd));
      UserAccount userAccount = (UserAccount) currentUser.getPrincipal();
      if (userAccount == null) {
        throw new AuthenticationException();
      }
      currentUser.getSession().setAttribute("userAccountId", userAccount.getId());
//      Set<String> set = new HashSet<String>();
//      set.add("/*");
      return Json.ok("login").data("id", userAccount.getId())
                   .data("name", userAccount.getUser().getName())
                   .data("role", userAccount.getUser().getRole().getName())
                   .data("perms", userService.getPerms(userAccount.getUser().getRole()))
//                 .data("menuItems", set);
                   .data("menuItems", userService.getMenuItems(userAccount.getUser().getRole()));

    } catch (UnknownAccountException uae) {
      return Json.error("unknown account");

    } catch (IncorrectCredentialsException ice) {
      return Json.error("incorrect credentials");

    } catch (LockedAccountException lae) {
      return Json.error("locked account");

    } catch (AuthenticationException ae) {
      return Json.error(ae.getMessage());
    }

  }

  @PostMapping("/user/logout")
  public Json logout() {
    Subject currentUser = SecurityUtils.getSubject();
    UserAccount userAccount = (UserAccount) currentUser.getPrincipal();
    if (userAccount != null) {
      currentUser.logout();
      log.info("User {} logged-out", userAccount.getUsername());
    }

    return Json.ok("logout");
  }
  
  @PutMapping("/user_accounts/password")
  public String update(@RequestBody String pass) {
    Object parse = JSON.parse(pass);
    JSONObject jsonObject = (JSONObject)parse;
    String oldPassword = jsonObject.getString("oldPassword");
    String password = jsonObject.getString("password");
    Session session = SecurityUtils.getSubject().getSession();
    long id  = (long) session.getAttribute("userAccountId");
    UserAccount userAccount = userAccountService.findById(id);
    String salt = userAccount.getSalt();
    UserAccount ua = userAccountService.findByPassword(oldPassword, salt);
    if (ua != null) {
      userAccount.setPassword(password);
      userAccount.setUpdatedAt(LocalDateTime.now());
      userAccount.setPasswordStatus(UserAccount.MODIFIED);
      userAccount = userAccountService.save(userAccount); 
    }
    return GsonBuilderFactory.createGsonBuilder(
        Arrays.asList("createTime","creator","user","password","salt")
      ).toJson(userAccount);
  }
}
