package com.mofof.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mofof.entity.dict.ext.DictExtCity;
import com.mofof.entity.dict.ext.DictExtContinent;
import com.mofof.entity.dict.ext.DictExtCountry;
import com.mofof.entity.dict.ext.DictExtDistrict;
import com.mofof.entity.dict.ext.DictExtProvince;
import com.mofof.entity.system.Module;
import com.mofof.repository.ModuleRepository;

@Service
public class ModuleService {

  @Autowired
  private ModuleRepository moduleRepository;

  public void init(Resource resource) {
    try {
      String moduleString = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
      JSONArray jsonArray = JSON.parseArray(moduleString);
      Set<Module> modules = new HashSet<Module>();

      for (int i = 0; i < jsonArray.size(); i++) {

        JSONObject jsonObject = jsonArray.getJSONObject(i);
        Module module = saveModule(jsonObject);

        JSONArray jsonSubModules = jsonObject.getJSONArray("subModules");
        saveSubModules(module, jsonSubModules);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  private Module saveModule(JSONObject jsonObject) {
    Module module = new Module();
    module.setName(jsonObject.getString("name"));
    module.setSysKey(jsonObject.getString("sysKey"));
    if ("TAB".equals(jsonObject.getString("type"))) {
      module.setModType(Module.TAB_TYPE);
    } else {
      module.setModType(Module.MOD_TYPE);
    }
    return moduleRepository.save(module);
  }

  public void saveSubModules(Module module, JSONArray jsonSubModules) {
    if(jsonSubModules == null) return;
    
    for (int i = 0; i < jsonSubModules.size(); i++) {
      
      Module m2 = new Module();
      Object jsonModule2 = jsonSubModules.get(i);
      if (jsonModule2 instanceof JSONObject) {
        JSONObject jsonModule = (JSONObject) jsonModule2;
        m2.setName(jsonModule.getString("name"));
        if (jsonModule.containsKey("sysKey")) {
          m2.setSysKey(jsonModule.getString("sysKey"));
        }
        if ("TAB".equals(jsonModule.getString("type"))) {
          m2.setModType(Module.TAB_TYPE);
        } else {
          m2.setModType(Module.MOD_TYPE);
        }  
        m2.setParentModule(module);
        moduleRepository.save(m2);
        
        JSONArray jsonSubModules2 = jsonModule.getJSONArray("subModules");
        saveSubModules(m2, jsonSubModules2);
      }      
    }
  }

  public boolean existsBySysKey(String sysKey) {   
    return moduleRepository.existsBySysKey(sysKey);
  }

  public Module findBySysKey(String sysKey) {
    return moduleRepository.findBySysKey(sysKey);   
  }
}
