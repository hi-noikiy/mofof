package com.mofof.service;

import com.alibaba.fastjson.JSONArray;
import com.mofof.entity.dict.ext.Province;
import com.mofof.repository.CityRepository;
import com.mofof.repository.DistrictRepository;
import com.mofof.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hzh on 2018/12/21.
 */
@Service
@Transactional
public class DistService {
    @Autowired
    ProvinceRepository provinceRepository;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    DistrictRepository districtRepositoryl;

    /**
     * 获取省市区数据
     * @return
     */
    public JSONArray getDistData(){
        List<Province> provinces = provinceRepository.findAll();
        JSONArray jsonArray = new JSONArray();
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.
//                JSON
//
//                String dictionaryString = new String(IOUtils.readFully(resource.getInputStream(), -1, true));
//        JSONArray jsonArray = JSON.parseArray(dictionaryString);
//        List<DictItem> dictItems = new ArrayList<>();
//        for (int i = 0; i < jsonArray.size(); i++) {
//            JSONObject jsonObject = jsonArray.getJSONObject(i);
//            DictItem dictItem = new DictItem();
//
//        jsonArray.add()
        return null;
    }
}
