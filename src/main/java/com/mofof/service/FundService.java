package com.mofof.service;

import com.alibaba.fastjson.JSONObject;
import com.mofof.entity.administrator.Contact;
import com.mofof.entity.common.License;
import com.mofof.entity.fund.AdminFee;
import com.mofof.entity.fund.Fund;
import com.mofof.entity.fund.FundAdminTeam;
import com.mofof.entity.fund.TimeLine;
import com.mofof.entity.relation.InvestRelation;
import com.mofof.entity.relation.ResponsePerson;
import com.mofof.repository.*;
import com.mofof.util.DataServerHttpUtils;
import com.mofof.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by ChenErliang on 17/5/5.
 */
@Service
@Transactional
public class FundService {

    @Autowired
    private FundRepository fundRepository;
    @Autowired
    private InvestRelationRepository investRelationRepository;
    @Autowired
    private TimelineRepository timelineRepository;

    @Autowired
    private FundAdminTeamRepository fundAdminTeamRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ResponsePersonRepository responsePersonRepository;

    public Fund save(Fund fund) {

        //新增时,初始化管理费
        TimeLine timeLine =null;
        if(fund!=null&&fund.getId()==null){
            timeLine = new TimeLine();

            List<AdminFee> list = new ArrayList<>();
            AdminFee adminFee1 = new AdminFee();
            adminFee1.setFeePercent(0);
            adminFee1.setFundStatus("投资期");
            list.add(adminFee1);

            AdminFee adminFee2 = new AdminFee();
            adminFee2.setFeePercent(0);
            adminFee2.setFundStatus("回收期");
            list.add(adminFee2);

            AdminFee adminFee3 = new AdminFee();
            adminFee3.setFeePercent(0);
            adminFee3.setFundStatus("延长期");
            list.add(adminFee3);

            timeLine.setAdminFees(list);
            timeLine.setFund(fund);
            timelineRepository.save(timeLine);
        }

        fundRepository.save(fund);

        if(timeLine!=null){
            timeLine.setFund(fund);
            timeLine.setOffsetDays(0);
            timelineRepository.save(timeLine);
        }
        return fund;
    }

