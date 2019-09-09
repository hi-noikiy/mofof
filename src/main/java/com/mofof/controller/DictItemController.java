package com.mofof.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mofof.entity.dict.DictItem;
import com.mofof.entity.dict.DictOption;
import com.mofof.service.DictItemService;

@RestController
public class DictItemController extends APIController {

  @Override
  protected List<String> gsonIgnoreFields() {
    String[] ignores = new String[] { "options", "createTime" };
    return Arrays.asList(ignores);
  }

  @Autowired
  private DictItemService dictItemService;

  @GetMapping("/dictItems")
  public String index() {
    List<DictItem> dictItems = dictItemService.findAllDictItems();
    return this.gson().toJson(dictItems);
  }

  @GetMapping("/dictItems/{id}/dictOptions")
  public List<DictOption> showId(@PathVariable(value = "id") long id) {
    DictItem dictItem = dictItemService.findById(id);
    List<DictOption> options = dictItem.getOptions();
    return options;
  }

  @GetMapping("/dict/{keyName}/options")
  public DictItem showKeyName(@PathVariable(value = "keyName") String keyName) {
    DictItem dictItem = dictItemService.findByKeyName(keyName);
//		List<DictOption> options = dictItem.getOptions();
    return dictItem;
  }

  @PostMapping("/dictItems")
  public DictItem create(@RequestBody DictItem dictItem) {
    int dictType = dictItem.getDictType();
    if (dictType == DictItem.USER_DICT_TYPE) {
      dictItem = dictItemService.save(dictItem);
    }
    return dictItem;
  }

  @GetMapping("/dictItems/find/{key}")
  public DictItem show(@PathVariable(value = "key") String key) {
    return dictItemService.findByKeyName(key);
  }

  @PutMapping("/dictItems/{id}")
  public DictItem update(@PathVariable(value = "id") Long id,
      @RequestBody DictItem dictItem) {
    DictItem dictItem2 = dictItemService.findById(id);
    if (dictItem2 != null) {
      dictItem.setId(id);
      int dictType = dictItem2.getDictType();
      if (dictType == DictItem.USER_DICT_TYPE) {
        dictItem2 = dictItemService.save(dictItem);
      }
    }
    return dictItem2;
  }

  @PutMapping("/dict/{keyName}/options")
  public List<DictOption> updateOption(
      @PathVariable(value = "keyName") String keyName,
      @RequestBody List<DictOption> options) {
    DictItem dictItem = dictItemService.findByKeyName(keyName);
    List<DictOption> options2 = dictItem.getOptions();
    options2.clear();
    options2.addAll(options);
    dictItem.setOptions(options2);
    dictItem = dictItemService.save(dictItem);
    return dictItem.getOptions();
  }

  @DeleteMapping("/dictItems/{id}")
  public void destroy(@PathVariable(value = "id") Long id) {
    DictItem dictItem = dictItemService.findById(id);
    if (dictItem != null) {
      long dictType = dictItem.getDictType();
      if (dictType == DictItem.USER_DICT_TYPE) {
        dictItemService.deleteOne(id);
      }
    }
  }
}
