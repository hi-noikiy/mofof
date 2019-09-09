package com.mofof.service;

import com.mofof.dto.SelectOption;
import com.mofof.repository.InvestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Created by ChenErliang on 17/5/4.
 */
@Service
@Transactional
public class AutoCompleteService {

    @Autowired
    private InvestorRepository investorRepository;

    public final Map<String, Supplier<Map<String, List<SelectOption>>>> nameMap = new HashMap<>();

    public AutoCompleteService() {
        nameMap.put("test", this::getTest);
    }

    public Map<String, Map<String, List<SelectOption>>> getAutoComplete(List<String> keyNames) {
        Map<String, Map<String, List<SelectOption>>> map = new HashMap<>();
        keyNames.forEach(keyName -> {
            Supplier<Map<String, List<SelectOption>>> supplier = this.nameMap.get(keyName);
            if (supplier != null) {
                map.put(keyName, supplier.get());
            }
        });
        return map;
    }

    private Map<String, List<SelectOption>> getTest() {
        Map<String, List<SelectOption>> result = new HashMap<>();
        List<SelectOption> investorList = new ArrayList<>();
        List<SelectOption> platformList = new ArrayList<>();
        investorRepository.findAll().forEach(investor -> {
            if (investor.getPlatform()) {
                SelectOption selectOption = new SelectOption(investor.getId(), investor.getChineseName());
                platformList.add(selectOption);
            } else {
                SelectOption selectOption = new SelectOption(investor.getId(), investor.getChineseName());
                investorList.add(selectOption);
            }
        });
        result.put("used", investorList);
        result.put("unused", platformList);
        return result;
    }


}
