package com.mofof.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.core.io.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonElement;
import com.mofof.entity.system.MenuItem;
import com.mofof.entity.system.Module;
import com.mofof.entity.system.Role;
import com.mofof.entity.system.RoleAction;
import com.mofof.entity.system.RoleCategory;
import com.mofof.entity.system.RoleModule;
import com.mofof.repository.MenuItemRepository;
import com.mofof.repository.RoleActionRepository;
import com.mofof.repository.RoleCategoryRepository;
import com.mofof.repository.RoleModuleRepository;
import com.mofof.repository.RoleRepository;

@Service
public class RoleModuleService {

  @Autowired
  private RoleModuleRepository roleModuleRepository;
  @Autowired
  private RoleRepository roleRepository;
  @Autowired
  private MenuItemRepository menuItemRepository;
  @Autowired
  private ModuleService moduleService;
  @Autowired
  RoleActionRepository roleActionRepository;
  @Autowired
  RoleCategoryRepository roleCategoryRepository;

  StringBuffer buffer = new StringBuffer();

  public void init(Resource resource) {

    try {
      String moduleString = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
      JSONArray jsonArray = JSON.parseArray(moduleString);
      for (int i = 0; i < jsonArray.size(); i++) {
        buffer.setLength(0);
        JSONObject jsonObject = jsonArray.getJSONObject(i);
        JSONObject jsonRole = jsonObject.getJSONObject("role");
        String value = jsonRole.getString("value");
        Role role = roleRepository.findByName(value);
        if (role == null) {
          RoleCategory roleCategory = roleCategoryRepository.findOne(1L);
          Role r = new Role();
          r.setName(value);
          r.setRoleCategory(roleCategory);
          role = roleRepository.save(r);
        }
        JSONArray jsonModules = jsonObject.getJSONArray("modules");
        saveModules(role, jsonModules);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void saveModules(Role role, JSONArray jsonArray) {
    for (int i = 0; i < jsonArray.size(); i++) {
      buffer.setLength(0);
      RoleModule rm = new RoleModule();
      Object jsonModule2 = jsonArray.get(i);
      if (jsonModule2 instanceof JSONObject) {
        JSONObject jsonModule = (JSONObject) jsonModule2;
        rm.setName(jsonModule.getString("moduleName"));
        if (jsonModule.containsKey("moduleKey")) {
          rm.setSysKey(jsonModule.getString("moduleKey"));
        }
        if (jsonModule.containsKey("tabName")) {
          // 创建或者查找对应的tab
          String tabName = jsonModule.getString("tabName");
          if(tabName != null && !("".equals(tabName))) {
            RoleModule tab = findOrCreate(role, tabName, jsonModule.getString("moduleGroup"));
            rm.setParentRoleModule(tab);
          } 
        }        
        rm.setSysGroup(jsonModule.getString("moduleGroup"));
        
        rm.setModType(Module.MOD_TYPE);
        rm.setRole(role);
        rm = roleModuleRepository.save(rm);
        buffer.append(jsonModule.getString("moduleKey") + "/");

        JSONArray jsonSubModules2 = jsonModule.getJSONArray("subModules");
        JSONArray jsonBtns = jsonModule.getJSONArray("btns");
        saveRoleAction(jsonBtns, rm, buffer);
        saveSubModules(rm, jsonSubModules2, role);
      }
    }
  }

  public void saveSubModules(RoleModule roleModule, JSONArray jsonArray, Role role) {
    if (jsonArray == null)
      return;
    for (int i = 0; i < jsonArray.size(); i++) {
      StringBuffer buffer2 = new StringBuffer();

      buffer2 = buffer2.append(buffer);
      RoleModule rm = new RoleModule();
      Object jsonModule2 = jsonArray.get(i);
      if (jsonModule2 instanceof JSONObject) {
        JSONObject jsonModule = (JSONObject) jsonModule2;
        rm.setName(jsonModule.getString("moduleName"));
        if (jsonModule.containsKey("moduleKey")) {
          rm.setSysKey(jsonModule.getString("moduleKey"));
        }
        if (jsonModule.containsKey("tabName")) {
          // 创建或者查找对应的tab
          String tabName = jsonModule.getString("tabName");
          if(tabName != null && !("".equals(tabName))) {
            RoleModule tab = findOrCreate(roleModule, tabName);
            rm.setParentRoleModule(tab);
          } else {
            rm.setParentRoleModule(roleModule);
          }        
        }      
        rm.setSysGroup(jsonModule.getString("moduleGroup"));
        rm.setModType(Module.MOD_TYPE);
        rm.setRole(role);
        //rm.setParentRoleModule(roleModule);
        roleModuleRepository.save(rm);
        buffer2.append(jsonModule.getString("moduleKey") + "/");

        JSONArray jsonSubModules2 = jsonModule.getJSONArray("subModules");
        JSONArray jsonBtns = jsonModule.getJSONArray("btns");
        saveRoleAction(jsonBtns, rm, buffer2);
        buffer2.setLength(0);
        saveSubModules(rm, jsonSubModules2, role);
      }
    }
  }

  public void saveRoleAction(JSONArray jsonArray, RoleModule roleModule, StringBuffer btnSysId) {
    if (jsonArray == null)
      return;
    for (int i = 0; i < jsonArray.size(); i++) {
      Object jsonAction = jsonArray.get(i);
      RoleAction action = new RoleAction();
      if (jsonAction instanceof JSONObject) {
        JSONObject jsonRoleAction = (JSONObject) jsonAction;
        action.setName(jsonRoleAction.getString("btnName"));
        action.setSysId(btnSysId.toString() + jsonRoleAction.getString("btnKey"));
        action.setRoleModule(roleModule);
        roleActionRepository.save(action);

      }
    }
  }

  public RoleModule findOrCreate(RoleModule rm, String tabName) {   
    RoleModule rModule = roleModuleRepository.findByParentIdAndTabName(rm.getId(), tabName );
    if (rModule == null) {
      rModule = RoleModule.newTab(rm, tabName);
      rModule = roleModuleRepository.save(rModule);
    }  
    return rModule;
  }
  
  public RoleModule findOrCreate(Role role, String tabName, String sysGroup) {
    RoleModule rModule = roleModuleRepository.findByTabName(tabName );
    if (rModule == null) {
      rModule = RoleModule.newTab(role, tabName, sysGroup);
      rModule = roleModuleRepository.save(rModule);
    } 
    return rModule;
  }
  
  public boolean existsBySysKey(String sysKey) {    
    return roleModuleRepository.existsBySysKey(sysKey);
  }

  public Set<RoleModule> findByRoleId(long id) {
    return roleModuleRepository.findByRoleId(id);
  }

  public Set<RoleModule> findByRoleIdAndSysGroup(long id, String sysGroup) {   
    return roleModuleRepository.findByRoleIdAndSysGroup(id, sysGroup);
  }
}
