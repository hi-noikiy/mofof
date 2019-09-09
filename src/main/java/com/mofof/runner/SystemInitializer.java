package com.mofof.runner;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.mofof.entity.system.Role;
import com.mofof.entity.system.RoleCategory;
import com.mofof.entity.system.User;
import com.mofof.entity.system.UserAccount;
import com.mofof.repository.MenuItemRepository;
import com.mofof.repository.RoleRepository;
import com.mofof.repository.UserAccountRepository;
import com.mofof.service.ActionItemService;
import com.mofof.service.DictExtBankTypeService;
import com.mofof.service.DictExtContinentService;
import com.mofof.service.DictExtIndustryService;
import com.mofof.service.DictItemService;
import com.mofof.service.MenuItemService;
import com.mofof.service.ModuleService;
import com.mofof.service.RoleCategoryService;
import com.mofof.service.RoleModuleService;

@Component
@Order(1)
public class SystemInitializer implements ApplicationRunner {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleCategoryService roleCategoryService;
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Value("classpath:role_menus.json")
    Resource menusResource;
    @Autowired
    MenuItemService menuItemService;
    @Autowired
    private MenuItemRepository menuItemRepository;
    @Value("classpath:dictionary_example.json")
    Resource dictionary;
    @Value("classpath:dict_ext_continent.json")
    Resource dictExtContinent;
    @Value("classpath:dict_ext_bank.json")
    Resource dictExtBank;
    @Value("classpath:dict_ext_industry.json")
    Resource dictExtIndustry;
    @Autowired
    private DictExtContinentService dictExtContinentService;
    @Autowired
    private DictExtIndustryService dictExtIndustryService;
    @Autowired
    private DictExtBankTypeService dictExtBankTypeService;
    @Autowired
    private DictItemService dictItemService;
    @Value("classpath:role_modules.json")
    Resource roleModuleResource;
    @Autowired
    private RoleModuleService roleModuleService;

    @Value("classpath:modules.json")
    Resource moduleResource;
    @Autowired
    private ModuleService moduleService;
    @Value("classpath:action_items.json")
    Resource actionItemResource;
    @Autowired
    private ActionItemService actionItemService;

    Faker cnFaker = new Faker(new Locale("zh-CN"));
    Faker enFaker = new Faker(new Locale("en-US"));

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("============== INITING SYSTEM ==============");
        
        System.out.println("........ Creating default RoleCategory");
        RoleCategory roleCategory = roleCategoryService.findByName(RoleCategory.CONVENTIONAL);
        if (roleCategory == null) {
          roleCategory = new RoleCategory();
          roleCategory.setName(RoleCategory.CONVENTIONAL);
          roleCategory = roleCategoryService.save(roleCategory);
        }
        
        // Create admin role by default
        System.out.println("........ Creating default roles");
        Role savedRAdmin = roleRepository.findByName(Role.ADMIN);
        if (savedRAdmin == null) {
            Role rAdmin = new Role();
            rAdmin.setName(Role.ADMIN);
            rAdmin.setRoleCategory(roleCategory);
            savedRAdmin = roleRepository.save(rAdmin);
        }
        Role savedRMofof = roleRepository.findByName(Role.SUPER);
        if (savedRMofof == null) {
            Role rMofof = new Role();
            rMofof.setName(Role.SUPER);
            rMofof.setRoleCategory(roleCategory);
            savedRMofof = roleRepository.save(rMofof);
        }
    Role savedRUser = roleRepository.findByName(Role.USER);
    if (savedRUser == null) {
      Role rUser = new Role();
      rUser.setName(Role.USER);
      rUser.setRoleCategory(roleCategory);
      savedRMofof = roleRepository.save(rUser);
    }
        // Create admin and mofof user by default
        System.out.println("........ Creating default users");

        UserAccount userAccount = userAccountRepository.findByUsername("admin");
        if (userAccount == null) {
            UserAccount admin = new UserAccount();
            admin.setUsername("admin");
            admin.hashPassword("123456");
            User user = new User();
            user.setJobNumber(1);
            user.setName("管理员");
            user.setPhone(enFaker.phoneNumber().cellPhone());
            user.setEmailAddress(enFaker.internet().emailAddress());
            user.setRole(savedRAdmin);
            admin.setUser(user);
            userAccountRepository.save(admin);
        }
        UserAccount userAccount2 = userAccountRepository.findByUsername("mofof");
        if (userAccount2 == null) {
            UserAccount mofof = new UserAccount();
            mofof.setUsername("mofof");
            mofof.hashPassword("123456");
            User user = new User();
            user.setJobNumber(2);
            user.setName("超级管理员");
            user.setPhone(enFaker.phoneNumber().cellPhone());
            user.setEmailAddress(enFaker.internet().emailAddress());
            user.setRole(savedRMofof);
            mofof.setUser(user);
            userAccountRepository.save(mofof);
        }

        System.out.println("........ Loading role menu items");

        if (!menuItemRepository.existsByIcon("fa fa-user")) {
            menuItemService.init(menusResource);
        }

        System.out.println("........ Creating default dictitems");
//	        dictService.deleteAll();
        if (!dictItemService.existsByKeyName("investStatus")) {
          dictItemService.initDictionary(dictionary);
        }
        System.out.println("........ Creating default dictExt");
        // dictExtContinentService.deleteAll();
//		dictExtDistrictRepository.deleteAll();
//		dictExtCityRepository.deleteAll();
        if (!dictExtContinentService.existsByNameEn("Asia")) {
            dictExtContinentService.initDictionary(dictExtContinent);
            dictExtBankTypeService.initDictBank(dictExtBank);
            dictExtIndustryService.initIndustry(dictExtIndustry);
        }


        System.out.println("........ Creating default Module");

        if (!moduleService.existsBySysKey("fund.list")) {
            moduleService.init(moduleResource);
            actionItemService.initActionItem(actionItemResource);
        }
        if (!roleModuleService.existsBySysKey("fund-list")) {
            roleModuleService.init(roleModuleResource);
        }

    }

}
