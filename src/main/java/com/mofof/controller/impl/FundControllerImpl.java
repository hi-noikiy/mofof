//package com.mofof.controller.impl;
//
//import com.mofof.controller.FundController;
//import com.mofof.entity.administrator.Contact;
//import com.mofof.entity.fund.Fund;
//import com.mofof.entity.fund.FundAdminTeam;
//import com.mofof.entity.fund.TimeLine;
//import com.mofof.entity.relation.InvestRelation;
//import com.mofof.entity.relation.ResponsePerson;
//import com.mofof.service.FundService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
///**
// * Created by hzh on 2018/11/18.
// */
//@RestController
//public class FundControllerImpl implements FundController{
//
//    @Autowired
//    FundService fundService;
//
//    @Override
//    public Fund saveRecord(@RequestBody Fund fund) {
//        return fundService.save(fund);
//    }
//
//    @Override
//    public Iterable<Fund> allFunds() {
//        return fundService.findAll();
//    }
//
//    @Override
//    public Iterable<Fund> findByInvestStatus(String investStatus) {
//        return fundService.findAllByInvestStatus(investStatus);
//    }
//
//    @Override
//    public  Iterable<InvestRelation> allRelations() {
//        Iterable<InvestRelation> list = fundService.findAllRelation();
//        //System.out.println(JSON.toJSONString(list));
////        return JSON.toJSONString(list);
//        return list;
//    }
//
//    @Override
//    public Fund getFund(Long id) {
//        return fundService.findById(id);
//    }
//
//    @Override
//    public InvestRelation getRelation(Long id) {
//        return fundService.findRelationById(id);
//    }
//
//    @Override
//    public TimeLine getTimeline(Long fundId) {
//        return fundService.findTimelineByFundId(fundId);
//    }
//
//    @Override
//    public TimeLine saveTimeLine(@RequestBody TimeLine timeline) {
//        return fundService.saveTimeLine(timeline);
//    }
//
//    @Override
//    public Iterable<FundAdminTeam> getFundAdminTeam(Long id) {
//        return fundService.findAdminTeamByFundId(id);
//    }
//
//    @Override
//    public Iterable<Contact> getContacts(Long id) {
//        return fundService.findContactsByFundId(id);
//    }
//
//    @Override
//    public Iterable<FundAdminTeam> addFundAdminTeams(@RequestBody FundAdminTeam[] fundAdminTeams) {
//        return fundService.addFundAdminTeams(fundAdminTeams);
//    }
//
//    @Override
//    public Iterable<FundAdminTeam> saveFundAdminTeams(@RequestBody FundAdminTeam[][] fundAdminTeams) {
//        return fundService.modifyFundAdminTeams(fundAdminTeams);
//    }
//
//    @Override
//    public List<ResponsePerson> projectResponsePersons(Long id) {
//        return fundService.findAllResponsePersons(id);
//    }
//
//    @Override
//    public ResponsePerson responsePerson(Long id) {
//        return fundService.findResponsePerson(id);
//    }
//
//    @Override
//    public String saveResponsePersons(@RequestBody List<ResponsePerson> prps) {
//        fundService.saveResponsePersons(prps);
//        return " ";
//    }
//}
//
