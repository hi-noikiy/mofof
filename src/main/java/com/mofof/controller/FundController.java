package com.mofof.controller;

import java.util.List;
import java.util.Map;

import com.mofof.util.PageRequestHelper;
import com.mofof.util.Result;
import com.mofof.util.ResultHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.mofof.entity.administrator.Contact;
import com.mofof.entity.fund.Fund;
import com.mofof.entity.fund.FundAdminTeam;
import com.mofof.entity.fund.TimeLine;
import com.mofof.entity.relation.InvestRelation;
import com.mofof.entity.relation.ResponsePerson;
import com.mofof.service.FundService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value = "基金接口", description = "FundController")
@RequestMapping(path = "/fund")
public class FundController {
	private static final Logger log = LoggerFactory.getLogger(FundController.class);

	@Autowired
	FundService fundService;

	@ApiOperation(value = "保存/更新基金", notes = "保存/更新基金")
	@PostMapping(path = "/save")
	Fund saveRecord(@RequestBody @ApiParam(name = "用户Fund", value = "传入json格式", required = true) Fund fund) {
		return fundService.save(fund);
	}

	@ApiOperation(value = "获取所有基金,不带筛选条件", notes = "获取所有基金,不带筛选条件")
	@GetMapping(path = "/all")
	Iterable<Fund> allFunds() {
		return fundService.findAll();
	}

	/**
	 * 查询基金集合
	 * @param pageNo
	 * @param fundType
	 * @param organizeForm
	 * @param investStatus
	 * @param fullName
	 * @param chineseName
	 * @param englishName
	 * @return
	 */
	@GetMapping(path = "/search")
	public Result<Page<Fund>> search(Integer pageNo, String fundType, String organizeForm, String investStatus, String fullName, String chineseName,String englishName) {
		Page<Fund> page = fundService.search(PageRequestHelper.build(pageNo),fundType,organizeForm,investStatus,fullName,chineseName,englishName);
		return ResultHelper.success(page);
	}

	@ApiOperation(value = "根据状态获取基金列表", notes = "根据状态获取基金列表")
	@ApiImplicitParam(name = "investStatus", value = "基金状态", required = true, dataType = "String", paramType = "path")
	@GetMapping(path = "/fund-investstatus")
	Iterable<Fund> findByInvestStatus(String investStatus) {
		return fundService.findAllByInvestStatus(investStatus);
	}

	@ApiOperation(value = "获取所有投资关系,不带筛选条件", notes = "获取所有投资关系,不带筛选条件")
	@GetMapping(path = "/allRelations")
	Iterable<InvestRelation> allRelations() {
		Iterable<InvestRelation> list = fundService.findAllRelation();
		// System.out.println(JSON.toJSONString(list));
//        return JSON.toJSONString(list);
		return list;
	}

	@ApiOperation(value = "获取基金", notes = "根据id来获取基金")
	@ApiImplicitParam(name = "id", value = "基金id", required = true, dataType = "Long", paramType = "path")
	@RequestMapping(path = "/fund/{id}", method = RequestMethod.GET)
	Fund getFund(@PathVariable Long id) {
		return fundService.findById(id);
	}

	@ApiOperation(value = "获取投资关系", notes = "根据id来获取投资关系")
	@ApiImplicitParam(name = "id", value = "投资关系id", required = true, dataType = "Long", paramType = "path")
	@GetMapping(path = "/relation")
	InvestRelation getRelation(Long id) {
		return fundService.findRelationById(id);
	}

	@ApiOperation(value = "根据基金id获取管理费", notes = "根据基金id来获取管理费")
	@ApiImplicitParam(name = "fundId", value = "基金id", required = true, dataType = "Long", paramType = "path")
	@GetMapping(path = "/timeline")
	TimeLine getTimeline(Long fundId) {
		return fundService.findTimelineByFundId(fundId);
	}


	@ApiOperation(value = "保存管理费", notes = "保存管理费")
	@PostMapping(path = "/saveTimeLine")
	TimeLine saveTimeLine(
			@RequestBody @ApiParam(name = "管理费TimeLine", value = "传入json格式", required = true) TimeLine timeline) {
		return fundService.saveTimeLine(timeline);
	}

	@ApiOperation(value = "根据基金id获取管理团队", notes = "根据基金id获取管理团队")
	@ApiImplicitParam(name = "id", value = "基金id", required = true, dataType = "Long", paramType = "path")
	@GetMapping(path = "/allAdminTeams")
	Iterable<FundAdminTeam> getFundAdminTeam(Long id) {
		return fundService.findAdminTeamByFundId(id);
	}

	@ApiOperation(value = "根据基金id获取联系人", notes = "根据基金id获取联系人")
	@ApiImplicitParam(name = "id", value = "基金id", required = true, dataType = "Long", paramType = "path")
	@GetMapping(path = "/contacts")
	Iterable<Contact> getContacts(Long id) {
		return fundService.findContactsByFundId(id);
	}

	@ApiOperation(value = "保存管理团队,可以多个", notes = "保存管理团队,可以多个")
	@PostMapping(path = "/addFundAdminTeams")
	Iterable<FundAdminTeam> addFundAdminTeams(
			@RequestBody @ApiParam(name = "管理团队数组FundAdminTeam[]", value = "传入json格式", required = true) FundAdminTeam[] fundAdminTeams) {
		return fundService.addFundAdminTeams(fundAdminTeams);
	}

	@ApiOperation(value = "修改管理团队", notes = "修改管理团队")
	@PostMapping(path = "/saveFundAdminTeams")
	Iterable<FundAdminTeam> saveFundAdminTeams(
			@RequestBody @ApiParam(name = "管理团队二维数组FundAdminTeam[][]", value = "传入json格式", required = true) FundAdminTeam[][] fundAdminTeams) {
		return fundService.modifyFundAdminTeams(fundAdminTeams);
	}

	@ApiOperation(value = "根据投资关系id获取人员分工", notes = "根据投资关系id获取人员分工")
	@ApiImplicitParam(name = "id", value = "投资关系id", required = true, dataType = "Long", paramType = "path")
	@GetMapping(path = "/response-persons")
	List<ResponsePerson> projectResponsePersons(Long id) {
		return fundService.findAllResponsePersons(id);
	}

	@ApiOperation(value = "获取人员分工", notes = "根据id获取人员分工")
	@ApiImplicitParam(name = "id", value = "人员分工id", required = true, dataType = "Long", paramType = "path")
	@GetMapping(path = "/response-person")
	ResponsePerson responsePerson(Long id) {
		return fundService.findResponsePerson(id);
	}

	@ApiOperation(value = "保存人员分工,多个", notes = "保存人员分工,多个,list")
	@PostMapping(path = "/save-response-persons")
	String saveResponsePersons(
			@RequestBody @ApiParam(name = "人员分工list:prps", value = "传入json格式", required = true) List<ResponsePerson> prps) {
		fundService.saveResponsePersons(prps);
		return " ";
	}


	@ApiOperation(value = "更新基金证照信息", notes = "更新基金证照信息")
	@ApiImplicitParam(name = "id", value = "基金id", required = true, dataType = "Long", paramType = "path")
	@PutMapping(path = "/regInfo/{id}")
	Fund updateRegInfo(@PathVariable  Long id) {
		return fundService.updateRegInfo(id);
	}

	@ApiOperation(value = "根据基金类型分组", notes = "根据基金类型分组")
	@GetMapping(path = "/fundListGroupByFundType")
	Map<String, Map<String,Object>> fundListGroupByFundType() {
		return fundService.fundListGroupByFundType();
	}
}
