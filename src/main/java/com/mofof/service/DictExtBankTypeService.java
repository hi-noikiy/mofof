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
import com.mofof.entity.dict.ext.DictExtBankName;
import com.mofof.entity.dict.ext.DictExtBankType;
import com.mofof.repository.DictExtBankNameRepository;
import com.mofof.repository.DictExtBankTypeRepository;

@Service
public class DictExtBankTypeService {

	@Autowired
	private DictExtBankTypeRepository dictExtBankTypeRepository;
	@Autowired
	private DictExtBankNameRepository dictExtBankNameRepository;
	
	/**
	 * 初始化远程字典
	 */
	public void initDictBank(Resource resource) {

		try {
			String dictionaryString = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
			JSONArray jsonArray = JSON.parseArray(dictionaryString);
			Set<DictExtBankType> types = new HashSet<DictExtBankType>();
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				DictExtBankType type = new DictExtBankType();
				type.setId(1L + i);
				type.setRank(jsonObject.getIntValue("rank"));
				type.setBankType(jsonObject.getString("bankType"));
				
				Set<DictExtBankName> names = new HashSet<DictExtBankName>();
				JSONArray jsonNames = jsonObject.getJSONArray("dictExtBankNames");
				for (int j = 0; j < jsonNames.size(); j++) {
					DictExtBankName name = new DictExtBankName();
					Object name1 = jsonNames.get(j);
					if (name1 instanceof JSONObject) {
						JSONObject jsonName = (JSONObject) name1;
						name.setRank(jsonName.getIntValue("rank"));
						if (jsonName.containsKey("bankName")) {
							name.setBankName(jsonName.getString("bankName"));
						}
						dictExtBankTypeRepository.save(type);
						name.setDictExtBankType(type);
					}
					names.add(name);
					dictExtBankNameRepository.save(names);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Set<DictExtBankType> findAll() {
		return (Set<DictExtBankType>) dictExtBankTypeRepository.findAll();
	}
}
