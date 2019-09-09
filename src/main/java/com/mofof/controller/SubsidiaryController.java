package com.mofof.controller;

import com.mofof.entity.fund.Subsidiary;
import com.mofof.service.SubsidiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hzh on 17/6/14.
 */
@RestController
@RequestMapping(path = "/subsidiary")
public class SubsidiaryController {
    @Autowired
    private SubsidiaryService subsidiaryService;

    @GetMapping(path = "/subsidiarys")
    public List<Subsidiary> subsidiarys(Long fundId) {
        return subsidiaryService.findByFundId(fundId);
    }

    /**
     * 根据基金id和机构类型获取下属机构
     * @param fundId
     * @param subsidiaryType
     * @return
     */
    @GetMapping(path = "/subsidiaryByType")
    public Subsidiary subsidiarysByType(Long fundId, String subsidiaryType) {
        return subsidiaryService.findByFundIdAndType(fundId, subsidiaryType);
    }
    
    @PostMapping(path = "/deleteSubsidiary")
    public String deleteSubsidiary(@RequestBody Subsidiary subsidiary) {
    	String flag="success";
    	try {
    		subsidiaryService.deleteByFundIdAndType(subsidiary.getFund().getId(),subsidiary.getSubsidiaryType());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			flag="fail";
		}
        return flag;
    }
    
    @GetMapping(path = "/subsidiary")
    public Subsidiary subsidiary(Long id) {
        return subsidiaryService.findOne(id);
    }

    @PostMapping(path = "/save")
    public Subsidiary save(@RequestBody Subsidiary subsidiary) {
        return subsidiaryService.save(subsidiary);
    }
    @PostMapping(path = "/saveSubsidiarys")
    public void saveSubsidiarys(@RequestBody List<Subsidiary> subsidiarys) {
        subsidiaryService.saveSubsidiarys(subsidiarys);
    }
    @PostMapping(path = "/delete")
    public void delete(@RequestBody Subsidiary subsidiary) {
         subsidiaryService.delete(subsidiary);
    }

}
