package com.mofof.runner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.github.javafaker.Faker;
import com.mofof.entity.administrator.Administrator;
import com.mofof.entity.administrator.TeamMember;
import com.mofof.entity.affiliate.Affiliate;
import com.mofof.entity.common.Individual;
import com.mofof.entity.common.License;
import com.mofof.entity.common.Organization;
import com.mofof.entity.file.DirMetadata;
import com.mofof.entity.file.Directory;
import com.mofof.entity.fund.Fund;
import com.mofof.entity.mywork.Alert;
import com.mofof.entity.mywork.CalEvent;
import com.mofof.entity.platform.Platform;
import com.mofof.entity.project.Project;
import com.mofof.entity.relation.PlatformDepartmentRelation;
import com.mofof.entity.system.Department;
import com.mofof.entity.system.Role;
import com.mofof.entity.system.RoleCategory;
import com.mofof.entity.system.User;
import com.mofof.entity.system.UserAccount;
import com.mofof.repository.AlertRepository;
import com.mofof.repository.CalEventRepository;
import com.mofof.repository.DepartmentRepository;
import com.mofof.repository.DirMetadataRepository;
import com.mofof.repository.PlatformDepartmentRelationRepository;
import com.mofof.repository.RoleCategoryRepository;
import com.mofof.repository.RoleRepository;
import com.mofof.repository.UserAccountRepository;
import com.mofof.service.AdministratorService;
import com.mofof.service.AffiliateService;
import com.mofof.service.DirectoryService;
import com.mofof.service.FileService;
import com.mofof.service.FundService;
import com.mofof.service.MenuItemService;
import com.mofof.service.PlatformService;
import com.mofof.service.ProjectService;
import com.mofof.service.TeamMemberService;
import com.mofof.util.DateUtil;

@Component
@Order(2)
public class DemoDataLoader implements ApplicationRunner {
  @Autowired
  private RoleRepository roleRepository;
  @Autowired
  private RoleCategoryRepository roleCategoryRepository;
  @Autowired
  private UserAccountRepository userAccountRepository;
  @Autowired
  private DepartmentRepository departmentRepository;
  @Autowired
  private DirectoryService directoryService;
  @Autowired
  private DirMetadataRepository dirMetadataRepository;
  @Autowired
  private CalEventRepository calEventRepository;
  @Autowired
  private AlertRepository alertRepository;
  @Autowired
  MenuItemService menuItemService;
  @Autowired
  private FundService fundService;
  @Autowired
  private AdministratorService administratorService;
  @Autowired
  private FileService fileService;
  @Autowired
  private PlatformDepartmentRelationRepository pdrr;

    @Autowired
    private PlatformService platformService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TeamMemberService teamMemberService;
    @Autowired
    private AffiliateService affiliateService;
  @Value("classpath:sample.png")
  Resource pngSample;
  @Value("classpath:sample.pdf")
  Resource pdfSample;
  @Value("classpath:sample.xlsx")
  Resource xlsxSample;
  
//  public static void main(String[] args) {
//    System.out.println(dictionary.getFilename());
//  }
  Faker cnFaker = new Faker(new Locale("zh-CN"));
  Faker enFaker = new Faker(new Locale("en-US"));
  @Override
  public void run(ApplicationArguments args) throws Exception {
    this.createUsers();
    this.createDepts();
    
    // 创建测试数据
    this.createMockData();
    this.linkDeptAndPlatform();
  }
  
