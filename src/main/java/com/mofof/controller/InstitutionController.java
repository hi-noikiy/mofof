package com.mofof.controller;

import com.mofof.entity.fund.Institution;
import com.mofof.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hzh on 17/6/14.
 */
@RestController
@RequestMapping(path = "/institution")
public class InstitutionController {
    @Autowired
    private InstitutionService institutionService;

    @GetMapping(path = "/institutions")
    public List<Institution> institutions(Long fundId) {
        return institutionService.findByFundId(fundId);
    }

    @GetMapping(path = "/typeInstitutions")
    public List<Institution> institutionsByType(Long fundId,String institutionType) {
        return institutionService.findByFundIdAndType(fundId, institutionType);
    }
    
    @PostMapping(path = "/deleteInstitution")
    public String deleteInstitution(@RequestBody Institution institution) {
    	String flag="success";
    	try {
    		institutionService.deleteByFundIdAndType(institution.getFund().getId(),institution.getInstitutionType());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			flag="fail";
		}
        return flag;
    }
    
    @GetMapping(path = "/institution")
    public Institution institution(Long id) {
        return institutionService.findOne(id);
    }

    @PostMapping(path = "/save")
    public Institution save(@RequestBody Institution institution) {
        return institutionService.save(institution);
    }
    @PostMapping(path = "/saveInstitutions")
    public void saveInstitutions(@RequestBody List<Institution> institutions) {
        institutionService.saveInstitutions(institutions);
    }

}
