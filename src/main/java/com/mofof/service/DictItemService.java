package com.mofof.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mofof.entity.dict.DictItem;
import com.mofof.entity.dict.DictOption;
import com.mofof.entity.dict.DictTerm;
import com.mofof.repository.DictItemRepository;
import com.mofof.repository.DictTermRepository;

@Service
@Transactional
public class DictItemService {

	@Autowired
	private DictItemRepository dictItemRepository;
	@Autowired
	private DictTermRepository dictTermRepository;

	public void deleteAll() {
		dictItemRepository.deleteAll();
	}

	/**
	 * 初始化数据字典
	 *
	 * @param resource
	 */
	public void initDictionary(Resource resource) {

		try {
			String dictionaryString = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
			JSONArray jsonArray = JSON.parseArray(dictionaryString);
			List<DictItem> dictItems = new ArrayList<>();
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				DictItem dictItem = new DictItem();
				if (!dictItemRepository.existsByKeyName(jsonObject.getString("keyName"))) {
					if (jsonObject.containsKey("dictType")) {
						dictItem.setDictType(jsonObject.getInteger("dictType"));
					} else {
						dictItem.setDictType(1);
					}
					dictItem.setId(1L + i);
					dictItem.setName(jsonObject.getString("name"));
					dictItem.setKeyName(jsonObject.getString("keyName"));
					if (jsonObject.containsKey("cascadeKey")) {
						dictItem.setCascadeKey(jsonObject.getString("cascadeKey"));
					}
					if (jsonObject.containsKey("allowInput")) {
						dictItem.setAllowInput(jsonObject.getBoolean("allowInput"));
					} else {
						dictItem.setAllowInput(false);
					}
					if (jsonObject.containsKey("logic")) {
						dictItem.setLogic(jsonObject.getBoolean("dictType"));
					} else {
						dictItem.setLogic(false);
					}
					if (jsonObject.containsKey("multiple")) {
						dictItem.setMultiple(jsonObject.getBoolean("multiple"));
					} else {
						dictItem.setMultiple(false);
					}
				} else {
					dictItem = dictItemRepository.findOneByKeyName(jsonObject.getString("keyName"));
				}
				List<DictOption> dictOptions = new ArrayList<>();
				JSONArray jsonOptions = jsonObject.getJSONArray("options");
				for (int j = 0; j < jsonOptions.size(); j++) {
					DictOption dictOption = new DictOption();
					Object option = jsonOptions.get(j);
					if (option instanceof String) {
						dictOption.setName((String) option);
						dictOption.setDefaultValue(false);
					} else if (option instanceof JSONObject) {
						JSONObject jsonOption = (JSONObject) option;
						dictOption.setName(jsonOption.getString("name"));
						if (jsonOption.containsKey("content")) {
							dictOption.setContent(jsonOption.getString("content"));
						}
						if (jsonOption.containsKey("keyName")) {
							dictOption.setKeyName(jsonOption.getString("keyName"));
						}
						if (jsonOption.containsKey("tagText")) {
							dictOption.setTagText(jsonOption.getString("tagText"));
						}
						if (jsonOption.containsKey("rank")) {
							dictOption.setRank(jsonOption.getInteger("rank"));
						}
						if (jsonOption.containsKey("tagNum")) {
							dictOption.setTagNum(jsonOption.getInteger("tagNum"));
						}
						if (jsonOption.containsKey("cascadeOption")) {
							dictOption.setCascadeOption(jsonOption.getString("cascadeOption"));
						}
						if (jsonOption.containsKey("defaultValue")) {
							dictOption.setDefaultValue(jsonOption.getBooleanValue("defaultValue"));
						}
						if (jsonOption.containsKey("description")) {
							dictOption.setDescription(jsonOption.getString("description"));
						}
						if (jsonOption.containsKey("orderNum")) {
							dictOption.setOrderNum(jsonOption.getInteger("orderNum"));
						} else {
							dictOption.setOrderNum(1 + j);
						}
						if (jsonOption.containsKey("numLabel")) {
							dictOption.setNumLabel(jsonOption.getInteger("numLabel"));
						} else {
							dictOption.setNumLabel(1 + j);
						}

					}
					dictOptions.add(dictOption);
				}
				dictItem.setOptions(dictOptions);
				dictItems.add(dictItem);

				List<DictTerm> dictTerms = new ArrayList<>();
				JSONArray jsonTerms = jsonObject.getJSONArray("dictTerms");
				if (jsonTerms != null) {
					for (int a = 0; a < jsonTerms.size(); a++) {
						DictTerm dictTerm = new DictTerm();
						Object terms = jsonTerms.get(a);
						if (terms instanceof JSONObject) {
							JSONObject jsonTerm = (JSONObject) terms;
							if (!dictTermRepository.existsByKeyName(jsonTerm.getString("keyName"))) {
								if (jsonTerm.containsKey("rank")) {
									dictTerm.setRank(jsonTerm.getIntValue("rank"));
								}
								if (jsonTerm.containsKey("keyName")) {
									dictTerm.setKeyName(jsonTerm.getString("keyName"));
								} else {
									dictTerm.setKeyName("高级字典" + a);
								}
								if (jsonTerm.containsKey("cascadeOption")) {
									dictTerm.setCascadeOption(jsonTerm.getString("cascadeOption"));
								}
								if (jsonTerm.containsKey("name")) {
									dictTerm.setName(jsonTerm.getString("name"));
								}
								if (jsonTerm.containsKey("description")) {
									dictTerm.setDescription(jsonTerm.getString("description"));
								}
								if (jsonTerm.containsKey("defaultValue")) {
									dictTerm.setDefaultValue(jsonTerm.getBooleanValue("defaultValue"));
								} else {
									dictTerm.setDefaultValue(false);
								}
								if (jsonTerm.containsKey("term")) {
									dictTerm.setTerm(jsonTerm.getString("term"));
								}
								if (jsonTerm.containsKey("tagNum")) {
									dictTerm.setTagNum(jsonTerm.getInteger("tagNum"));
								} else {
									dictTerm.setTagNum(1 + a);
								}
								if (jsonTerm.containsKey("content")) {
									dictTerm.setContent(jsonTerm.getString("content"));
								}
								if (jsonTerm.containsKey("tagText")) {
									dictTerm.setTagText(jsonTerm.getString("tagText"));
								}
								dictTerms.add(dictTerm);
							}
						}
					}
					dictTermRepository.save(dictTerms);
				}
			}
			dictItemRepository.save(dictItems);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public DictItem save(DictItem dictItem) {
		if (dictItem.getOptions() != null) {
			// 移除name 为空白的option
			for (Iterator<DictOption> it = dictItem.getOptions().iterator(); it.hasNext();) {
				DictOption option = it.next();
				if (option.getName() == null || option.getName().trim().equals("")) {
					it.remove();
				}
			}
		}
		return dictItemRepository.save(dictItem);
	}

	public DictItem findByKeyName(String keyName) {
		return dictItemRepository.findOneByKeyName(keyName);
	}

	public List<DictItem> findAllDictItems() {
		return dictItemRepository.findAllByOrderByIdDesc();
	}

	public void deleteOne(Long id) {
		dictItemRepository.delete(id);
	}

	public DictItem findById(Long id) {
		return dictItemRepository.findById(id);
	}

	public Set<DictItem> findAllByCascadeKey(String cascadeKey) {
		return dictItemRepository.findByCascadeKey(cascadeKey);
	}

	public Set<DictItem> findByDictType(int dictType) {
		return dictItemRepository.findByDictType(dictType);
	}

  public boolean existsByKeyName(String keyName) {
    return dictItemRepository.existsByKeyName(keyName);
  }
}
