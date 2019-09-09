package com.mofof.controller;

import java.util.Arrays;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mofof.entity.system.Module;
import com.mofof.service.ModuleService;
import com.mofof.util.GsonBuilderFactory;

@RestController
public class ModuleController {

  @Autowired
  private ModuleService moduleService;

//  @GetMapping("/modules")
//  public String index() {
//    Set<Module> modules = moduleService.findAll();
//    return GsonBuilderFactory.createGsonBuilder(
//        Arrays.asList("parentModule","createTime","creator")
//      ).toJson(modules);
//  }
}