  private void createUsers() throws ParseException, IOException {
    
    RoleCategory roleCategory = roleCategoryRepository
        .findByName(RoleCategory.CONVENTIONAL);
    Role role01 = roleRepository.findByName("财务负责人");
    if (role01 == null) {
      role01 = new Role();
      role01.setName("财务负责人");
      role01.setRoleCategory(roleCategory);
      roleRepository.save(role01);
    } else {
      role01.setRoleCategory(roleCategory);
      roleRepository.save(role01);
    }
    Role role02 = roleRepository.findByName("投资负责人");
    if (role02 == null) {
      role02 = new Role();
      role02.setName("投资负责人");
      role02.setRoleCategory(roleCategory);
      roleRepository.save(role02);
    } else {
      role02.setRoleCategory(roleCategory);
      roleRepository.save(role02);
    }
    
    Department dep01 = departmentRepository.findByName("财务部");    
    if (dep01 == null) {
      dep01 = new Department();
      dep01.setName("财务部");          
      dep01 = departmentRepository.save(dep01);
    }
    
    Department dep02 = departmentRepository.findByName("投资部");    
    if (dep02 == null) {
      dep02 = new Department();
      dep02.setName("投资部");          
      dep02 = departmentRepository.save(dep02);
    }
    
    Department dep03 = departmentRepository.findByName("分管财务部");
    if (dep03 == null) {
      dep03 = new Department();
      dep03.setName("分管财务部");          
      dep03 = departmentRepository.save(dep03);
    }
    
    Department dep04 = departmentRepository.findByName("分管投资部");
    if (dep04 == null) {
      dep04 = new Department();
      dep04.setName("分管投资部");          
      dep04 = departmentRepository.save(dep04);
    }
    
    UserAccount test03 = userAccountRepository.findByUsername("caiwuzs");
    if (test03 == null) {
      List<Department> departments = new ArrayList<Department>();
      departments.add(dep01);
      List<Department> managedDepartments = new ArrayList<Department>();
      managedDepartments.add(dep03);
      test03 = new UserAccount();
      test03.setUsername("caiwuzs");
      test03.hashPassword("123456");
      User u03 = new User();
      u03.setJobNumber(2);
      u03.setName("财务张三");
      u03.setRole(role01);
      u03.setPhone(enFaker.phoneNumber().cellPhone());
      u03.setEmailAddress(enFaker.internet().emailAddress());
      u03.setDepartments(departments);
      u03.setManagedDepartments(managedDepartments);
      test03.setUser(u03);
      userAccountRepository.save(test03);

      Directory directory = directoryService.create(test03);
      // 创建样本文件夹
      Directory directory3 = new Directory();
      DirMetadata dirMetadata3 = new DirMetadata();
      directory3.setName("样本");
      directory3.setParent(directory);
      dirMetadata3.setDirectory(directory3);
      dirMetadataRepository.save(dirMetadata3);

//      String fileName = "test.txt";
    
      File file = pngSample.getFile();
      String pathname = file.getAbsolutePath();
      String contentType = Files.probeContentType(new File(pathname).toPath());
//      byte[] contents = Files.readAllBytes(Paths.get(pathname));
//      File file = new File(FileUploadController.targetFolder + fileName);
      // delete if exits
//      file.delete();
      FileInputStream fileInputStream = new FileInputStream(file);
      MultipartFile mockMF = new MockMultipartFile(file.getName(), file.getName(),
          contentType, fileInputStream);
//      MockMultipartFile mockPDFFile =
//          new MockMultipartFile("user-file", fileName, "text/plain", "test data".getBytes());
      fileService.upload(directory3.getId(), mockMF);
      //pdf
      file = pdfSample.getFile();
      pathname = file.getAbsolutePath();
      contentType = Files.probeContentType(new File(pathname).toPath());
      fileInputStream = new FileInputStream(file);
      mockMF = new MockMultipartFile(file.getName(), file.getName(),
          contentType, fileInputStream);
      fileService.upload(directory3.getId(), mockMF);
      //xlsx
      file = xlsxSample.getFile();
      pathname = file.getAbsolutePath();
      contentType = Files.probeContentType(new File(pathname).toPath());
      fileInputStream = new FileInputStream(file);
      mockMF = new MockMultipartFile(file.getName(), file.getName(),
          contentType, fileInputStream);
      fileService.upload(directory3.getId(), mockMF);
      
      createCalEvent(test03);
    }
    UserAccount test04 = userAccountRepository.findByUsername("touzils");
    if (test04 == null) {
      List<Department> departments = new ArrayList<Department>();
      departments.add(dep02);
      List<Department> managedDepartments = new ArrayList<Department>();
      managedDepartments.add(dep04);
      test04 = new UserAccount();
      test04.setUsername("touzils");
      test04.hashPassword("123456");
      User u04 = new User();
      u04.setJobNumber(2);
      u04.setName("投资李四");
      u04.setPhone(enFaker.phoneNumber().cellPhone());
      u04.setEmailAddress(enFaker.internet().emailAddress());
      u04.setRole(role02);
      u04.setDepartments(departments);
      u04.setManagedDepartments(managedDepartments);
      test04.setUser(u04);
      userAccountRepository.save(test04);
      
      directoryService.create(test04);
      createCalEvent(test04);
    }      
  }
  
