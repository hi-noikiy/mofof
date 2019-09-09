package com.mofof.controller;

import com.mofof.entity.common.FinanceItem;
import com.mofof.entity.finance.*;
import com.mofof.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by ChenErliang on 2017/8/6.
 */

@RestController
@RequestMapping(path = "/finance")
public class FinanceController {

    @Autowired
    FinanceService financeService;

    @GetMapping(path = "/itemsForRelation")
    public Map<String, Object> itemsForRelation(Long fundId) {
        Iterable<FundBalanceItem> balanceItems = financeService.getBalanceItems(fundId);
        Iterable<FundCashFlowItem> cashFlowItems = financeService.getCashFlowItems(fundId);
        Iterable<FundProfitLossItem> profitLossItems = financeService.getProfitLossItems(fundId);
        Iterable<FundCapitalAccountItem> capitalAccountItems = financeService.getCapitalAccountItems(fundId);
        Map<String, Object> result = new HashMap<>();
        result.put("balanceItems", balanceItems);
        result.put("cashFlowItems", cashFlowItems);
        result.put("profitLossItems", profitLossItems);
        result.put("capitalAccountItems", capitalAccountItems);
        return result;
    }

    @GetMapping(path = "/itemsForRelationStandard")
    public Map<String, Object> itemsForRelationStandard() {
        Iterable<StandardBalanceItem> balanceItems = financeService.getBalanceItemsStandard(null);
        Iterable<StandardCashFlowItem> cashFlowItems = financeService.getCashFlowItemsStandard(null);
        Iterable<StandardProfitLossItem> profitLossItems = financeService.getProfitLossItemsStandard(null);
        Iterable<StandardCapitalAccountItem> capitalAccountItems = financeService.getCapitalAccountItemsStandard(null);
        Map<String, Object> result = new HashMap<>();
        result.put("balanceItems", balanceItems);
        result.put("cashFlowItems", cashFlowItems);
        result.put("profitLossItems", profitLossItems);
        result.put("capitalAccountItems", capitalAccountItems);
        return result;
    }

    @GetMapping(path = "/balanceItems")
    public Iterable<FundBalanceItem> getBalanceItems(Long fundId) {
        return financeService.getBalanceItems(fundId);
    }

    @GetMapping(path = "/capitalAccountItems")
    public Iterable<FundCapitalAccountItem> getCapitalAccountItems(Long fundId) {
        return financeService.getCapitalAccountItems(fundId);
    }

    @GetMapping(path = "/cashFlowItems")
    public Iterable<FundCashFlowItem> getCashFlowItems(Long fundId) {
        return financeService.getCashFlowItems(fundId);
    }

    @GetMapping(path = "/profitLossItems")
    public Iterable<FundProfitLossItem> getProfitLossItems(Long fundId) {
        return financeService.getProfitLossItems(fundId);
    }

    @GetMapping(path = "/balanceItemsStandard")
    public Iterable<StandardBalanceItem> getBalanceItemsStandard(Long fundId) {
        return financeService.getBalanceItemsStandard(fundId);
    }

    @GetMapping(path = "/capitalAccountItemsStandard")
    public Iterable<StandardCapitalAccountItem> getCapitalAccountItemsStandard(Long fundId) {
        return financeService.getCapitalAccountItemsStandard(fundId);
    }

    @GetMapping(path = "/cashFlowItemsStandard")
    public Iterable<StandardCashFlowItem> getCashFlowItemsStandard(Long fundId) {
        return financeService.getCashFlowItemsStandard(fundId);
    }

    @GetMapping(path = "/profitLossItemsStandard")
    public Iterable<StandardProfitLossItem> getProfitLossItemsStandard(Long fundId) {
        return financeService.getProfitLossItemsStandard(fundId);
    }

    @PostMapping(path = "/saveBalanceItems")
    public Iterable<FundBalanceItem> saveBalanceItems(@RequestBody FinanceItemsParams<FundBalanceItem,StandardBalanceItem> params) {
        if(params.getStandardFinanceItems()!=null){
            financeService.saveMultiRelationForBalanceItems(params.getStandardFinanceItems(),params.getFundId());
        }
        return financeService.saveAllBalanceItems(params.getFundId(), params.getFinanceItems());
    }

