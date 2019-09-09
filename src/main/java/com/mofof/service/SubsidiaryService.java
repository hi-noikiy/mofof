package com.mofof.service;

import com.mofof.entity.fund.Subsidiary;
import com.mofof.repository.SubsidiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzh on 17/6/14.
 */
@Service
@Transactional
public class SubsidiaryService {
    @Autowired
    private SubsidiaryRepository subsidiaryRepository;

    public List<Subsidiary> findByFundId(Long fundId) {
        return subsidiaryRepository.findAllByFundIdOrderByChangeDateDesc(fundId);
    }

    public Subsidiary findByFundIdAndType(Long fundId, String type) {
        return subsidiaryRepository.findByFundIdAndSubsidiaryTypeOrderByChangeDateDesc(fundId,type);
    }
    public void deleteByFundIdAndType(Long fundId,String type)throws Exception{
//        List<Subsidiary> subsidiarys=subsidiaryRepository.findByFundIdAndSubsidiaryTypeOrderByChangeDateDesc(fundId,type);
//        subsidiaryRepository.delete(subsidiarys);
    }
    public Subsidiary findOne(Long id) {
        return subsidiaryRepository.findOne(id);
    }

    public Subsidiary save(Subsidiary subsidiary) {
        subsidiary.setChangeDate(LocalDate.now());
        return subsidiaryRepository.save(subsidiary);
    }

    public void delete(Subsidiary subsidiary) {
         subsidiaryRepository.delete(subsidiary);
    }


    public void saveSubsidiarys(List<Subsidiary> subsidiarys) {
//    	if (subsidiarys!=null&&subsidiarys.size()>0) {
//    		List<Subsidiary> oldDeleteList=subsidiaryRepository.findByFundIdAndSubsidiaryTypeOrderByChangeDateDesc(subsidiarys.get(0).getFund().getId(), subsidiarys.get(0).getSubsidiaryType());
//    		List<Subsidiary> newDeleteList=new ArrayList<Subsidiary>();
//    		newDeleteList.addAll(oldDeleteList);
//			if (oldDeleteList!=null&&oldDeleteList.size()>0) {
//				for(Subsidiary oldSubsidiary:oldDeleteList){
//					for(Subsidiary newSubsidiary:subsidiarys){
//						if (oldSubsidiary.getId().equals(newSubsidiary.getId())) {//表示已存在
//							newDeleteList.remove(oldSubsidiary);
//							break;
//						}
//					}
//				}
//                subsidiaryRepository.delete(newDeleteList);
//			}
//		}
//        subsidiaryRepository.save(subsidiarys);
    }
}
