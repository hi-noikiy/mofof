package com.mofof.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import com.mofof.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mofof.dto.SelectOption;
import com.mofof.entity.common.License;

/**
 * Created by ChenErliang on 17/5/4.
 */
@RestController
@RequestMapping(path = "/common")
public class CommonController {

    @Value("classpath:dictionary_example.json")
    Resource dictionary;
    @Value("classpath:dict_category.json")
    Resource dictionaryCategory;

    @Autowired
    private DictService dictService;
    @Autowired
    private DataSelectionService dataSelectionService;
    @Autowired
    private AutoCompleteService autoCompleteService;
    @Autowired
    private FileSystemStorageService fileSystemStorageService;
    @Autowired
    private DistService distService;

    @GetMapping(path = "/test")
    public String test() {
        return "Test:" + LocalDateTime.now();
    }

    @GetMapping(path = "/init")
    public String init() throws IOException {
        dictService.deleteAll();
        dictService.initDictionary(dictionary);
//        dictService.initDictCategory(dictionaryCategory);
        fileSystemStorageService.init();
        return "Init Done";
    }

    /**
     * 供选择的数据
     *
     * @param keyNames
     * @return
     */
    @GetMapping(path = "/data-selection")
    public Map<String, List<SelectOption>> dataSelection(@RequestParam("keyNames[]") List<String> keyNames,@RequestParam Map<String,String> params) {
        return dataSelectionService.getDataSelection(keyNames,params);
    }

    /**
     * 供选择的数据字典
     * @param keyNames
     * @return
     */
    @GetMapping(path = "/dict-selection")
    public Map<String, List<String>> dictSelection(@RequestParam("keyNames[]") List<String> keyNames,@RequestParam String cascadeOption) {
        return dictService.getDictItemsByKeyNames(keyNames,cascadeOption);
    }

    /**
     * 自动完成数据
     *
     * @param keyNames
     * @return
     */
    @GetMapping(path = "/auto-complete")
    public Map<String, Map<String, List<SelectOption>>> autoComplete(@RequestParam("keyNames[]") List<String> keyNames) {
        return autoCompleteService.getAutoComplete(keyNames);
    }

    @GetMapping(path = "/refresh-license")
    public License refreshLicense(String fullName) {
        License license = new License();
        license.setLicenseName(fullName);
        license.setUniformCode("Code20170503");
        license.setType("测试类型");
        license.setArtificialPerson("张三");
        license.setRegCapital("3000万");
        license.setFoundDate(LocalDate.now());
        license.setRegAddress("上海市");
        license.setBusinessStartDate(LocalDate.now());
        license.setBusinessEndDate(LocalDate.now());
        license.setBusinessScope("软件开发 系统集成 网络建设");
        license.setRegAuthority("上海市静安区工商管理局");
        license.setRegStatus("正常11");
        license.setApprovalDate(LocalDate.parse("2016-05-15"));
        license.setApprovalDate(LocalDate.parse("2016-05-05"));
        license.setRevokeDate(LocalDate.parse("2016-05-25"));
        license.setLastUpdateTime(LocalDateTime.now());
        System.out.println("hwf11");
        return license;
    }

    /**
     * 省市区数据(供地址选择器使用)
     *
     * @return
     */
    @GetMapping(path = "/dist-data")
    public List<Map> distData(Boolean city) {
        List<Map> list = new ArrayList<>();
        List<Map> sublist = new ArrayList<>();
        List<Map> subsublist = new ArrayList<>();
        Map map = new LinkedHashMap();
        map.put("value","浙江省");
        map.put("label","浙江省");

        Map submap = new LinkedHashMap();
        submap.put("value","杭州市");
        submap.put("label","杭州市");
        sublist.add(submap);

        if(city==null||!city){
            Map subsubmap = new LinkedHashMap();
            subsubmap.put("value","滨江区");
            subsubmap.put("label","滨江区");
            subsublist.add(subsubmap);

            submap.put("children",subsublist);
        }

        map.put("children",sublist);
        list.add(map);
        return list;
//        return distService.getDistData();
    }

//    /**
//     * 银行数据(供银行选择器使用)
//     * @return
//     */
//    @GetMapping(path = "/bank-data")
//    public List<Map> bankData() {
//        List<Map> list = new ArrayList<>();
//        List<Map> sublist = new ArrayList<>();
//        List<Map> subsublist = new ArrayList<>();
//        Map map = new LinkedHashMap();
//        map.put("value","大型商业银行");
//        map.put("label","大型商业银行");
//
//        Map submap = new LinkedHashMap();
//        submap.put("value","中国工商银行");
//        submap.put("label","中国工商银行");
//        sublist.add(submap);
//
//        Map subsubmap = new LinkedHashMap();
//        subsubmap.put("value","杭州分行");
//        subsubmap.put("label","杭州分行");
//        subsublist.add(subsubmap);
//
//        submap.put("children",subsublist);
//        map.put("children",sublist);
//        list.add(map);
//        return list;
//    }
}
