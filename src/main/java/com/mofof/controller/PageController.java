package com.mofof.controller;

import com.mofof.constant.Codes;
import com.mofof.util.Json;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这里定义了：
 * 用户未登录时跳转的请求路径 和
 * 用户没有访问权限时跳转的请求路径
 */
@RestController
@RequestMapping("/page")
public class PageController {

    @RequestMapping("/401")
    public Json page401() {
        return new Json(false, Codes.UNAUTHEN, "not login", null);
    }

    @RequestMapping("/403")
    public Json page403() {
        return new Json(false, Codes.UNAUTHZ, "no perm", null);
    }


}