    @PostMapping(path = "/saveProfitLossItems")
    public Iterable<FundProfitLossItem> saveProfitLossItems(@RequestBody FinanceItemsParams<FundProfitLossItem,StandardProfitLossItem> params) {
        if(params.getStandardFinanceItems()!=null){
            financeService.saveMultiRelationForProfitLossItems(params.getStandardFinanceItems(),params.getFundId());
        }
        return financeService.saveAllProfitLossItems(params.getFundId(), params.getFinanceItems());
    }

    @PostMapping(path = "/saveCashFlowItems")
    public Iterable<FundCashFlowItem> saveCashFlowItems(@RequestBody FinanceItemsParams<FundCashFlowItem,StandardCashFlowItem> params) {
        if(params.getStandardFinanceItems()!=null){
            financeService.saveMultiRelationForCashFlowItems(params.getStandardFinanceItems(),params.getFundId());
        }
        return financeService.saveAllCashFlowItems(params.getFundId(), params.getFinanceItems());
    }

    @PostMapping(path = "/saveCapitalAccountItems")
    public Iterable<FundCapitalAccountItem> saveCapitalAccountItems(@RequestBody FinanceItemsParams<FundCapitalAccountItem,StandardCapitalAccountItem> params) {
        if(params.getStandardFinanceItems()!=null){
            financeService.saveMultiRelationForCapitalAccountItems(params.getStandardFinanceItems(),params.getFundId());
        }
        return financeService.saveAllCapitalAccountItems(params.getFundId(), params.getFinanceItems());
    }

    @PostMapping(path = "/saveBalanceItemsStandard")
    public Iterable<StandardBalanceItem> saveBalanceItemsStandard(@RequestBody FinanceItemsParams<StandardBalanceItem,StandardBalanceItem> params) {
        return financeService.saveAllBalanceItemsStandard(params.getFinanceItems());
    }

    @PostMapping(path = "/saveProfitLossItemsStandard")
    public Iterable<StandardProfitLossItem> saveProfitLossItemsStandard(@RequestBody FinanceItemsParams<StandardProfitLossItem,StandardProfitLossItem> params) {
        return financeService.saveAllProfitLossItemsStandard(params.getFinanceItems());
    }

    @PostMapping(path = "/saveCashFlowItemsStandard")
    public Iterable<StandardCashFlowItem> saveCashFlowItemsStandard(@RequestBody FinanceItemsParams<StandardCashFlowItem,StandardCashFlowItem> params) {
        return financeService.saveAllCashFlowItemsStandard(params.getFinanceItems());
    }

    @PostMapping(path = "/saveCapitalAccountItemsStandard")
    public Iterable<StandardCapitalAccountItem> saveCapitalAccountItemsStandard(@RequestBody FinanceItemsParams<StandardCapitalAccountItem,StandardCapitalAccountItem> params) {
        return financeService.saveAllCapitalAccountItemsStandard(params.getFinanceItems());
    }

    @GetMapping(path = "/financeOriginForCheck")
    public Map<String, Object> financeOriginForCheck(Long fundId, String year, String financeType, String cycle) {
        Map<String, Object> result = new HashMap<>();
        if (year != null && financeType != null && cycle != null) {
            FundBalanceOrigin fundBalanceOrigin = financeService.getBalanceOrigin(fundId, year, financeType, cycle);
            if (fundBalanceOrigin == null) {
                fundBalanceOrigin = financeService.getBlankBalanceOrigin(fundId, year, financeType, cycle);
            }
            result.put("BS", buildCheckMap(fundBalanceOrigin));
            FundCashFlowOrigin fundCashFlowOrigin = financeService.getCashFlowOrigin(fundId, year, financeType, cycle);
            if (fundCashFlowOrigin == null) {
                fundCashFlowOrigin = financeService.getBlankCashFlowOrigin(fundId, year, financeType, cycle);
            }
            result.put("CF", buildCheckMap(fundCashFlowOrigin));

            FundProfitLossOrigin fundProfitLossOrigin = financeService.getProfitLossOrigin(fundId, year, financeType, cycle);
            if (fundProfitLossOrigin == null) {
                fundProfitLossOrigin = financeService.getBlankProfitLossOrigin(fundId, year, financeType, cycle);
            }
            result.put("PL", buildCheckMap(fundProfitLossOrigin));
        }
        return result;
    }

