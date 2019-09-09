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
import com.mofof.entity.dict.ext.DictExtContinent;
import com.mofof.entity.dict.ext.DictExtCountry;
import com.mofof.entity.dict.ext.DictExtDistrict;
import com.mofof.entity.dict.ext.DictExtProvince;
import com.mofof.repository.DictExtCityRepository;
import com.mofof.repository.DictExtContinentRepository;
import com.mofof.repository.DictExtCountryRepository;
import com.mofof.repository.DictExtDistrictRepository;
import com.mofof.repository.DictExtProvinceRepository;

@Service
public class DictExtContinentService {

  @Autowired
  private DictExtContinentRepository dictExtContinentRepository;
  @Autowired
  private DictExtDistrictRepository dictExtDistrictRepository;
  @Autowired
  private DictExtCityRepository dictExtCityRepository;
  @Autowired
  private DictExtProvinceRepository dictExtProvinceRepository;
  @Autowired
  private DictExtCountryRepository dictExtCountryRepository;

  public Set<DictExtContinent> findAll() {
    return dictExtContinentRepository.findAll();
  }

  public Set<DictExtContinent> save(Set<DictExtContinent> continents) {
    return (Set<DictExtContinent>) dictExtContinentRepository.save(continents);
  }

  /**
   * 初始化远程字典
   */
  public void initDictionary(Resource resource) {

    try {
      String dictionaryString = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
      JSONArray jsonArray = JSON.parseArray(dictionaryString);
      Set<DictExtContinent> continents = new HashSet<DictExtContinent>();
      for (int i = 0; i < jsonArray.size(); i++) {
        JSONObject jsonObject = jsonArray.getJSONObject(i);
        DictExtContinent continent = new DictExtContinent();
        continent.setId(3L + i);
        continent.setNameCn(jsonObject.getString("nameCn"));
        continent.setNameEn(jsonObject.getString("nameEn"));
        if (jsonObject.containsKey("rank")) {
          continent.setRank(jsonObject.getInteger("rank"));
        } else {
          continent.setRank(1);
        }

        Set<DictExtCountry> countries = new HashSet<DictExtCountry>();
        JSONArray jsonCountries = jsonObject.getJSONArray("dictExtCountries");
        for (int j = 0; j < jsonCountries.size(); j++) {
          DictExtCountry country = new DictExtCountry();
          Object jsonCountry1 = jsonCountries.get(j);
          if (jsonCountry1 instanceof JSONObject) {
            JSONObject jsonCountry = (JSONObject) jsonCountry1;
            country.setNameCn(jsonCountry.getString("nameCn"));
            if (jsonCountry.containsKey("nameEn")) {
              country.setNameEn(jsonCountry.getString("nameEn"));
            }
            if (jsonCountry.containsKey("rank")) {
              country.setRank(jsonCountry.getIntValue("rank"));
            }
            Set<DictExtProvince> provinces = new HashSet<DictExtProvince>();
            JSONArray jsonProvinces = jsonCountry.getJSONArray("dixtDictExtProvinces");
            for (int a = 0; a < jsonProvinces.size(); a++) {
              DictExtProvince province = new DictExtProvince();
              Object jsonProvince1 = jsonProvinces.get(a);
              if (jsonProvince1 instanceof JSONObject) {
                JSONObject jsonProvince = (JSONObject) jsonProvince1;
                province.setShortName(jsonProvince.getString("shortName"));
                if (jsonProvince.containsKey("fullName")) {
                  province.setFullName(jsonProvince.getString("fullName"));
                }
                if (jsonProvince.containsKey("provinceCode")) {
                  province.setProvinceCode(jsonProvince.getString("provinceCode"));
                }
                if (jsonProvince.containsKey("provinceNo")) {
                  province.setProvinceNo(jsonProvince.getString("provinceNo"));
                }
                Set<DictExtCity> cities = new HashSet<DictExtCity>();
                JSONArray jsonCities = jsonProvince.getJSONArray("dictExtCities");
                for (int b = 0; b < jsonCities.size(); b++) {
                  DictExtCity city = new DictExtCity();
                  Object jsonCity1 = jsonCities.get(b);
                  if (jsonCity1 instanceof JSONObject) {
                    JSONObject jsonCity = (JSONObject) jsonCity1;
                    city.setShortName(jsonCity.getString("shortName"));
                    if (jsonCity.containsKey("fullName")) {
                      city.setFullName(jsonCity.getString("fullName"));
                    }
                    if (jsonCity.containsKey("cityCode")) {
                      city.setCityCode(jsonCity.getString("cityCode"));
                    }
                    if (jsonCity.containsKey("cityNo")) {
                      city.setCityNo(jsonCity.getString("cityNo"));
                    }
                    Set<DictExtDistrict> districts = new HashSet<DictExtDistrict>();
                    JSONArray jsonDistricts = jsonCity.getJSONArray("dictExtDistricts");
                    for (int c = 0; c < jsonDistricts.size(); c++) {
                      DictExtDistrict district = new DictExtDistrict();
                      Object jsonDistrict1 = jsonDistricts.get(c);
                      if (jsonDistrict1 instanceof JSONObject) {
                        JSONObject jsonDistrict = (JSONObject) jsonDistrict1;
                        district.setShortName(jsonDistrict.getString("shortName"));
                        if (jsonDistrict.containsKey("fullName")) {
                          district.setFullName(jsonDistrict.getString("fullName"));
                        }
                        if (jsonDistrict.containsKey("districtCode")) {
                          district.setDistrictCode(jsonDistrict.getString("districtCode"));
                        }
                        if (jsonDistrict.containsKey("districtNo")) {
                          district.setDistrictNo(jsonDistrict.getString("districtNo"));
                        }
                      }
                      city = dictExtCityRepository.save(city);                     
                      district.setDictExtCity(city);
                      districts.add(district);  
                    }
                    dictExtDistrictRepository.save(districts);
                  }
                  province = dictExtProvinceRepository.save(province);
                  city = dictExtCityRepository.findByFullName(city.getFullName());
                  city.setDictExtProvince(province);  
                  dictExtCityRepository.save(city);
                }
              }
              country = dictExtCountryRepository.save(country);
              province = dictExtProvinceRepository.findByFullName(province.getFullName());
              province.setDictExtCountry(country); 
              dictExtProvinceRepository.save(province);
            }
          }
          continent = dictExtContinentRepository.save(continent);
          country = dictExtCountryRepository.findByNameEn(country.getNameEn());
          country.setDictExtContinent(continent);
          dictExtCountryRepository.save(country);
        }
      }    
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void deleteAll() {
    dictExtContinentRepository.deleteAll();

  }

  public boolean existsByNameEn(String nameEn) {  
    return  dictExtContinentRepository.existsByNameEn(nameEn);
  }

}