  private void createDepts() {
    // TODO Auto-generated method stub

  }

  private void createCalEvent(UserAccount userAccount) throws ParseException {
    
      CalEvent calEvent01 = new CalEvent();
      calEvent01.setTitle("珍惜现在");
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date startAt = sdf.parse("2019-06-17 08:00:00");
      Date endAt = sdf.parse("2019-06-17 10:00:00");
      calEvent01.setStartedAt(LocalDateTime.ofInstant(startAt.toInstant(), ZoneId.systemDefault()));
      calEvent01.setEndedAt(LocalDateTime.ofInstant(endAt.toInstant(), ZoneId.systemDefault()));
      calEvent01.setTimeRange(CalEvent.FEWDAY);
      calEvent01.setLocation(cnFaker.address().city());
      calEvent01.setTopic(CalEvent.INNER_EVT);
      calEvent01.setMemo(cnFaker.lorem().fixedString(20));
      calEvent01.setImportance(10);
      Set<User> attendees = new HashSet<User>();
      attendees.add(userAccount.getUser());
      calEvent01.setAttendees(attendees);
      calEvent01.setCreatorUser(userAccount.getUser());      
      Alert alert = new Alert();
      alert.setnMinBefore(2);
      alert.setRepeatCount(1);
      alert.setNotifyVia("web,sms");
      alert.setCalEvent(calEvent01);
      alertRepository.save(alert);
    

      CalEvent calEvent02 = new CalEvent();
      calEvent02.setTitle("回顾过去");
      
      calEvent02.setStartedAt(DateUtil.atStartOfDay("20190615"));
      calEvent02.setEndedAt(DateUtil.atEndOfDay("20190615"));
      calEvent02.setTimeRange(CalEvent.ALLDAY);
      calEvent02.setLocation(cnFaker.address().city());
      calEvent02.setTopic(CalEvent.TRIP_EVT);
      calEvent02.setMemo(cnFaker.lorem().fixedString(20));
      calEvent02.setImportance(10);
      attendees = new HashSet<User>();
      attendees.add(userAccount.getUser());
      calEvent02.setAttendees(attendees);
      calEvent02.setCreatorUser(userAccount.getUser());
      alert = new Alert();
      alert.setnMinBefore(2);
      alert.setRepeatCount(1);
      alert.setNotifyVia("web,sms");
      alert.setCalEvent(calEvent02);
      alertRepository.save(alert);
    
       
      CalEvent calEvent03 = new CalEvent();
      calEvent03.setTitle("展望未来");
      startAt = sdf.parse("2019-06-18 08:00:00");
      calEvent03.setStartedAt(LocalDateTime.ofInstant(startAt.toInstant(), ZoneId.systemDefault()));
      endAt = sdf.parse("2019-06-19 08:00:00");
      calEvent03.setEndedAt(LocalDateTime.ofInstant(endAt.toInstant(), ZoneId.systemDefault()));
      calEvent03.setTimeRange(CalEvent.DAYS);
      calEvent03.setLocation(cnFaker.address().city());
      calEvent03.setTopic(CalEvent.TRIP_EVT);
      calEvent03.setMemo(cnFaker.lorem().fixedString(20));
      calEvent03.setImportance(10);
      attendees = new HashSet<User>();
      attendees.add(userAccount.getUser());
      calEvent03.setAttendees(attendees);
      calEvent03.setCreatorUser(userAccount.getUser());
      alert = new Alert();
      alert.setnMinBefore(2);
      alert.setRepeatCount(1);
      alert.setNotifyVia("web,sms");
      alert.setCalEvent(calEvent03);
      alertRepository.save(alert);     
  }


