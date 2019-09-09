package com.mofof.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mofof.repository.MenuItemRepository;
import com.mofof.repository.RoleRepository;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mofof.entity.system.MenuItem;
import com.mofof.entity.system.Role;
import com.mofof.entity.system.RoleModule;

@Service
@Transactional
public class MenuItemService {
  @Autowired
  MenuItemRepository menuItemRepository;
  @Autowired
  RoleRepository roleRepository;

  public Set<MenuItem> getRoleMenuItems(Role role) {
    return this.menuItemRepository.findByRoleId(role.getId());
  }

  public MenuItem save(MenuItem menuItem) {
    return menuItemRepository.save(menuItem);
  }

  public Set<MenuItem> findByRoleId(long id) {
    return menuItemRepository.findByRoleId(id);
  }

  public void deleteByRoleId(long id) {
    menuItemRepository.deleteByRoleId(id);
  }

  public Role addMenuItems(Role role, Set<MenuItem> menuItems) {
    for (MenuItem menuItem : menuItems) {
      menuItem.setRole(role);
    }
    return role;
  }

  public void init(Resource resource) {
    try {
      String menuitemString = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
      JSONArray jsonArray = JSON.parseArray(menuitemString);
      for (int i = 0; i < jsonArray.size(); i++) {
        JSONObject jsonObject = jsonArray.getJSONObject(i);
        JSONObject jsonRole = jsonObject.getJSONObject("role");
        String name = jsonRole.getString("value");
        Role role = roleRepository.findByName(name);
        if (role == null) {
          Role r = new Role();
          r.setName(name);
          role = roleRepository.save(r);
        }
        JSONArray jsonMenus = jsonObject.getJSONArray("menus");
        saveMenus(role, jsonMenus);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void saveMenus(Role role, JSONArray jsonArray) {
    for (int i = 0; i < jsonArray.size(); i++) {
      MenuItem menuItem = new MenuItem();
      Object jsonMenuItem = jsonArray.get(i);
      if (jsonMenuItem instanceof JSONObject) {
        JSONObject jsonMenu = (JSONObject) jsonMenuItem;
        menuItem.setName(jsonMenu.getString("name"));
        if (jsonMenu.containsKey("icon")) {
          menuItem.setIcon(jsonMenu.getString("icon"));
        }
        if (jsonMenu.containsKey("href")) {
          menuItem.setHref(jsonMenu.getString("href"));
        }
        if (jsonMenu.containsKey("moduleGroup")) {
          menuItem.setSysGroup(jsonMenu.getString("moduleGroup"));
        }
        menuItem.setRole(role);
        menuItem = menuItemRepository.save(menuItem);

        JSONArray jsonSubMenus = jsonMenu.getJSONArray("subMenus");
        saveSubMenus(menuItem, jsonSubMenus, role);
      }
    }
  }

  public void saveSubMenus(MenuItem menuItem, JSONArray jsonArray, Role role) {
    if (jsonArray == null)
      return;
    for (int i = 0; i < jsonArray.size(); i++) {
      MenuItem mi = new MenuItem();
      Object jsonMenuItem = jsonArray.get(i);
      if (jsonMenuItem instanceof JSONObject) {
        JSONObject jsonMenu = (JSONObject) jsonMenuItem;
        mi.setName(jsonMenu.getString("name"));
        if (jsonMenu.containsKey("icon")) {
          mi.setIcon(jsonMenu.getString("icon"));
        }
        if (jsonMenu.containsKey("href")) {
          mi.setHref(jsonMenu.getString("href"));
        }
        if (jsonMenu.containsKey("moduleGroup")) {
          mi.setSysGroup(jsonMenu.getString("moduleGroup"));
        }
        mi.setRole(role);
        mi.setParentMenuItem(menuItem);
        menuItemRepository.save(mi);

        JSONArray jsonSubMenus2 = jsonMenu.getJSONArray("subModules");
        saveSubMenus(mi, jsonSubMenus2, role);
      }
    }
  }
}