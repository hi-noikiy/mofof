package com.mofof.controller;

import com.mofof.entity.system.User;
import com.mofof.service.LayoutSettingService;
import com.mofof.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 页面自定义布局控制器
 * Created by hzh on 2019/4/15.
 */
@RestController
@RequestMapping(path = "/layout-setting")
public class LayoutSettingController {
    @Autowired
    LayoutSettingService layoutSettingService;

    /**
     * 基金模块的用户布局设置
     * @return
     */
    @GetMapping(path = "/setting4Fund")
    public List<Map> setting4Fund() {
        User user = ShiroUtils.getCurrentUser();
        return layoutSettingService.setting4Fund(user);
    }

    /**
     * 项目模块的用户布局设置
     * @return
     */
    @GetMapping(path = "/setting4Project")
    public List<Map> setting4Project() {
        User user = ShiroUtils.getCurrentUser();
        return layoutSettingService.setting4Project(user);
    }

    /**
     * 平台模块的用户布局设置
     * @return
     */
    @GetMapping(path = "/setting4Platform")
    public List<Map> setting4Platform() {
        User user = ShiroUtils.getCurrentUser();
        return layoutSettingService.setting4Platform(user);
    }

}
