package com.mofof.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mofof.entity.dict.DictItem;
import com.mofof.entity.dict.DictOption;
import com.mofof.repository.DictItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.io.IOUtils;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by ChenErliang on 17/5/4.
 */
@Service
@Transactional
public class DictService {

    @Autowired
    private DictItemRepository dictItemRepository;

    public void deleteAll() {
        dictItemRepository.deleteAll();
    }

//    public void initDictCategory(Resource resource) {
//        try {
//            String dictionaryString = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
//            JSONArray jsonArray = JSON.parseArray(dictionaryString);
//            List<DictCategory> dictCategoryList = new ArrayList<>();
//
//            List<DictItem> dictItems = dictItemRepository.findAllByOrderByIdDesc();
//            Map<String, DictItem> dictItemMap = new HashMap<>();
//            dictItems.forEach(dictItem -> dictItemMap.put(dictItem.getKeyName(), dictItem));
//            for (int i = 0; i < jsonArray.size(); i++) {
//                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                DictCategory dictCategory = buildDictCategory(jsonObject, dictItemMap, null);
//                dictCategoryList.add(dictCategory);
//            }
//            dictCategoryRepository.save(dictCategoryList);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    public Iterable<DictCategory> getAllRootDictCategory() {
//        return dictCategoryRepository.findAllByParentIdIsNull();
//    }

//    public DictCategory buildDictCategory(JSONObject jsonObject, Map<String, DictItem> dictItemMap, DictCategory parent) {
//        DictCategory dictCategory = new DictCategory();
//        dictCategory.setName(jsonObject.getString("name"));
//        if (parent != null) {
//            dictCategory.setParent(parent);
//        }
//        List<DictItem> items = new ArrayList<>();
//        if (jsonObject.containsKey("items")) {
//            JSONArray jsonArray = jsonObject.getJSONArray("items");
//            for (int i = 0; i < jsonArray.size(); i++) {
//                String keyName = jsonArray.getString(i);
//                DictItem dictItem = dictItemMap.get(keyName);
//                if (dictItem != null) {
//                    items.add(dictItem);
//                }
//            }
//            dictCategory.setDictItems(items);
//        }
//        if (jsonObject.containsKey("children")) {
//            JSONArray jsonArray = jsonObject.getJSONArray("children");
//            List<DictCategory> children = new ArrayList<>();
//            for (int i = 0; i < jsonArray.size(); i++) {
//                JSONObject subJsonObject = jsonArray.getJSONObject(i);
//                DictCategory subDictCategory = buildDictCategory(subJsonObject, dictItemMap, dictCategory);
//                children.add(subDictCategory);
//            }
//            dictCategory.setSubCategorys(children);
//        }
//        return dictCategory;
//    }

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
                dictItem.setId(1L + i);
                dictItem.setName(jsonObject.getString("name"));
                dictItem.setKeyName(jsonObject.getString("keyName"));
                if (jsonObject.containsKey("cascadeKey")) {
                    dictItem.setCascadeKey(jsonObject.getString("cascadeKey"));
                }

                if (jsonObject.containsKey("dictType")) {
                    dictItem.setDictType(jsonObject.getInteger("dictType"));
                } else {
                    dictItem.setDictType(1);
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
                        }else {
                        	dictOption.setOrderNum(1+j);
                        }
                        if (jsonOption.containsKey("numLabel")) {
                            dictOption.setNumLabel(jsonOption.getInteger("numLabel"));
                        }else {
                        	dictOption.setNumLabel(1+j);
                        }
                    }
                    dictOptions.add(dictOption);
                }
                dictItem.setOptions(dictOptions);
                dictItems.add(dictItem);
            }
            dictItemRepository.save(dictItems);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据keyName列表取数据字典的具体选项,可根据cascadeValue筛选
     *
     * @param keyNames
     * @return
     */
    public Map<String, List<String>> getDictItemsByKeyNames(List<String> keyNames,String cascadeOption) {
        List<DictItem> dictItems = dictItemRepository.findByKeyNameIn(keyNames);
        Map<String, List<String>> map = new HashMap<>();
        for (DictItem dictItem : dictItems) {
            String keyName = dictItem.getKeyName();
//            List<String> options = dictItem.getOptions().stream().map(DictOption::getName).collect(Collectors.toList());
            List<String> options = dictItem.getOptions().stream().filter(item->item.getCascadeOption()==null||cascadeOption==null||"".equals(cascadeOption)||item.getCascadeOption().equals(cascadeOption)).map(DictOption::getName).collect(Collectors.toList());
            map.put(keyName, options);
        }
        return map;
    }

    public DictItem save(DictItem dictItem) {
        //移除name 为空白的option
        for (Iterator<DictOption> it = dictItem.getOptions().iterator(); it.hasNext(); ) {
            DictOption option = it.next();
            if (option.getName() == null || option.getName().trim().equals("")) {
                it.remove();
            }
        }
        return dictItemRepository.save(dictItem);
    }

    public List<DictItem> findAllDictItems() {
        return dictItemRepository.findAllByOrderByIdDesc();
    }

    public DictItem findById(Long id) {
        DictItem dictItem = dictItemRepository.findOne(id);
        return dictItem;
    }

    public String cascadeString(String keyName) {
        DictItem dictItem = dictItemRepository.findOneByKeyName(keyName);
        return  dictItem.getName();
    }

    public DictItem findByKeyName(String keyName) {
        return dictItemRepository.findOneByKeyName(keyName);
    }
}