  /**
   * 模拟测试数据,仅测试系统开放
   */
  private void createMockData() {
    Administrator admin1 = null;
    Administrator admin2 = null;
    Iterable<Administrator> administrators = administratorService.findAll();
    if (administrators != null && !administrators.iterator().hasNext()) {
      admin1 = this.mockAdministrator001();
      admin2 = this.mockAdministrator002();
    }

    Fund fund1 = null;
    Fund fund2 = null;
    Fund fund3 = null;
    Fund fund4 = null;
    Fund fund5 = null;
    Iterable<Fund> funds = fundService.findAll();
    if (funds != null && !funds.iterator().hasNext()) {
      fund1 = this.mockFund001("上海开然投资管理有限公司");
      fund2 = this.mockFund002("北京小米科技有限责任公司");
      fund3 = this.mockFund002("大连万达集团股份有限公司");
      fund4 = this.mockFund002("上海诚毅新能源创业投资有限公司");
      fund5 = this.mockFund002("北京君联睿智创业投资中心(有限合伙)");
      fund1.setAdministrator(admin1);
      fund2.setAdministrator(admin2);
      fund3.setAdministrator(admin1);
      fund4.setAdministrator(admin2);
      fund5.setAdministrator(admin2);
      fundService.save(fund1);
      fundService.save(fund2);
      fundService.save(fund3);
      fundService.save(fund4);
      fundService.save(fund5);
    }

      List<Platform> platforms = platformService.findAll();
      if (platforms != null && platforms.size() == 0) {
          this.mockPlatform("中国科学院控股有限公司");
          this.mockPlatform("上海浦东新兴产业投资有限公司");
      for (int i = 0; i < 10; i++) {
        this.mockPlatform(enFaker.company().name());
      }
      }

      List<Project> projects = projectService.projects();
      if (projects != null && projects.size() == 0) {
          this.mockProject("上海诚毅芯投资有限公司");
          this.mockProject("上海华依动力测试技术有限公司");
          this.mockProject("深圳市聚成企业管理顾问股份有限公司");
          this.mockProject("上海安硕信息技术股份有限公司");
      }

      Iterable<TeamMember> teamMembers = teamMemberService.findAll();
      if (teamMembers != null && !teamMembers.iterator().hasNext()) {
          this.mockTeamMember("宋雪枫");
          this.mockTeamMember("陈波");
          this.mockTeamMember("朱立南");
          this.mockTeamMember("王能光");
          this.mockTeamMember("李家庆");
          this.mockTeamMember("郑坚敏");
      }

      Iterable<Affiliate> affiliates = affiliateService.allAffiliate();
      if (affiliates != null && !affiliates.iterator().hasNext()) {
          this.mockAffiliate("北京博道投资顾问中心(有限合伙)");
          this.mockAffiliate("上海诚毅投资管理有限公司");
      }

  }

  private Fund mockFund001(String fullName) {
    Fund fund = new Fund();
    fund.setFundType("PE");
    fund.setCurrency("人民币");
    fund.setFundAmount("99999.99");
    fund.setFundStatus("投资期");
    fund.setInstitutionProperty("国有");
    fund.setInvestStatus("未投资");
    fund.setOrganizeForm("有限合伙制");
    fund.setRegProvince("浙江省");
    fund.setRegCity("杭州市");
    fund.setRegDistrict("滨江区");
    fund.setRegAddress("中化大厦B座201");
    fund.setOfficeAddress("浙江省杭州市滨江区中化大厦B座201");
    fund.setFoundDate(LocalDate.parse("2014-04-03"));
    fund.setUniformCode("913304030211002345A");
    fund.setRecordDate(LocalDate.parse("2014-06-05"));
    fund.setRecordCode("9133040asdfasdf");
    fund.setNote("这是一个用于测试的模拟基金");
//    fund.setAdministrator();
//    fund.setInvestRelations();

    Organization organization = new Organization();
    organization.setChineseName(fullName);
    organization.setEnglishName("mockfund001");
    organization.setFullName(fullName);
//    organization.setOrganizationType("");

    License license = new License();
    license.setLicenseName(fullName);
    license.setUniformCode("913304030211002345A");
    license.setType("测试类型");
    license.setArtificialPerson("王小二");
    license.setRegCapital("10000万");
    license.setFoundDate(LocalDate.parse("2014-04-03"));
    license.setRegAddress("浙江省杭州市滨江区中化大厦B座201");
    license.setBusinessStartDate(LocalDate.parse("2014-04-03"));
    license.setBusinessEndDate(LocalDate.parse("2034-04-03"));
    license.setBusinessScope("软件开发 系统集成 网络建设");
    license.setRegAuthority("杭州市工商管理局滨江分局");
    license.setRegStatus("续存");
    license.setApprovalDate(LocalDate.parse("2014-04-03"));
    license.setLastUpdateTime(LocalDateTime.now());
    license.setRevokeDate(null);// 吊销日期

    organization.setLicense(license);
    fund.setOrganization(organization);
    fundService.save(fund);
    System.out.println(" ........mock fund:" + fullName + " created");
    return fund;
  }

