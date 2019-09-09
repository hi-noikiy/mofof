package com.mofof.controller;

import java.util.Arrays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mofof.service.DictExtBankNameService;
import com.mofof.service.DictExtBankTypeService;
import com.mofof.service.DictExtCityService;
import com.mofof.service.DictExtContinentService;
import com.mofof.service.DictExtCountryService;
import com.mofof.service.DictExtDistrictService;
import com.mofof.service.DictExtIndustryL1Service;
import com.mofof.service.DictExtIndustryL2Service;
import com.mofof.service.DictExtIndustryL3Service;
import com.mofof.service.DictExtIndustryService;
import com.mofof.service.DictExtProvinceService;
import com.mofof.util.GsonBuilderFactory;

@RestController
public class DictExtController {

	@Autowired
	DictExtContinentService dictExtContinentService;
	@Autowired
	DictExtCountryService dictExtCountryService;
	@Autowired
	DictExtProvinceService dictExtProvinceService;
	@Autowired
	DictExtCityService dictExtCityService;
	@Autowired
	DictExtDistrictService dictExtDistrictService;
	@Autowired
	DictExtBankTypeService dictExtBankTypeService;
	@Autowired
	DictExtBankNameService dictExtBankNameService;
	@Autowired
	DictExtIndustryService dictExtIndustryService;
	@Autowired
	DictExtIndustryL1Service dictExtIndustryL1Service;
	@Autowired
	DictExtIndustryL2Service dictExtIndustryL2Service;
	@Autowired
	DictExtIndustryL3Service dictExtIndustryL3Service;
	
	@GetMapping("/dict_exts")
	public String[] dictExts() {
		String[] dixExts = {"大洲","国家","省","市","区","银行类型","银行名称","行业类型分类","一级分类","二级分类","三级分类"};
		return dixExts;
	}

	@GetMapping("/dict_ext/continents")
	public String continents() {
		return GsonBuilderFactory.createGsonBuilder(
					Arrays.asList("dictExtCountries", "createTime")
				).toJson(dictExtContinentService.findAll());
	}

	@GetMapping("/dict_ext/continents/{id}/countries")
	public String countries(@PathVariable long id) {
		return GsonBuilderFactory.createGsonBuilder(
					Arrays.asList("dictExtContinent","createTime","dixtDictExtProvinces")
				).toJson(dictExtCountryService.findByContinentId(id));
	}
	
	@GetMapping("/dict_ext/countries/{id}/provinces")
	public String provinces(@PathVariable long id) {
		return GsonBuilderFactory.createGsonBuilder(
					Arrays.asList("dictExtCountry","createTime","dictExtCities")
				).toJson(dictExtProvinceService.findByCountryId(id));		
	}	
	
	@GetMapping("/dict_ext/provinces/{id}/cities")
	public String cities(@PathVariable long id) {
		return GsonBuilderFactory.createGsonBuilder(
					Arrays.asList("dictExtProvince","createTime","dictExtDistricts")
				).toJson(dictExtCityService.findByProvinceId(id));
	}
	
	@GetMapping("/dict_ext/cities/{id}/districts")
	public String districts(@PathVariable long id) {
		return GsonBuilderFactory.createGsonBuilder(
				Arrays.asList("dictExtCity","createTime")
			).toJson(dictExtDistrictService.findByCityId(id));
	}
	
	@GetMapping("/dict_ext/bank_types")
	public String bankTypes() {
		return GsonBuilderFactory.createGsonBuilder(
					Arrays.asList("dictExtBankNames","createTime")
				).toJson(dictExtBankTypeService.findAll());
	}
	
	@GetMapping("/dict_ext/bank_types/{id}/bank_names")
	public String bankNames(@PathVariable long id) {
		return GsonBuilderFactory.createGsonBuilder(
					Arrays.asList("dictExtBankType","createTime")
				).toJson(dictExtBankNameService.findAllByBankTypeId(id));
	}
	
	@GetMapping("/dict_ext/industries")
	public String industrys() {
		return GsonBuilderFactory.createGsonBuilder(
					Arrays.asList("dictExtIndustryL1s", "createTime")
				).toJson(dictExtIndustryService.findAll());
	}
	
	@GetMapping("/dict_ext/industries/{id}/industry_l1s")
	public String industryL1s(@PathVariable long id) {
		return GsonBuilderFactory.createGsonBuilder(
					Arrays.asList("dictExtIndustry","createTime","dictExtIndustryL2s")
				).toJson(dictExtIndustryL1Service.findByIndustryId(id));
	}
	
	@GetMapping("/dict_ext/industry_l1s/{id}/industry_l2s")
	public String industryL2s(@PathVariable long id) {
		return GsonBuilderFactory.createGsonBuilder(
					Arrays.asList("dictExtIndustryL1","createTime","dictExtIndustryL3s")
				).toJson(dictExtIndustryL2Service.findByIndustryl1Id(id));
	}
	
	@GetMapping("/dict_ext/industry_l2s/{id}/industry_l3s")
	public String industryL3s(@PathVariable long id) {
		return GsonBuilderFactory.createGsonBuilder(
					Arrays.asList("dictExtIndustryL2","createTime")
				).toJson(dictExtIndustryL3Service.findByIndustryl2Id(id));
	}
}
