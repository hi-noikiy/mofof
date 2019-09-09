package com.mofof.controller;

import com.mofof.entity.platform.Platform;
import com.mofof.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hzh
 * @date 2019-06-25
 */
@RestController
@RequestMapping(path = "/platform")
public class PlatformController {

    @Autowired
    PlatformService platformService;

    /**
     * 获取所有平台,不带筛选条件
     * @return
     */
    @GetMapping(path = "/platforms")
    public List<Platform> platforms() {
        return platformService.findAll();
    }

    /**
     * 获取投资平台
     * @param id
     * @return
     */
    @GetMapping(path = "/platform/{id}")
    public Platform save(@PathVariable Long id) {
        return platformService.findOne(id);
    }

    /**
     * 保存投资平台
     * @param platform
     * @return
     */
    @RequestMapping(path = "/platform",method = {RequestMethod.POST,RequestMethod.PUT})
    public Platform save(@RequestBody Platform platform) {
        return platformService.save(platform);
    }

    /**
     * 删除投资平台
     * @param platform
     */
    @DeleteMapping(path = "/platform")
    public void delete(@RequestBody Platform platform) {
        platformService.delete(platform.getId());
    }
}