  private Fund mockFund002(String fullName) {
    Fund fund = new Fund();
    fund.setFundType("VC");
    fund.setCurrency("美元");
    fund.setFundAmount("1000000.99");
    fund.setFundStatus("投资期");
    fund.setInstitutionProperty("国有");
    fund.setInvestStatus("未投资");
    fund.setOrganizeForm("有限合伙制");
    fund.setRegProvince("浙江省");
    fund.setRegCity("杭州市");
    fund.setRegDistrict("滨江区");
    fund.setRegAddress("中化大厦B座201");
    fund.setOfficeAddress("浙江省杭州市滨江区中化大厦B座202");
    fund.setFoundDate(LocalDate.parse("2014-04-03"));
    fund.setUniformCode("913311111111115A");
    fund.setRecordDate(LocalDate.parse("2014-06-05"));
    fund.setRecordCode("913111111dfasdf");
    fund.setNote("这是一个用于测试的模拟基金");
//    fund.setAdministrator();
//    fund.setInvestRelations();

    Organization organization = new Organization();
    organization.setChineseName(fullName);
    organization.setEnglishName("mockfund002");
    organization.setFullName(fullName);
//    organization.setOrganizationType("");

    License license = new License();
    license.setLicenseName(fullName);
    license.setUniformCode("913311111111115A");
    license.setType("测试类型");
    license.setArtificialPerson("王小二");
    license.setRegCapital("10000万");
    license.setFoundDate(LocalDate.parse("2014-04-03"));
    license.setRegAddress("浙江省杭州市滨江区中化大厦B座202");
    license.setBusinessStartDate(LocalDate.parse("2014-04-03"));
    license.setBusinessEndDate(LocalDate.parse("2034-04-03"));
    license.setBusinessScope("软件开发 系统集成 网络建设");
    license.setRegAuthority("杭州市工商管理局滨江分局");
    license.setRegStatus("续存");
    license.setApprovalDate(LocalDate.parse("2014-04-03"));
    license.setLastUpdateTime(LocalDateTime.now());
    license.setRevokeDate(null);// 吊销日期

    organization.setLicense(license);
    fund.setOrganization(organization);
    fundService.save(fund);
    System.out.println(" ........mock fund:" + fullName + " created");
    return fund;
  }

  private Administrator mockAdministrator001() {

    Administrator administrator = new Administrator();
    administrator.setTypes("PE");
    administrator.setWebsite("www.administartor.com");
    administrator.setRegProvince("浙江省");
    administrator.setRegCity("杭州市");
    administrator.setRegDistrict("江干区");
    administrator.setRegAddress("蓝天大厦A座1201");
    administrator.setFoundDate(LocalDate.parse("2011-04-03"));
    administrator.setUniformCode("91330403asdfasd345A");
    administrator.setRecordDate(LocalDate.parse("2012-04-03"));
    administrator.setRecordCode("recordcode----1");
    administrator.setLogo("/static/administartor/20140525/31245463234534534.png");

    Organization organization = new Organization();
    organization.setChineseName("管理人001");
    organization.setEnglishName("Administrator001");
    organization.setFullName("模拟测试管理人001全称");
//    organization.setOrganizationType("");

    License license = new License();
    license.setLicenseName("模拟测试管理人001全称");
    license.setUniformCode("91330403asdfasd345A");
    license.setType("测试类型");
    license.setArtificialPerson("李小四");
    license.setRegCapital("3000万");
    license.setFoundDate(LocalDate.parse("2011-04-03"));
    license.setRegAddress("浙江省杭州市江干区蓝天大厦A座1201");
    license.setBusinessStartDate(LocalDate.parse("2011-04-03"));
    license.setBusinessEndDate(LocalDate.parse("2031-04-03"));
    license.setBusinessScope("投资管理 咨询服务");
    license.setRegAuthority("杭州市工商管理局江干分局");
    license.setRegStatus("续存");
    license.setApprovalDate(LocalDate.parse("2011-04-03"));
    license.setLastUpdateTime(LocalDateTime.now());
    license.setRevokeDate(null);// 吊销日期

    organization.setLicense(license);
    administrator.setOrganization(organization);
    administratorService.save(administrator);
    System.out.println("........mock administrator 1:模拟测试管理人001全称 created");
    return administrator;
  }

