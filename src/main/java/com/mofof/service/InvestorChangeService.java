package com.mofof.service;

import com.mofof.entity.common.Individual;
import com.mofof.entity.common.Organization;
import com.mofof.entity.investor.Investor;
import com.mofof.entity.relation.InvestorChange;
import com.mofof.entity.relation.InvestorChangeDetail;
import com.mofof.entity.relation.InvestorChangeProcess;
import com.mofof.repository.InvestorChangeRepository;
import com.mofof.repository.InvestorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hzh on 17/7/10.
 */
@Service
@Transactional
public class InvestorChangeService {
    @Autowired
    InvestorChangeRepository investorChangeRepository;
    @Autowired
    InvestorRepository investorRepository;


    public List<InvestorChange> findAllByFundIdAndChangeDate(Long id,String changeDate) {
    	if (changeDate!=null) {
    		return investorChangeRepository.findAllByFundIdAndChangeDateLessThanEqualOrderByChangeDateDesc(id,getLocalDate(changeDate,"yyyy-MM-dd"));
		}else{
			return investorChangeRepository.findAllByFundIdOrderByChangeDateDesc(id);
		}
    }

    public InvestorChange save(InvestorChange investorChange) {
    	for(InvestorChangeDetail detail:investorChange.getInvestorChangeDetails()){
    		if (detail.getInvestor().getId()==null) {
				if (StringUtils.isEmpty(detail.getInvestor().getOrganization().getChineseName())) {
					List<Investor> list=(List<Investor>) investorRepository.findAllByIndividualChineseNameLike(detail.getInvestor().getIndividual().getChineseName());
					if (list!=null&&list.size()>0) {
						detail.setInvestor(list.get(0));
					}else{
						Investor investor=new Investor();
						Individual individual=new Individual();
						individual.setChineseName(detail.getInvestor().getChineseName());
						investor.setPlatform(false);
						investor.setType(0);
						investor.setIndividual(individual);
						investorRepository.save(investor);
						detail.setInvestor(investor);
					}
				}else{
					List<Investor> list=(List<Investor>) investorRepository.findAllByOrganizationChineseNameLike(detail.getInvestor().getOrganization().getChineseName());
					if (list!=null&&list.size()>0) {
						detail.setInvestor(list.get(0));
					}else{
						Investor investor=new Investor();
						Organization organization=new Organization();
						organization.setChineseName(detail.getInvestor().getChineseName());
						investor.setPlatform(false);
						investor.setType(1);
						investor.setOrganization(organization);
						investorRepository.save(investor);
						detail.setInvestor(investor);
					}
				}
			}else{
				detail.setInvestor(investorRepository.findOne(detail.getInvestor().getId()));
			}
    	}
    	for(InvestorChangeProcess process:investorChange.getInvestorChangeProcesses()){
    		if (process.getInvestor().getId()==null) {
				if (StringUtils.isEmpty(process.getInvestor().getOrganization().getChineseName())) {
					List<Investor> list=(List<Investor>) investorRepository.findAllByIndividualChineseNameLike(process.getInvestor().getIndividual().getChineseName());
					if (list!=null&&list.size()>0) {
						process.setInvestor(list.get(0));
					}else{
						Investor investor=new Investor();
						Individual individual=new Individual();
						individual.setChineseName(process.getInvestor().getChineseName());
						investor.setPlatform(false);
						investor.setType(0);
						investor.setIndividual(individual);
						investorRepository.save(investor);
						process.setInvestor(investor);
					}
				}else{
					List<Investor> list=(List<Investor>) investorRepository.findAllByOrganizationChineseNameLike(process.getInvestor().getOrganization().getChineseName());
					if (list!=null&&list.size()>0) {
						process.setInvestor(list.get(0));
					}else{
						Investor investor=new Investor();
						Organization organization=new Organization();
						organization.setChineseName(process.getInvestor().getChineseName());
						investor.setPlatform(false);
						investor.setType(1);
						investor.setOrganization(organization);
						investorRepository.save(investor);
						process.setInvestor(investor);
					}
				}
			}else {
				process.setInvestor(investorRepository.findOne(process.getInvestor().getId()));
			}
    	}
    	List<InvestorChangeProcess> processesList=investorChange.getInvestorChangeProcesses();
    	if (investorChange.getId()!=null) {//表示修改
    		processesList=new ArrayList<InvestorChangeProcess>();
    		InvestorChange oldChange=investorChangeRepository.findOne(investorChange.getId());//修改前的变更记录
    		for(InvestorChangeDetail newDetail:investorChange.getInvestorChangeDetails()){
    			boolean proFlag=true;//默认新增
    			for (InvestorChangeDetail oldDetail:oldChange.getInvestorChangeDetails()) {
					 if (newDetail.getInvestor().getChineseName().equals(oldDetail.getInvestor().getChineseName())) {
						 if (newDetail.getInvestAmount().compareTo(oldDetail.getInvestAmount())>0) {
							 InvestorChangeProcess process=new InvestorChangeProcess();
							 process.setChangeType("增资");
							 process.setInvestor(newDetail.getInvestor());
							 process.setInvestAmount(newDetail.getInvestAmount().subtract(oldDetail.getInvestAmount()));
							 processesList.add(process);
						 }else if (newDetail.getInvestAmount().compareTo(oldDetail.getInvestAmount())<0) {
							 InvestorChangeProcess process=new InvestorChangeProcess();
							 process.setChangeType("减资");
							 process.setInvestor(newDetail.getInvestor());
							 process.setInvestAmount(oldDetail.getInvestAmount().subtract(newDetail.getInvestAmount()));
							 processesList.add(process);
						}
						 proFlag=false;
						 break;
					}
				}
    			if (proFlag) {
    				 InvestorChangeProcess process=new InvestorChangeProcess();
					 process.setChangeType("增资");
					 process.setInvestor(newDetail.getInvestor());
					 process.setInvestAmount(newDetail.getInvestAmount()==null?BigDecimal.ZERO:process.getInvestAmount());
					 processesList.add(process);
				}
    		}
    	}
			List<InvestorChange> changes=investorChangeRepository.findAllByFundIdAndChangeDateGreaterThanOrderByChangeDateDesc(investorChange.getFund().getId(), investorChange.getChangeDate());
			for(InvestorChange change:changes){
				for(InvestorChangeProcess process:processesList){
					boolean flag=true;//默认表示新增投资人
					for(InvestorChangeDetail detail:change.getInvestorChangeDetails()){
						if (process.getInvestor().getChineseName().equals(detail.getInvestor().getChineseName())) {
							if ("增资，受让，划入，新名".contains(process.getChangeType())) {
								detail.setInvestAmount(detail.getInvestAmount().add(process.getInvestAmount()==null?BigDecimal.ZERO:process.getInvestAmount()));
							}else{
								detail.setInvestAmount(detail.getInvestAmount().subtract(process.getInvestAmount()==null?BigDecimal.ZERO:process.getInvestAmount()));
							}
							//detail.setInvestAmount(process.getInvestAmount()==null?BigDecimal.ZERO:process.getInvestAmount());
							flag=false;
							break;
						}
					}
					if (flag) {
						InvestorChangeDetail newDetail=new InvestorChangeDetail();
						newDetail.setInvestAmount(process.getInvestAmount()==null?BigDecimal.ZERO:process.getInvestAmount());
						newDetail.setInvestor(process.getInvestor());
						change.getInvestorChangeDetails().add(newDetail);
					}
				}
				investorChangeRepository.save(change);
			}
        return investorChangeRepository.save(investorChange);
    }

    /**
	 * 通过时间格式获取LocalDate
	 * @param dateStr
	 * @return
	 * @author hwf
	 * @date 2017年8月1日
	 */
	public static LocalDate getLocalDate(String dateStr,String dataFormat){
        try {
        	DateFormat df = new SimpleDateFormat(dataFormat);   
			Date date = df.parse(dateStr);
			return getLocalDateByDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		} 
	}
	/**
	 * 将date类型转成 LocalDate
	 * @param date
	 * @return
	 * @author hwf
	 * @date 2017年8月1日
	 */
	public static LocalDate getLocalDateByDate(Date date){
		Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        return localDate;
	}
}
