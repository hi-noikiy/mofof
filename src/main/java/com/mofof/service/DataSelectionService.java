package com.mofof.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import com.alibaba.fastjson.JSON;
import com.mofof.entity.dict.ext.Bank;
import com.mofof.entity.dict.ext.BankType;
import com.mofof.entity.dict.ext.BranchBank;
import com.mofof.entity.fund.Agreement;
import com.mofof.entity.fund.FundAccount;
import com.mofof.entity.investor.InvestorAccount;
// import com.mofof.entity.platform.PlatformAccount;
import com.mofof.entity.relation.InvestRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mofof.dto.SelectOption;
import com.mofof.entity.administrator.Administrator;
import com.mofof.entity.administrator.TeamMember;
import com.mofof.entity.affiliate.Affiliate;
import com.mofof.entity.common.Organization;
import com.mofof.entity.fund.Fund;
import com.mofof.entity.system.Department;
import com.mofof.entity.system.Role;
import com.mofof.entity.system.User;
import com.mofof.entity.system.UserAccount;
import com.mofof.repository.AdministratorRepository;
import com.mofof.repository.AffiliateRepository;
import com.mofof.repository.DepartmentRepository;
import com.mofof.repository.FundRepository;
import com.mofof.repository.OrganizationRepository;
import com.mofof.repository.RoleRepository;
import com.mofof.repository.TeamMemberRepository;
import com.mofof.repository.UserAccountRepository;
import com.mofof.entity.fund.FundAdminTeam;
import com.mofof.repository.*;

import java.util.function.Function;

/**
 * Created by ChenErliang on 17/5/4.
 */
@Service
@Transactional
public class DataSelectionService {

    @Autowired
    private AdministratorRepository administratorRepository;
    @Autowired
    private AffiliateRepository affiliateRepository;
    @Autowired
    private FundRepository fundRepository;
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private TeamMemberRepository teamMemberRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    FundAdminTeamRepository fundAdminTeamRepository;
    @Autowired
    FundAccountRepository fundAccountRepository;
    @Autowired
    InvestorAccountRepository investorAccountRepository;
    // PlatformAccountRepository platformAccountRepository;
    @Autowired
    InvestRelationRepository investRelationRepository;
    @Autowired
    AgreementRepository agreementRepository;
    @Autowired
    BankRepository bankRepository;
    @Autowired
    BankTypeRepository bankTypeRepository;
    @Autowired
    BranchBankRepository branchBankRepository;
    @Autowired
    UserRepository userRepository;

    public final Map<String, Supplier<List<SelectOption>>> nameMap = new HashMap<>();

    public final Map<String, Function<String, List<SelectOption>>> nameMap2 = new HashMap<>();

    public DataSelectionService() {
//        nameMap.put("administrator", this::getAdministrators);
//        nameMap.put("affiliate", this::getAffiliates);
//        nameMap.put("fund", this::getFunds);
//        nameMap.put("user", this::getUsers);
//        nameMap.put("organization", this::getOrganizations);
//        nameMap.put("teamMember", this::getTeamMembers);
//        nameMap.put("role", this::getRoles);
//        nameMap.put("department", this::getDepartments);
//        nameMap.put("teamMemberForFund", this::getTeamMemberForFund);


        nameMap2.put("administrator", this::getAdministrators);
        nameMap2.put("affiliate", this::getAffiliates);
        nameMap2.put("fund", this::getFunds);
        //nameMap2.put("user", this::getUsers);
        nameMap2.put("organization", this::getOrganizations);
        nameMap2.put("teamMember", this::getTeamMembers);
        nameMap2.put("role", this::getRoles);
        nameMap2.put("department", this::getDepartments);
        nameMap2.put("teamMemberForFund", this::getTeamMemberForFund);
        nameMap2.put("fundAccount", this::getFundAccount);
        nameMap2.put("investorAccount", this::getInvestorAccount);
        // nameMap2.put("platformAccount", this::getPlatformAccount);
        nameMap2.put("agreement", this::getAgreement);
        nameMap2.put("bankType", this::getBankType);
        nameMap2.put("bank", this::getBank);
        nameMap2.put("branch", this::getBranch);
    }


//    public Map<String, List<SelectOption>> getDataSelection(List<String> keyNames) {
//        Map<String, List<SelectOption>> map = new HashMap<>();
//        keyNames.forEach(keyName -> {
//            Supplier<List<SelectOption>> supplier = this.nameMap.get(keyName);
//            if (supplier != null) {
//                map.put(keyName, supplier.get());
//            }
//        });
//        return map;
//    }

