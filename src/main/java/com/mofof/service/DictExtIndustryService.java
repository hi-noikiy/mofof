package com.mofof.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mofof.entity.dict.DictItem;
import com.mofof.entity.dict.DictOption;
import com.mofof.entity.dict.ext.DictExtCity;
import com.mofof.entity.dict.ext.DictExtCountry;
import com.mofof.entity.dict.ext.DictExtDistrict;
import com.mofof.entity.dict.ext.DictExtIndustry;
import com.mofof.entity.dict.ext.DictExtIndustryL1;
import com.mofof.entity.dict.ext.DictExtIndustryL2;
import com.mofof.entity.dict.ext.DictExtIndustryL3;
import com.mofof.entity.dict.ext.DictExtProvince;
import com.mofof.repository.DictExtIndustryL1Repository;
import com.mofof.repository.DictExtIndustryL2Repository;
import com.mofof.repository.DictExtIndustryL3Repository;
import com.mofof.repository.DictExtIndustryRepository;

@Service
public class DictExtIndustryService {

	@Autowired
	DictExtIndustryRepository dictExtIndustryRepository;
	@Autowired
	DictExtIndustryL1Repository dictExtIndustryL1Repository;
	@Autowired
	DictExtIndustryL2Repository dictExtIndustryL2Repository;
	@Autowired
	DictExtIndustryL3Repository dictExtIndustryL3Repository;
	
	public Set<DictExtIndustry> findAll() {
		return dictExtIndustryRepository.findAll();
	}
	
	/**
	 * 初始化远程字典
	 */
	public void initIndustry(Resource resource) {

		try {
			String dictionaryString = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
			JSONArray jsonArray = JSON.parseArray(dictionaryString);
			Set<DictExtIndustry> industries = new HashSet<DictExtIndustry>();
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				DictExtIndustry industry = new DictExtIndustry();
				industry.setId(1L + i);
				industry.setNameEn(jsonObject.getString("nameEn"));
				industry.setNameCn(jsonObject.getString("nameCn"));
				industry.setCustom(jsonObject.getBooleanValue("custom"));
				industry.setUsed(jsonObject.getBooleanValue("used"));
				industry.setTagText(jsonObject.getString("tagText"));
				industry.setTagNum(jsonObject.getIntValue("tagNum"));
				industry.setDescription(jsonObject.getString("description"));
				if (jsonObject.containsKey("rank")) {
					industry.setRank(jsonObject.getInteger("rank"));
				} 

				Set<DictExtIndustryL1> industryL1s = new HashSet<DictExtIndustryL1>();
				JSONArray jsonIndustryL1s = jsonObject.getJSONArray("dictExtIndustryL1s");
				for (int j = 0; j < jsonIndustryL1s.size(); j++) {
					DictExtIndustryL1 industryL1 = new DictExtIndustryL1();
					Object jsonIndustry1 = jsonIndustryL1s.get(j);
					if (jsonIndustry1 instanceof JSONObject) {
						JSONObject jsonIndustryL1= (JSONObject) jsonIndustry1;
						industryL1.setNameCn(jsonIndustryL1.getString("nameCn"));
						if (jsonIndustryL1.containsKey("nameEn")) {
							industryL1.setNameEn(jsonIndustryL1.getString("nameEn"));
						}
						if (jsonIndustryL1.containsKey("rank")) {
							industryL1.setRank(jsonIndustryL1.getIntValue("rank"));
						}
						if (jsonIndustryL1.containsKey("insdustryCode")) {
							industryL1.setInsdustryCode(jsonIndustryL1.getIntValue("insdustryCode"));
						}
						Set<DictExtIndustryL2> industryL2s = new HashSet<DictExtIndustryL2>();
						JSONArray jsonIndustryL2s = jsonIndustryL1.getJSONArray("dictExtIndustryL2s");
						for (int a = 0; a < jsonIndustryL2s.size(); a++) {
							DictExtIndustryL2 industryL2 = new DictExtIndustryL2();
							Object jsonIndustry2 = jsonIndustryL2s.get(a);
							if (jsonIndustry2 instanceof JSONObject) {
								JSONObject jsonIndustryL2 = (JSONObject) jsonIndustry2;
								industryL2.setRank(jsonIndustryL2.getIntValue("rank"));
								if (jsonIndustryL2.containsKey("nameCn")) {
									industryL2.setNameCn(jsonIndustryL2.getString("nameCn"));
								}
								if (jsonIndustryL2.containsKey("nameEn")) {
									industryL2.setNameEn(jsonIndustryL2.getString("nameEn"));
								}
								if (jsonIndustryL2.containsKey("insdustryCode")) {
									industryL2.setInsdustryCode(jsonIndustryL2.getIntValue("insdustryCode"));
								}
								Set<DictExtIndustryL3> industryL3s = new HashSet<DictExtIndustryL3>();
								JSONArray jsonIndustryL3s = jsonIndustryL2.getJSONArray("dictExtIndustryL3s");
								for (int b = 0; b < jsonIndustryL3s.size(); b++) {
									DictExtIndustryL3 industryL3 = new DictExtIndustryL3();
									Object jsonIndustry3 = jsonIndustryL3s.get(b);
									if (jsonIndustry3 instanceof JSONObject) {
										JSONObject jsonIndustryL3 = (JSONObject) jsonIndustry3;
										industryL3.setRank(jsonIndustryL3.getIntValue("rank"));
										if (jsonIndustryL3.containsKey("nameCn")) {
											industryL3.setNameCn(jsonIndustryL3.getString("nameCn"));
										}
										if (jsonIndustryL3.containsKey("nameEn")) {
											industryL3.setNameEn(jsonIndustryL3.getString("nameEn"));
										}
										if (jsonIndustryL3.containsKey("insdustryCode")) {
											industryL3.setInsdustryCode(jsonIndustryL3.getIntValue("insdustryCode"));
										}																			
									}
									dictExtIndustryL2Repository.save(industryL2);
									industryL3.setDictExtIndustryL2(industryL2);
									dictExtIndustryL3Repository.save(industryL3);
								}
							}
							industryL1 = dictExtIndustryL1Repository.save(industryL1);
							industryL2 = dictExtIndustryL2Repository.findByNameEn(industryL2.getNameEn());
							industryL2.setDictExtIndustryL1(industryL1);
							dictExtIndustryL2Repository.save(industryL2);
						}
					}		
					dictExtIndustryRepository.save(industry);
					industryL1 = dictExtIndustryL1Repository.findByNameEn(industryL1.getNameEn());
					industryL1.setDictExtIndustry(industry);
					dictExtIndustryL1Repository.save(industryL1);
				}
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
