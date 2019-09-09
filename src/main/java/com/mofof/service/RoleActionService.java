package com.mofof.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mofof.entity.system.ActionItem;
import com.mofof.entity.system.MenuItem;
import com.mofof.entity.system.RoleAction;
import com.mofof.entity.system.RoleModule;
import com.mofof.repository.MenuItemRepository;
import com.mofof.repository.RoleActionRepository;
import com.mofof.repository.RoleModuleRepository;

@Service
public class RoleActionService {
  
  @Autowired
  private RoleActionRepository roleActionItemRepository;
  @Autowired
  private MenuItemRepository menuItemRepository;
  @Autowired
  private RoleModuleRepository roleModuleRepository;
  @Autowired
  private ActionItemService actionItemService;
  
//  public void init() {
//    RoleModule roleModule = roleModuleRepository.findBySysKey("fund.list");
//    MenuItem menuItem = menuItemRepository.findByValue("/fund ");
//    RoleAction rat = new RoleAction();
//    rat.setRoleModule(roleModule);
//    List<ActionItem> actionItems = actionItemService.findBySysId("module/path@add");
//    rat = RoleAction.copyActionItem(actionItems.get(0));  
//    rat.setMenuItem(menuItem);
//    roleActionItemRepository.save(rat);
//  }

}