    @GetMapping(path = "/financeStandardForCheck")
    public Map<String, Object> financeStandardForCheck(Long fundId, String year, String financeType, String cycle) {
        Map<String, Object> result = new HashMap<>();
        if (year != null && financeType != null && cycle != null) {
            FundBalanceStandard fundBalanceStandard = financeService.getBalanceStandard(fundId, year, financeType, cycle);
            if (fundBalanceStandard == null) {
                fundBalanceStandard = financeService.getBlankBalanceStandard(fundId, year, financeType, cycle);
            }
            result.put("BS", buildCheckMap(fundBalanceStandard));
            FundCashFlowStandard fundCashFlowStandard = financeService.getCashFlowStandard(fundId, year, financeType, cycle);
            if (fundCashFlowStandard == null) {
                fundCashFlowStandard = financeService.getBlankCashFlowStandard(fundId, year, financeType, cycle);
            }
            result.put("CF", buildCheckMap(fundCashFlowStandard));
            FundProfitLossStandard fundProfitLossStandard = financeService.getProfitLossStandard(fundId, year, financeType, cycle);
            if(fundProfitLossStandard==null){
                fundProfitLossStandard = financeService.getBlankProfitLossStandard(fundId, year, financeType, cycle);
            }
            result.put("PL", buildCheckMap(fundProfitLossStandard));
        }
        return result;
    }

    private <T extends BaseFinance<D>,D extends BaseFinanceDetail> Map<String,BigDecimal> buildCheckMap(T finance){
        Map<String, BigDecimal> map = new HashMap<>();
        List<D> details = finance.getDetails().stream().filter(detail -> !detail.getItem().isDeleted()).collect(Collectors.toList());
        for (int i = 1; i <= details.size(); i++) { //从l1开始第一行
            D detail = details.get(i-1);
            map.put("l"+i, detail.getAmount());
        }
        return map;
    }

    @GetMapping(path = "/balanceOrigin")
    public FundBalanceOrigin getBalanceOrigin(Long fundId, String year, String financeType, String cycle) {
        FundBalanceOrigin fundBalanceOrigin = null;
        if (year != null && financeType != null && cycle != null) {
            fundBalanceOrigin = financeService.getBalanceOrigin(fundId, year, financeType, cycle);
        }
        if (fundBalanceOrigin == null) {
            fundBalanceOrigin = financeService.getBlankBalanceOrigin(fundId, year, financeType, cycle);
        }
        return fundBalanceOrigin;
    }
    @GetMapping(path = "/balanceStandard")
    public FundBalanceStandard getBalanceStandard(Long fundId, String year, String financeType, String cycle) {
        FundBalanceStandard fundBalanceStandard = null;
        if (year != null && financeType != null && cycle != null) {
            fundBalanceStandard= financeService.getBalanceStandard(fundId, year, financeType, cycle);
        }
        if (fundBalanceStandard == null) {
            fundBalanceStandard = financeService.getBlankBalanceStandard(fundId, year, financeType, cycle);
        }
        return fundBalanceStandard;
    }

    @GetMapping(path = "/cashFlowOrigin")
    public FundCashFlowOrigin getCashFlowOrigin(Long fundId, String year, String financeType, String cycle) {
        FundCashFlowOrigin fundCashFlowOrigin = null;
        if (year != null && financeType != null && cycle != null) {
            fundCashFlowOrigin = financeService.getCashFlowOrigin(fundId, year, financeType, cycle);
        }
        if (fundCashFlowOrigin == null) {
            fundCashFlowOrigin = financeService.getBlankCashFlowOrigin(fundId, year, financeType, cycle);
        }
        return fundCashFlowOrigin;
    }

