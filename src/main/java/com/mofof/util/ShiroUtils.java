package com.mofof.util;

import com.mofof.entity.system.Role;
import com.mofof.entity.system.User;
import org.apache.shiro.SecurityUtils;

/**
 * Shiro一些工具方法
 * Created by hzh on 2019/4/15.
 */
public class ShiroUtils {
    /**
     * 获取当前系统登录用户
     * @return
     */
    public static User getCurrentUser(){
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 获取当前系统登录用户角色
     * @return
     */
    public static Role getCurrentRole(){
        User u= (User) SecurityUtils.getSubject().getPrincipal();
        return u.getRole();
    }
}