    /**
     *根据条件查询fund
     * @param pageRequest
     * @param fundType
     * @param organizeForm
     * @param investStatus
     * @param fullName
     * @param chineseName
     * @param englishName
     * @return
     */
    public Page<Fund> search(PageRequest pageRequest, String fundType, String organizeForm, String investStatus, String fullName, String chineseName,String englishName) {
        Specification<Fund> specification = new Specification<Fund>() {
            @Override
            public Predicate toPredicate(Root<Fund> root,
                                         CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                if (!StringUtils.isEmpty(fundType)) {
                    Predicate p = cb.equal(root.get("fundType").as(String.class), fundType);
                    list.add(p);
                }
                if (!StringUtils.isEmpty(organizeForm)) {
                    Predicate p = cb.equal(root.get("organizeForm").as(String.class), organizeForm);
                    list.add(p);
                }
                if (!StringUtils.isEmpty(investStatus)) {
                    Predicate p = cb.equal(root.get("investStatus").as(String.class), investStatus);
                    list.add(p);
                }
                if (null != fullName) {
                    Predicate p2 = cb.like(root.join("organization").get("fullName").as(String.class), "%"+fullName+"%");
                    list.add(p2);
                }
                if (null != chineseName) {
                    Predicate p2 = cb.like(root.join("organization").get("chineseName").as(String.class), "%"+chineseName+"%");
                    list.add(p2);
                }
                if (null != englishName) {
                    Predicate p2 = cb.like(root.join("organization").get("englishName").as(String.class), "%"+englishName+"%");
                    list.add(p2);
                }
//              构建组合的Predicate：
//                Predicate p = cb.and(p3, cb.or(p1, p2));
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };
        Page<Fund> page = fundRepository.findAll(specification, pageRequest);
        return page;
    }


    public Iterable<Fund> findAll() {
        return fundRepository.findAllByOrderByIdDesc();
    }

    public Iterable<Fund> findAllByInvestStatus(String investStatus) {
        return fundRepository.findAllByInvestStatusOrderByIdDesc(investStatus);
    }

    public Fund findById(Long id) {
        return fundRepository.findOne(id);
    }

    public Iterable<InvestRelation> findAllRelation() {
        return investRelationRepository.findAllByInvestorPlatformOrderByIdDesc(true);
    }

    public InvestRelation findRelationById(Long id) {
        return investRelationRepository.findOne(id);
    }

    public TimeLine findTimelineByFundId(Long id) {
        TimeLine timeLine = timelineRepository.findByFundId(id);
        return timeLine;
    }


    public TimeLine saveTimeLine(TimeLine timeLine) {
        return timelineRepository.save(timeLine);
    }

    public Iterable<FundAdminTeam> findAdminTeamByFundId(Long id) {
        return fundAdminTeamRepository.findByFundId(id);
    }

    public Iterable<Contact> findContactsByFundId(Long id) {
        return contactRepository.findContactByFundId(id);
    }

    public Iterable<FundAdminTeam> addFundAdminTeams(FundAdminTeam[] fundAdminTeams) {
        List<FundAdminTeam> pList = Arrays.asList(fundAdminTeams);
        return fundAdminTeamRepository.save(pList);
    }

    public Iterable<FundAdminTeam> modifyFundAdminTeams(FundAdminTeam[][] fundAdminTeams) {
        List<FundAdminTeam> mList = Arrays.asList(fundAdminTeams[0]);
        List<FundAdminTeam> dList = Arrays.asList(fundAdminTeams[1]);
        fundAdminTeamRepository.delete(dList);
        return fundAdminTeamRepository.save(mList);
    }

    public ResponsePerson findResponsePerson(Long id) {
        return responsePersonRepository.findOne(id);
    }

    public List<ResponsePerson> findAllResponsePersons(Long investRelationId) {
        return responsePersonRepository.findAllByInvestRelationId(investRelationId);
    }

    public void deleteResponsePerson(Long id) {
        responsePersonRepository.delete(id);
    }

    public ResponsePerson saveResponsePerson(ResponsePerson responsePerson) {
        return responsePersonRepository.save(responsePerson);
    }

    public void saveResponsePersons(List<ResponsePerson> prps) {
        if (prps != null) {
            prps.forEach(prp -> {
                if (prp.getId() != null) {
                    this.deleteResponsePerson(prp.getId());
                } else {
                    this.saveResponsePerson(prp);
                }

            });
        }
        ;
    }

    /**
     * 更新证照信息
     * @param id
     * @return
     */
    public Fund updateRegInfo(Long id){
        Fund fund = fundRepository.findOne(id);
        JSONObject jsonObject = DataServerHttpUtils.getRegInfo(fund.getOrganization().getFullName());
        if(jsonObject!=null){
            //System.out.println(jsonObject);
            License license = new License();
            license.setLicenseName(jsonObject.getString("name"));
            license.setUniformCode(jsonObject.getString("no"));
            license.setType(jsonObject.getString("econKind"));
            license.setArtificialPerson(jsonObject.getString("operName"));
            license.setRegCapital(jsonObject.getString("registCapi")+"万");
            license.setFoundDate(DateUtil.getLocalDate(jsonObject.getString("start_date")));
            license.setRegAddress(jsonObject.getString("address"));
            license.setBusinessStartDate(DateUtil.getLocalDate(jsonObject.getString("term_start")));
            license.setBusinessEndDate(DateUtil.getLocalDate(jsonObject.getString("term_end")));
            license.setBusinessScope(jsonObject.getString("scope"));
            license.setRegAuthority(jsonObject.getString("belongOrg"));
            license.setRegStatus(jsonObject.getString("status"));
            license.setApprovalDate(DateUtil.getLocalDate(jsonObject.getString("check_date")));
            license.setRevokeDate(DateUtil.getLocalDate(jsonObject.getString("end_date")));
            license.setLastUpdateTime(LocalDateTime.now());
            fund.getOrganization().setLicense(license);
            fundRepository.save(fund);
        }
        return fund;
    }

    /**
     * 根据基金基本信息分组
     * @return
     */
    public Map<String, Map<String,Object>> fundListGroupByFundType(){
        Iterable<Fund> funds = fundRepository.findAllByOrderByIdDesc();

        return null;
    }

}