    public Map<String, List<SelectOption>> getDataSelection(List<String> keyNames, Map<String, String> params) {
        Map<String, List<SelectOption>> map = new HashMap<>();

        Map<String, String> paramsMap = JSON.parseObject(params.get("params"), Map.class);

        keyNames.forEach(keyName -> {
            Function<String, List<SelectOption>> function = this.nameMap2.get(keyName);
//            Supplier<List<SelectOption>> supplier = this.nameMap.get(keyName);
            if (function != null) {
                map.put(keyName, function.apply(paramsMap!=null?paramsMap.get(keyName):null));
//                map.put(keyName, function.apply(params.get(keyName)));
            }
        });
        return map;
    }

    private List<SelectOption> getAdministrators(String params) {
        Iterable<Administrator> administrators = administratorRepository.findAll();
        List<SelectOption> dataSelections = new ArrayList<>();
        administrators.forEach(administrator -> dataSelections.add(new SelectOption(administrator.getId(), administrator.getOrganization().getChineseName())));
        return dataSelections;
    }


    private List<SelectOption> getAffiliates(String params) {
        Iterable<Affiliate> affiliates = affiliateRepository.findAll();
        List<SelectOption> dataSelections = new ArrayList<>();
        affiliates.forEach(affiliate -> dataSelections.add(new SelectOption(affiliate.getId(), "(" + (affiliate.getOrganization().getChineseName() != null && !"".equals(affiliate.getOrganization().getChineseName()) ? affiliate.getOrganization().getChineseName() : affiliate.getOrganization().getEnglishName()) + ")" + affiliate.getOrganization().getFullName())));
        return dataSelections;
    }

    private List<SelectOption> getFunds(String params) {
        Iterable<Fund> funds = fundRepository.findAll();
        List<SelectOption> dataSelections = new ArrayList<>();
        funds.forEach(fund -> dataSelections.add(new SelectOption(fund.getId(), "(" + (fund.getOrganization().getChineseName() != null && !"".equals(fund.getOrganization().getChineseName()) ? fund.getOrganization().getChineseName() : fund.getOrganization().getEnglishName()) + ")" + fund.getOrganization().getFullName())));
        return dataSelections;
    }

    private List<SelectOption> getOrganizations(String params) {
        Iterable<Organization> organizations = organizationRepository.findAll();
        List<SelectOption> dataSelections = new ArrayList<>();
        organizations.forEach(organization -> dataSelections.add(new SelectOption(organization.getId(), organization.getChineseName())));
        return dataSelections;
    }
// 注释是因user,userAccount的属性调整,
//    private List<SelectOption> getUsers(String params) {
//        Iterable<UserAccount> users = userAccountRepository.findAllByStatusOrderByIdDesc(1);
//        List<SelectOption> dataSelections = new ArrayList<>();
//        users.forEach(userAccount -> dataSelections.add(new SelectOption(userAccount.getUser().getId(), userAccount.getUser().getName())));
//        return dataSelections;
//    }

    private List<SelectOption> getTeamMembers(String params) {
        Iterable<TeamMember> teamMembers = teamMemberRepository.findAll();
        List<SelectOption> dataSelections = new ArrayList<>();
        teamMembers.forEach(tm -> dataSelections.add(new SelectOption(tm.getId(), tm.getIndividual().getChineseName())));
        return dataSelections;
    }

    private List<SelectOption> getRoles(String params) {
        Iterable<Role> roles = roleRepository.findAll();
        List<SelectOption> dataSelections = new ArrayList<>();
        roles.forEach(role -> dataSelections.add(new SelectOption(role.getId(), role.getName())));
        return dataSelections;
    }

    private List<SelectOption> getDepartments(String params) {
        Iterable<Department> departments = departmentRepository.findAll();
        List<SelectOption> dataSelections = new ArrayList<>();
        departments.forEach(dept -> dataSelections.add(new SelectOption(dept.getId(), dept.getName())));
        return dataSelections;
    }

    private List<SelectOption> getTeamMemberForFund(String params) {
        //params 为fundId
        Iterable<FundAdminTeam> fundAdminTeams;
        List<SelectOption> dataSelections = new ArrayList<>();
        if (params != null && !"".equals(params)) {
            fundAdminTeams = fundAdminTeamRepository.findByFundId(Long.valueOf(params));
        } else {
            fundAdminTeams = fundAdminTeamRepository.findAll();
        }
        List<Long> ids = new ArrayList<>();
        fundAdminTeams.forEach(fundAdminTeam -> {
            Long id = fundAdminTeam.getAdministratorTeam().getTeamMember().getId();
            if (!ids.contains(id)) {
                dataSelections.add(new SelectOption(id, fundAdminTeam.getAdministratorTeam().getTeamMember().getIndividual().getChineseName()));
            }
        });
        return dataSelections;
    }

