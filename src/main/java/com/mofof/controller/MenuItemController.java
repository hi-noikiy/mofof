package com.mofof.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mofof.entity.system.MenuItem;
import com.mofof.entity.system.Role;
import com.mofof.service.MenuItemService;
import com.mofof.service.RoleService;
import com.mofof.util.GsonBuilderFactory;



@RestController
public class MenuItemController extends APIController {
	
	@Override
	protected List<String> gsonIgnoreFields() {
		String[] ignores = new String[] {"role", "createTime"};
		return Arrays.asList(ignores);
	}
	

	@Autowired
	MenuItemService menuItemService;
	
	@Autowired
	RoleService roleService;

	@PutMapping("/roles/{roleId}/menuItems")
	public String create(@PathVariable("roleId") long roleId,@RequestBody Set<MenuItem> menuItems) {				
		menuItemService.deleteByRoleId(roleId);
		for (MenuItem menuItem : menuItems) {
			Role role = roleService.findById(roleId);
			menuItem.setRole(role);
			menuItemService.save(menuItem);
		}
		return this.gson().toJson(menuItemService.findByRoleId(roleId));
	}
	
	@GetMapping("/roles/{roleId}/menuItems")
	public String show(@PathVariable(value = "roleId") long roleId) {
		Iterable<MenuItem> menuItems = menuItemService.findByRoleId(roleId);	
		return GsonBuilderFactory.createGsonBuilder(
	      Arrays.asList("role","createTime","parentMenuItem")
	    ).toJson(menuItems);
	}

}