  private Administrator mockAdministrator002() {
    Administrator administrator = new Administrator();
    administrator.setTypes("VC");
    administrator.setWebsite("www.administartor002.com");
    administrator.setRegProvince("浙江省");
    administrator.setRegCity("杭州市");
    administrator.setRegDistrict("江干区");
    administrator.setRegAddress("蓝天大厦A座1201");
    administrator.setFoundDate(LocalDate.parse("2011-04-03"));
    administrator.setUniformCode("9133333asd231d345A");
    administrator.setRecordDate(LocalDate.parse("2012-04-03"));
    administrator.setRecordCode("recordcode----2");
    administrator.setLogo("/static/administartor/20140525/31245463234534534.png");

    Organization organization = new Organization();
    organization.setChineseName("管理人002");
    organization.setEnglishName("Administrator002");
    organization.setFullName("模拟测试管理人002全称");
//    organization.setOrganizationType("");

    License license = new License();
    license.setLicenseName("模拟测试管理人002全称");
    license.setUniformCode("9133333asd231d345A");
    license.setType("测试类型");
    license.setArtificialPerson("张小五");
    license.setRegCapital("7000万");
    license.setFoundDate(LocalDate.parse("2011-04-03"));
    license.setRegAddress("浙江省杭州市江干区蓝天大厦A座1201");
    license.setBusinessStartDate(LocalDate.parse("2011-04-03"));
    license.setBusinessEndDate(LocalDate.parse("2031-04-03"));
    license.setBusinessScope("投资管理 咨询服务");
    license.setRegAuthority("杭州市工商管理局江干分局");
    license.setRegStatus("续存");
    license.setApprovalDate(LocalDate.parse("2011-04-03"));
    license.setLastUpdateTime(LocalDateTime.now());
    license.setRevokeDate(null);// 吊销日期

    organization.setLicense(license);
    administrator.setOrganization(organization);
    administratorService.save(administrator);
    System.out.println("........mock administrator 2:模拟测试管理人002全称 created");
    return administrator;
  }

    /**
     * 投资平台
     *
     * @return
     */
    private Platform mockPlatform(String fullName) {
        Platform platform = new Platform();

        Organization organization = new Organization();
        organization.setChineseName(fullName);
        organization.setEnglishName("platform");
        organization.setFullName(fullName);
//      organization.setOrganizationType("");

        License license = new License();
        license.setLicenseName(fullName);
        license.setUniformCode("9133333asd231d345A");
        license.setType("测试类型");
        license.setArtificialPerson("王小二");
        license.setRegCapital("7000万");
        license.setFoundDate(LocalDate.parse("2011-04-03"));
    license.setRegAddress(cnFaker.address().fullAddress());
        license.setBusinessStartDate(LocalDate.parse("2011-04-03"));
        license.setBusinessEndDate(LocalDate.parse("2031-04-03"));
        license.setBusinessScope("经营范围");
        license.setRegAuthority("杭州市工商管理局江干分局");
        license.setRegStatus("续存");
        license.setApprovalDate(LocalDate.parse("2011-04-03"));
        license.setLastUpdateTime(LocalDateTime.now());
        license.setRevokeDate(null);//吊销日期

        organization.setLicense(license);

        platform.setOrganization(organization);
        platform.setPlatformType("企业");
        platform.setDescription("描述");

        System.out.println(" ........mock platform:" + fullName + " created");

        return platformService.save(platform);
    }

    /**
     * 项目
     *
     * @return
     */
    private Project mockProject(String fullName) {
        Project project = new Project();

        Organization organization = new Organization();
        organization.setChineseName(fullName);
        organization.setEnglishName("project");
        organization.setFullName(fullName);
//      organization.setOrganizationType("");

        License license = new License();
        license.setLicenseName(fullName);
        license.setUniformCode("9133333asd231d345A");
        license.setType("测试类型");
        license.setArtificialPerson("李四");
        license.setRegCapital("7000万");
        license.setFoundDate(LocalDate.parse("2011-04-03"));
        license.setRegAddress("浙江省杭州市江干区蓝天大厦A座1201");
        license.setBusinessStartDate(LocalDate.parse("2011-04-03"));
        license.setBusinessEndDate(LocalDate.parse("2031-04-03"));
        license.setBusinessScope("经营范围");
        license.setRegAuthority("杭州市工商管理局江干分局");
        license.setRegStatus("续存");
        license.setApprovalDate(LocalDate.parse("2011-04-03"));
        license.setLastUpdateTime(LocalDateTime.now());
        license.setRevokeDate(null);//吊销日期

        organization.setLicense(license);

        project.setOrganization(organization);
        project.setAliasName("别名啊");
        project.setArtificialPerson("李四");
        project.setDescription("描述");

        System.out.println(" ........mock project:" + fullName + " created");
        return projectService.save(project);
    }