    @GetMapping(path = "/cashFlowStandard")
    public FundCashFlowStandard getCashFlowStandard(Long fundId, String year, String financeType, String cycle) {
        FundCashFlowStandard fundCashFlowStandard = null;
        if (year != null && financeType != null && cycle != null) {
            fundCashFlowStandard= financeService.getCashFlowStandard(fundId, year, financeType, cycle);
        }
        if (fundCashFlowStandard == null) {
            fundCashFlowStandard = financeService.getBlankCashFlowStandard(fundId, year, financeType, cycle);
        }
        return fundCashFlowStandard;
    }

    @GetMapping(path = "/profitLossOrigin")
    public FundProfitLossOrigin getProfitLossOrigin(Long fundId, String year, String financeType, String cycle) {
        FundProfitLossOrigin fundProfitLossOrigin = null;
        if (year != null && financeType != null && cycle != null) {
            fundProfitLossOrigin = financeService.getProfitLossOrigin(fundId, year, financeType, cycle);
        }
        if (fundProfitLossOrigin == null) {
            fundProfitLossOrigin = financeService.getBlankProfitLossOrigin(fundId, year, financeType, cycle);
        }
        return fundProfitLossOrigin;
    }

    @GetMapping(path = "/profitLossStandard")
    public FundProfitLossStandard getProfitLossStandard(Long fundId, String year, String financeType, String cycle) {
        FundProfitLossStandard fundProfitLossStandard = null;
        if (year != null && financeType != null && cycle != null) {
            fundProfitLossStandard= financeService.getProfitLossStandard(fundId, year, financeType, cycle);
        }
        if (fundProfitLossStandard == null) {
            fundProfitLossStandard = financeService.getBlankProfitLossStandard(fundId, year, financeType, cycle);
        }
        return fundProfitLossStandard;
    }

    @GetMapping(path = "/capitalAccountOrigin")
    public FundCapitalAccountOrigin getCapitalAccountOrigin(Long fundId, String year, String financeType, String cycle,Long relationId) {
        FundCapitalAccountOrigin fundCapitalAccountOrigin = null;
        if (year != null && financeType != null && cycle != null) {
            fundCapitalAccountOrigin = financeService.getCapitalAccountOrigin(fundId, year, financeType, cycle);
        }
        if (fundCapitalAccountOrigin == null) {
            fundCapitalAccountOrigin = financeService.getBlankCapitalAccountOrigin(fundId, year, financeType, cycle);
        }
        financeService.setCapitalAccountFirstLine(fundCapitalAccountOrigin,FundCapitalAccountOriginDetail.class,FundCapitalAccountItem.class);
        financeService.setCapitalAccountPlatformDetailsOrigin(relationId,fundCapitalAccountOrigin);
        return fundCapitalAccountOrigin;
    }

    @GetMapping(path = "/capitalAccountStandard")
    public FundCapitalAccountStandard getCapitalAccountStandard(Long fundId, String year, String financeType, String cycle,Long relationId) {
        FundCapitalAccountStandard fundCapitalAccountStandard = null;
        if (year != null && financeType != null && cycle != null) {
            fundCapitalAccountStandard = financeService.getCapitalAccountStandard(fundId, year, financeType, cycle);
        }
        if (fundCapitalAccountStandard == null) {
            fundCapitalAccountStandard = financeService.getBlankCapitalAccountStandard(fundId, year, financeType, cycle);
        }
        financeService.setCapitalAccountFirstLine(fundCapitalAccountStandard,FundCapitalAccountStandardDetail.class,StandardCapitalAccountItem.class);
        financeService.setCapitalAccountPlatformDetailsStandard(relationId,fundCapitalAccountStandard);
        return fundCapitalAccountStandard;
    }

