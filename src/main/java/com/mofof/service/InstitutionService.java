package com.mofof.service;

import com.mofof.entity.fund.Institution;
import com.mofof.repository.InstitutionRepository;
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
public class InstitutionService {
    @Autowired
    private InstitutionRepository institutionRepository;

    public List<Institution> findByFundId(Long fundId) {
        return institutionRepository.findAllByFundIdOrderByChangeDateDesc(fundId);
    }

    public List<Institution> findByFundIdAndType(Long fundId,String type) {
        return institutionRepository.findAllByFundIdAndInstitutionTypeOrderByChangeDateDesc(fundId,type);
    }
    public void deleteByFundIdAndType(Long fundId,String type)throws Exception{
        List<Institution> institutions=institutionRepository.findAllByFundIdAndInstitutionTypeOrderByChangeDateDesc(fundId,type);
        institutionRepository.delete(institutions);
    }
    public Institution findOne(Long id) {
        return institutionRepository.findOne(id);
    }

    public Institution save(Institution institution) {
        institution.setChangeDate(LocalDate.now());
        return institutionRepository.save(institution);
    }
    public void saveInstitutions(List<Institution> institutions) {
    	if (institutions!=null&&institutions.size()>0) {
    		List<Institution> oldDeleteList=institutionRepository.findAllByFundIdAndInstitutionTypeOrderByChangeDateDesc(institutions.get(0).getFund().getId(), institutions.get(0).getInstitutionType());
    		List<Institution> newDeleteList=new ArrayList<Institution>();
    		newDeleteList.addAll(oldDeleteList);
			if (oldDeleteList!=null&&oldDeleteList.size()>0) {
				for(Institution oldInstitution:oldDeleteList){
					for(Institution newInstitution:institutions){
						if (oldInstitution.getId().equals(newInstitution.getId())) {//表示已存在
							newDeleteList.remove(oldInstitution);
							break;
						}
					}
				}
				institutionRepository.delete(newDeleteList);
			}
		}
        institutionRepository.save(institutions);
    }
}
