package com.mofof.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.mofof.entity.dict.DictTerm;
import com.mofof.service.DictItemService;
import com.mofof.service.DictTermService;

@RestController
public class DictTermController extends APIController {
	private static final Logger log = LoggerFactory.getLogger(DictTermController.class);

	@Override
	protected List<String> gsonIgnoreFields() {
		String[] ignores = new String[] { "options", "createTime" };
		return Arrays.asList(ignores);
	}

	@Autowired
	DictTermService dictTermService;

	@Autowired
	DictItemService dictItemService;

	@GetMapping("/dictTerms")
	public List<DictTerm> index() {
		return dictTermService.findAllDictTerms();
	}

	@GetMapping("/dict_items/dict_terms")
	public String dictItems() {
		int dictType = DictItem.ADVANCED_DICT_TYPE;
		Set<DictItem> dictItems = dictItemService.findByDictType(dictType);
		return this.gson().toJson(dictItems);
	}

	@GetMapping("/dict_items/dict_terms/{keyName}")
	public List<DictOption> dictOptions(@PathVariable String keyName) {
		DictItem dictItem = dictItemService.findByKeyName(keyName);
		List<DictOption> options = dictItem.getOptions();
		return options;
	}

	@GetMapping("/dict_terms/{keyName}/options")
	public Set<DictTerm> dictTerms(@PathVariable String keyName) {
		Set<DictTerm> terms = dictTermService.findByCascadeOption(keyName);
		return terms;
	}

	@GetMapping("/dictTerms/find/{key}")
	public DictTerm show(@PathVariable(value = "key") String key) {
		log.info("DictTerm {} ======findByKey", key);
		return dictTermService.findByKey(key);
	}

	@PostMapping("/dictTerms")
	public DictTerm create(@RequestBody DictTerm dictTerm) {
		return dictTermService.save(dictTerm);
	}

	@PutMapping("/dictTerms/{id}")
	public DictTerm update(@PathVariable(value = "id") long id, @RequestBody DictTerm dictTerm) {
		dictTerm.setId(id);
		return dictTermService.save(dictTerm);
	}

	@DeleteMapping("/dictTerms/{id}")
	public void destroy(@PathVariable(value = "id") long id) {
		dictTermService.delete(id);
	}
}
