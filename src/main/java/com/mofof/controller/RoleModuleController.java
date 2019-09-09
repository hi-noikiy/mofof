package com.mofof.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mofof.service.RoleModuleService;
import com.mofof.util.GsonBuilderFactory;

@RestController
public class RoleModuleController {

  @Autowired
  private RoleModuleService roleModuleService;
  
//  @GetMapping("/menu_items/{id}/role_modules")
//  public String index(@PathVariable long id) {
//    return GsonBuilderFactory.createGsonBuilder(
//        Arrays.asList("menuItem","createTime","parentRoleModule")
//      ).toJson(roleModuleService.findByItemId(id));
//  }
  
  @GetMapping("/roles/{id}/menu_items/{sysGroup}/role_modules")
  public String index(@PathVariable long id, @PathVariable String sysGroup) {   
    return GsonBuilderFactory.createGsonBuilder(
      Arrays.asList("menuItem","createTime","parentRoleModule","role","roleModule")
    ).toJson(roleModuleService.findByRoleIdAndSysGroup(id, sysGroup));
    
  }
  
  @GetMapping("/roles/{id}/role_modules")
  public String indexTab(@PathVariable long id) {   
    return GsonBuilderFactory.createGsonBuilder(
        Arrays.asList("menuItem","createTime","parentRoleModule","role","roleModule")
      ).toJson(roleModuleService.findByRoleId(id));   
  } 
}
