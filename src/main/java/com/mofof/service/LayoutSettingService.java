package com.mofof.service;

import com.mofof.entity.system.Role;
import com.mofof.entity.system.RoleModule;
import com.mofof.entity.system.User;
import com.mofof.repository.RoleModuleRepository;
import com.mofof.repository.RoleRepository;
import com.mofof.repository.UserRepository;
import com.mofof.util.ShiroUtils;
import com.mofof.util.enumeration.SysGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 布局服务类
 * Created by hzh on 2019/4/15.
 */
@Service
@Transactional
public class LayoutSettingService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    RoleModuleRepository roleModuleRepository;


    public List<Map> setting4Fund(User user){
        Role role = ShiroUtils.getCurrentRole();
        Set<RoleModule> roleModules = roleModuleRepository.findByRoleIdAndSysGroupAndParentRoleModuleIsNotNull(role.getId(), SysGroup.FUND.getValue());
        List<Map> result = new ArrayList<>();
        roleModules.forEach((item)->{
//            item.
        });

        return result;
    }

    public List<Map> setting4Project(User user){
        return null;
    }

    public List<Map> setting4Platform(User user){
        return null;
    }


//    [
//    {
//        tabName:"基本信息",
//                modules:[
//        {
//            moduleName: "基本信息",
//                    moduleKey:"fund-info",
//                btns:[{ btnName:"add", isShow: true }]
//        },{
//        moduleName: "管理费",
//                moduleKey:"fund-managefee",
//                btns:[{ btnName:"edit", isShow: true }]
//    },{
//        moduleName: "关联机构",
//                moduleKey:"fund-affiliation",
//                btns:[{ btnName:"add", isShow: true },{ btnName:"edit", isShow: true}]
//    }
//            ]
//    },{
//        tabName:"出资分配11",
//                modules:[
//        {
//            moduleName: "出资主体",
//                    moduleKey:"fund-platform",
//                btns:[{ btnName:"add", isShow: true },{ btnName:"edit", isShow: true}]
//        },{
//            moduleName: "出资分配概况",
//                    moduleKey:"fund-cashflow-overall",
//                    btns:[{ btnName:"", isShow: false }]    //无操作
//        },{
//            moduleName: "出资分配明细",
//                    moduleKey:"fund-cashflow-detail",
//                    btns:[{ btnName:"add", isShow: true },{ btnName:"edit", isShow: true }]
//        },{
//            moduleName: "账户管理",
//                    moduleKey:"fund-account",
//                    btns:[{ btnName:"add", isShow: true },{ btnName:"edit", isShow: true }]
//        }
//            ]
//    }
//        ];
}