    /**
     * 团队成员
     * @param fullName
     * @return
     */
    private TeamMember mockTeamMember(String fullName){
        TeamMember teamMember = new TeamMember();
        teamMember.setAge(37);
        teamMember.setBirthday(LocalDate.now());
        teamMember.setDescription("描述");
        teamMember.setGender("男");
        teamMember.setNationality("中国");

        Individual individual = new Individual();
        individual.setCertificateNum("33018219992310053");
        individual.setCertificateType("身份在");
        individual.setChineseName(fullName);
        individual.setEnglishName("en name");
        teamMember.setIndividual(individual);
        System.out.println(" ........mock teamMember:" + fullName + " created");
        return teamMemberService.save(teamMember);
    }

    /**
     * 关联机构
     * @param fullName
     * @return
     */
    private Affiliate mockAffiliate(String  fullName){
        Affiliate affiliate = new Affiliate();

        affiliate.setCurrency("美元");
        affiliate.setInstitutionProperty("国有");
        affiliate.setOrganizeForm("有限合伙制");
        affiliate.setRegProvince("浙江省");
        affiliate.setRegCity("杭州市");
        affiliate.setRegDistrict("滨江区");
        affiliate.setRegAddress("中化大厦B座201");
        affiliate.setOfficeAddress("浙江省杭州市滨江区中化大厦B座202");
        affiliate.setFoundDate(LocalDate.parse("2014-04-03"));
        affiliate.setUniformCode("913311111111115A");
        affiliate.setRecordDate(LocalDate.parse("2014-06-05"));
        affiliate.setRecordCode("913111111dfasdf");
        affiliate.setNote("这是一个用于测试的模拟关联机构");

        Organization organization = new Organization();
        organization.setChineseName(fullName);
        organization.setEnglishName("affiliate");
        organization.setFullName(fullName);
//    organization.setOrganizationType("");

        License license = new License();
        license.setLicenseName(fullName);
        license.setUniformCode("913311111111115A");
        license.setType("测试类型");
        license.setArtificialPerson("王小二");
        license.setRegCapital("10000万");
        license.setFoundDate(LocalDate.parse("2014-04-03"));
        license.setRegAddress("浙江省杭州市滨江区中化大厦B座202");
        license.setBusinessStartDate(LocalDate.parse("2014-04-03"));
        license.setBusinessEndDate(LocalDate.parse("2034-04-03"));
        license.setBusinessScope("软件开发 系统集成 网络建设");
        license.setRegAuthority("杭州市工商管理局滨江分局");
        license.setRegStatus("续存");
        license.setApprovalDate(LocalDate.parse("2014-04-03"));
        license.setLastUpdateTime(LocalDateTime.now());
        license.setRevokeDate(null);// 吊销日期

        organization.setLicense(license);
        affiliate.setOrganization(organization);
        affiliate = affiliateService.saveAffiliate(affiliate);
        System.out.println(" ........mock affiliate:" + fullName + " created");
        return affiliate;
    }

  private void linkDeptAndPlatform() {

    List<PlatformDepartmentRelation> pdrs = pdrr.findAll();
    if(pdrs.size() == 0) {
      List<Platform> platforms = platformService.findAll();
      Department department1 = departmentRepository.findByName("财务部");
      Department department2 = departmentRepository.findByName("投资部");
      PlatformDepartmentRelation pdr;
      for(int i = 0; i < 2; i++) { 
        pdr = new PlatformDepartmentRelation();
        pdr.setDepartment(department1);
        pdr.setPlatform(platforms.get(i));
        pdr.setNote("财务部的投资平台");
        pdrr.save(pdr);
      }
      for(int i = 2; i < platforms.size(); i++) { 
        pdr = new PlatformDepartmentRelation();
        pdr.setDepartment(department2);
        pdr.setPlatform(platforms.get(i));
        pdr.setNote("投资部的投资平台");
        pdrr.save(pdr);
      }
    }
  }
}