    @PostMapping(path = "/saveBalance")
    public FinanceParams<FundBalanceOrigin,FundBalanceStandard> saveBalance(@RequestBody FinanceParams<FundBalanceOrigin,FundBalanceStandard> params) {
        if(params.getFinance()!=null){
            financeService.saveBalanceOrigin(params.getFinance());
            params.setFinance(financeService.getBlankBalanceOrigin(params.getFinance().getFund().getId(), "", "", ""));
        }
        if(params.getFinanceStandard()!=null){
            financeService.saveBalanceStandard(params.getFinanceStandard());
            params.setFinanceStandard(financeService.getBlankBalanceStandard(params.getFinance().getFund().getId(), "", "", ""));
        }
        return params;
    }

    @GetMapping(path = "/getBalanceOriginsForView")
    public Map<String, Object> getBalanceOriginsForView(Long fundId, String startDate, String endDate) {
        LocalDate startDateObject = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate endDateObject = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);
        return financeService.getBalanceOriginsForView(fundId, startDateObject, endDateObject);
    }
    @GetMapping(path = "/getBalanceStandardForView")
    public Map<String, Object> getBalanceStandardForView(Long fundId, String startDate, String endDate) {
        LocalDate startDateObject = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate endDateObject = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);
        return financeService.getBalanceStandardForView(fundId, startDateObject, endDateObject);
    }

    @PostMapping(path = "/saveProfitLoss")
    public FinanceParams<FundProfitLossOrigin,FundProfitLossStandard> saveProfitLoss(@RequestBody FinanceParams<FundProfitLossOrigin,FundProfitLossStandard> params) {
        if(params.getFinance()!=null){
            financeService.saveProfitLossOrigin(params.getFinance());
            params.setFinance(financeService.getBlankProfitLossOrigin(params.getFinance().getFund().getId(), "", "", ""));
        }
        if(params.getFinanceStandard()!=null){
            financeService.saveProfitLossStandard(params.getFinanceStandard());
            params.setFinanceStandard(financeService.getBlankProfitLossStandard(params.getFinanceStandard().getFund().getId(),"","",""));
        }
        return params;
    }

    @GetMapping(path = "/getProfitLossOriginsForView")
    public Map<String, Object> getProfitLossOriginsForView(Long fundId, String startDate, String endDate) {
        LocalDate startDateObject = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate endDateObject = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);
        return financeService.getProfitLossOriginsForView(fundId, startDateObject, endDateObject);
    }
    @GetMapping(path = "/getProfitLossStandardForView")
    public Map<String, Object> getProfitLossStandardForView(Long fundId, String startDate, String endDate) {
        LocalDate startDateObject = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate endDateObject = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);
        return financeService.getProfitLossStandardForView(fundId, startDateObject, endDateObject);
    }


    @PostMapping(path = "/saveCapitalAccount")
    public FinanceParams<FundCapitalAccountOrigin,FundCapitalAccountStandard> saveCapitalAccount(@RequestBody FinanceParams<FundCapitalAccountOrigin,FundCapitalAccountStandard> params) {
        Long relationId = params.getRelationId();
        if(params.getFinance()!=null){
            financeService.saveCapitalAccountOrigin(relationId,params.getFinance());
            FundCapitalAccountOrigin fundCapitalAccountOrigin = financeService.getBlankCapitalAccountOrigin(params.getFinance().getFund().getId(), "", "", "");
            financeService.setCapitalAccountFirstLine(fundCapitalAccountOrigin,FundCapitalAccountOriginDetail.class,FundCapitalAccountItem.class);
            financeService.setCapitalAccountPlatformDetailsOrigin(relationId,fundCapitalAccountOrigin);
            params.setFinance(fundCapitalAccountOrigin);
        }
        if(params.getFinanceStandard()!=null){
            financeService.saveCapitalAccountStandard(params.getRelationId(),params.getFinanceStandard());
            FundCapitalAccountStandard fundCapitalAccountStandard = financeService.getBlankCapitalAccountStandard(params.getFinance().getFund().getId(), "", "", "");
            financeService.setCapitalAccountFirstLine(fundCapitalAccountStandard,FundCapitalAccountStandardDetail.class,StandardCapitalAccountItem.class);
            financeService.setCapitalAccountPlatformDetailsStandard(relationId,fundCapitalAccountStandard);
            params.setFinanceStandard(fundCapitalAccountStandard);
        }
        return params;
    }

    @GetMapping(path = "/getCapitalAccountOriginsForView")
    public Map<String, Object> getCapitalAccountOriginsForView(Long relationId,Long fundId, String startDate, String endDate) {
        LocalDate startDateObject = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate endDateObject = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);
        return financeService.getCapitalAccountOriginsForView(relationId,fundId, startDateObject, endDateObject);
    }
    @GetMapping(path = "/getCapitalAccountStandardForView")
    public Map<String, Object> getCapitalAccountStandardForView(Long relationId,Long fundId, String startDate, String endDate) {
        LocalDate startDateObject = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate endDateObject = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);
        return financeService.getCapitalAccountStandardForView(relationId,fundId, startDateObject, endDateObject);
    }

    @GetMapping(path = "/getCashFlowOriginsForView")
    public Map<String, Object> getCashFlowOriginsForView(Long fundId, String startDate, String endDate) {
        LocalDate startDateObject = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate endDateObject = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);
        return financeService.getCashFlowOriginsForView(fundId, startDateObject, endDateObject);
    }
    @GetMapping(path = "/getCashFlowStandardForView")
    public Map<String, Object> getCashFlowStandardForView(Long fundId, String startDate, String endDate) {
        LocalDate startDateObject = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate endDateObject = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);
        return financeService.getCashFlowStandardForView(fundId, startDateObject, endDateObject);
    }

    @PostMapping(path = "/saveCashFlow")
    public FinanceParams<FundCashFlowOrigin,FundCashFlowStandard> saveCashFlow(@RequestBody FinanceParams<FundCashFlowOrigin,FundCashFlowStandard> params) {
        if(params.getFinance()!=null){
            financeService.saveCashFlowOrigin(params.getFinance());
            params.setFinance(financeService.getBlankCashFlowOrigin(params.getFinance().getFund().getId(), "", "", ""));
        }
        if(params.getFinanceStandard()!=null){
            financeService.saveCashFlowStandard(params.getFinanceStandard());
            params.setFinanceStandard(financeService.getBlankCashFlowStandard(params.getFinance().getFund().getId(), "", "", ""));
        }
        return params;
    }

    @PostMapping(path = "/importFinanceItem")
    public Iterable importFinanceItem(Long fundId,String type,Long sourceFundId,String sourceType,List<String> sourceRelations){
        return financeService.importFinanceItem(fundId,type,sourceFundId,sourceType,sourceRelations);
    }

    public static class FinanceItemsParams<T extends BaseFinanceItem,R extends BaseFinanceItem> {
        private Long fundId;
        private List<T> financeItems;
        private List<R> standardFinanceItems; //存原始科目时，同时保存标准科目的跨表勾稽关系用

        public Long getFundId() {
            return fundId;
        }

        public void setFundId(Long fundId) {
            this.fundId = fundId;
        }

        public List<T> getFinanceItems() {
            return financeItems;
        }

        public void setFinanceItems(List<T> financeItems) {
            this.financeItems = financeItems;
        }

        public List<R> getStandardFinanceItems() {
            return standardFinanceItems;
        }

        public void setStandardFinanceItems(List<R> standardFinanceItems) {
            this.standardFinanceItems = standardFinanceItems;
        }
    }

    public static class FinanceParams<T extends BaseFinance,R extends BaseFinance>{
        private T finance;
        private R financeStandard;
        private Long relationId;

        public T getFinance() {
            return finance;
        }

        public void setFinance(T finance) {
            this.finance = finance;
        }

        public R getFinanceStandard() {
            return financeStandard;
        }

        public void setFinanceStandard(R financeStandard) {
            this.financeStandard = financeStandard;
        }

        public Long getRelationId() {
            return relationId;
        }

        public void setRelationId(Long relationId) {
            this.relationId = relationId;
        }
    }
}
