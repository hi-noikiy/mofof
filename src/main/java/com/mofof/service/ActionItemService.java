package com.mofof.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
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
import com.mofof.entity.system.ActionItem;
import com.mofof.entity.system.Module;
import com.mofof.repository.ActionItemRepository;
import com.mofof.repository.ModuleRepository;

@Service
public class ActionItemService {

  @Autowired
  private ActionItemRepository actionItemRepository;
  @Autowired
  private ModuleRepository moduleRepository;
  
  public void init() {
    Set<ActionItem> actionItems = new HashSet<ActionItem>();
    ActionItem item = new ActionItem();
    Module module = moduleRepository.findOne(1L);
    item.setName("/system/user@add@edit");
    item.setModule(module);
    actionItemRepository.save(item);
  }
  
  public void initActionItem(Resource resource) {

    try {
      String ActionItemString = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
      JSONArray jsonArray = JSON.parseArray(ActionItemString);
      Set<ActionItem> actionItems = new HashSet<ActionItem>();
      for (int i = 0; i < jsonArray.size(); i++) {
        JSONObject jsonObject = jsonArray.getJSONObject(i);
        ActionItem actionItem = new ActionItem();       
        actionItem.setName(jsonObject.getString("name"));
        actionItem.setSysId(jsonObject.getString("sysId"));       
        JSONObject jsonAction = jsonObject.getJSONObject("module");
        if (jsonAction.containsKey("sysKey")) {
         String sysKey = jsonAction.getString("sysKey");
         Module module = moduleRepository.findBySysKey(sysKey);
         actionItem.setModule(module);
        }        
        actionItems.add(actionItem);
      }
      actionItemRepository.save(actionItems);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public List<ActionItem> findBySysId(String sysId) {
    return actionItemRepository.findBySysId(sysId);    
  }
}