    private List<SelectOption> getFundAccount(String params) {
        //params 为invest-relation id
        Iterable<FundAccount> fundAccounts = new ArrayList<>();
        List<SelectOption> dataSelections = new ArrayList<>();
        if (params != null && !"".equals(params)) {

            InvestRelation ir = investRelationRepository.findOne(Long.valueOf(params));
            if (ir != null) {
                fundAccounts = fundAccountRepository.findByFundId(ir.getFund().getId());
            }
        } else {
            fundAccounts = fundAccountRepository.findAll();
        }
        fundAccounts.forEach(fundAccount -> dataSelections.add(new SelectOption(fundAccount.getId(), fundAccount.getAccount().getAccountName())));
        return dataSelections;
    }

    private List<SelectOption> getInvestorAccount(String params) {
        //params 为invest-relation id
        Iterable<InvestorAccount> investorAccounts = new ArrayList<>();
        List<SelectOption> dataSelections = new ArrayList<>();
        if (params != null && !"".equals(params)) {
            InvestRelation ir = investRelationRepository.findOne(Long.valueOf(params));
            if (ir != null) {
                investorAccounts = investorAccountRepository.findByInvestorIdOrderByIdDesc(ir.getInvestor().getId());
            }
        } else {
            investorAccounts = investorAccountRepository.findAll();
        }
        investorAccounts.forEach(investorAccount -> dataSelections.add(new SelectOption(investorAccount.getId(), investorAccount.getAccount().getAccountName())));
        return dataSelections;
    }
    // private List<SelectOption> getPlatformAccount(String params) {
    //     //params 为invest-relation id
    //     Iterable<PlatformAccount> platformAccounts = new ArrayList<>();
    //     List<SelectOption> dataSelections = new ArrayList<>();
    //     if (params != null && !"".equals(params)) {
    //         InvestRelation ir = investRelationRepository.findOne(Long.valueOf(params));
    //         if (ir != null) {
    //             platformAccounts = platformAccountRepository.findByPlatformIdOrderByIdDesc(ir.getPlatform().getId());
    //         }
    //     } else {
    //         platformAccounts = platformAccountRepository.findAll();
    //     }
    //     platformAccounts.forEach(platformAccount -> dataSelections.add(new SelectOption(platformAccount.getId(), platformAccount.getAccount().getAccountName())));
    //     return dataSelections;
    // }

    private List<SelectOption> getAgreement(String params) {
        //params
        Iterable<Agreement> agreements = new ArrayList<>();
        List<SelectOption> dataSelections = new ArrayList<>();
        if (params != null && !"".equals(params)) {
            String[] strArr = params.split("\\|");
            if (strArr.length > 1) {
                agreements = agreementRepository.findAllByFundIdAndAgreementTypeOrderByIdDesc(Long.valueOf(strArr[0]), strArr[1]);
            }
        } else {
            agreements = agreementRepository.findAll();
        }
        agreements.forEach(agreement -> dataSelections.add(new SelectOption(agreement.getId(), agreement.getName())));
        return dataSelections;
    }

    /**
     * 获取银行类型
     * @param params
     * @return
     */
    private List<SelectOption> getBankType(String params) {
        //params
        Iterable<BankType> bankTypes = new ArrayList<>();
        List<SelectOption> dataSelections = new ArrayList<>();
        bankTypes = bankTypeRepository.findAll();
        bankTypes.forEach(bankType -> dataSelections.add(new SelectOption(bankType.getId(), bankType.getBankType())));
        return dataSelections;
    }

    /**
     * 获取银行数据
     * @param params
     * @return
     */
    private List<SelectOption> getBank(String params) {
        //params
        Iterable<Bank> banks = new ArrayList<>();
        List<SelectOption> dataSelections = new ArrayList<>();
        if (params != null && !"".equals(params)) {
            banks = bankRepository.findAllByBankType(params);
        } else {
            banks = bankRepository.findAll();
        }
        banks.forEach(bank -> dataSelections.add(new SelectOption(bank.getId(), bank.getBankName())));
        return dataSelections;
    }

    /**
     * 获取银行网店(临时,后期需要切换到dataserver服务器上)
     * @param params
     * @return
     */
    private List<SelectOption> getBranch(String params) {
        //params
        Iterable<BranchBank> branchBanks = new ArrayList<>();
        List<SelectOption> dataSelections = new ArrayList<>();
        branchBanks = branchBankRepository.findAll();
        branchBanks.forEach(branchBank -> dataSelections.add(new SelectOption(branchBank.getId(), branchBank.getBranchName())));
        return dataSelections;
    }

}
