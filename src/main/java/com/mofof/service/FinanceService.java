package com.mofof.service;

import com.alibaba.fastjson.JSON;
import com.mofof.entity.common.BaseEntity;
import com.mofof.entity.common.FinanceItem;
import com.mofof.entity.finance.*;
import com.mofof.entity.fund.Fund;
import com.mofof.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by ChenErliang on 2017/8/6.
 */
@Service
@Transactional
public class FinanceService {

    @Autowired
    private FundBalanceItemRepository fundBalanceItemRepository;
    @Autowired
    private StandardBalanceItemRepository standardBalanceItemRepository;
    @Autowired
    private FundProfitLossItemRepository fundProfitLossItemRepository;
    @Autowired
    private StandardProfitLossItemRepository standardProfitLossItemRepository;
    @Autowired
    private FundCapitalAccountItemRepository fundCapitalAccountItemRepository;
    @Autowired
    private StandardCapitalAccountItemRepository standardCapitalAccountItemRepository;
    @Autowired
    private FundCashFlowItemRepository fundCashFlowItemRepository;
    @Autowired
    private StandardCashFlowItemRepository standardCashFlowItemRepository;

    @Autowired
    private FundBalanceOriginRepository fundBalanceOriginRepository;
    @Autowired
    private FundProfitLossOriginRepository fundProfitLossOriginRepository;
    @Autowired
    private FundCashFlowOriginRepository fundCashFlowOriginRepository;
    @Autowired
    private FundCapitalAccountOriginRepository fundCapitalAccountOriginRepository;

    @Autowired
    private FundBalanceStandardRepository fundBalanceStandardRepository;
    @Autowired
    private FundProfitLossStandardRepository fundProfitLossStandardRepository;
    @Autowired
    private FundCashFlowStandardRepository fundCashFlowStandardRepository;
    @Autowired
    private FundCapitalAccountStandardRepository fundCapitalAccountStandardRepository;

    @Autowired
    private StandardBalanceItemRelationRepository standardBalanceItemRelationRepository;
    @Autowired
    private StandardProfitLossItemRelationRepository standardProfitLossItemRelationRepository;
    @Autowired
    private StandardCashFlowItemRelationRepository standardCashFlowItemRelationRepository;
    @Autowired
    private StandardCapitalAccountItemRelationRepository standardCapitalAccountItemRelationRepository;
    @Autowired
    private InvestRelationRepository investRelationRepository;


    @Autowired
    private FundCapitalAccountPlatformOriginRepository fundCapitalAccountPlatformOriginRepository;
    @Autowired
    private FundCapitalAccountPlatformStandardRepository fundCapitalAccountPlatformStandardRepository;

    private <T extends BaseFinanceItem> Iterable<T> getFinanceItems(FundFinanceItemRepository repository, Long fundId) {
        return repository.findByFundIdAndDeletedOrderByOrderNum(fundId, false);
    }

    private <T extends BaseFinanceItem> Iterable<T> getFinanceItemsStandard(StandardFinanceItemRepository repository) {
        return repository.findByDeletedOrderByOrderNum(false);
    }

    /**
     * 标准表的跨表勾稽关系
     * @param repository
     * @param fundId
     * @param <T>
     * @return
     */
    private <T extends BaseStandardItemRelation,R extends BaseFinanceItem> void setMultiRelationForStandardItems(StandardItemRelationRepository repository,Long fundId,Iterable<R> financeItems){
        Iterable<T> relationEntitys = repository.findByFundIdAndItemDeleted(fundId,false);
        Map<Long,String> relationMap = new HashMap<>();
        relationEntitys.forEach(relationEntity->{
            relationMap.put(relationEntity.getItem().getId(),relationEntity.getRelation());
        });
        financeItems.forEach(financeItem->{
            String relation = relationMap.get(financeItem.getId());
            if(relation!=null){
                financeItem.getFinanceItem().setMultiRelation(relation);
            }
        });
    }

    public Iterable<FundBalanceItem> getBalanceItems(Long fundId) {
        return getFinanceItems(fundBalanceItemRepository, fundId);
    }

    public Iterable<StandardBalanceItem> getBalanceItemsStandard(Long fundId) {
        Iterable<StandardBalanceItem> standardBalanceItems =  getFinanceItemsStandard(standardBalanceItemRepository);
        if(fundId!=null){
            setMultiRelationForStandardItems(standardBalanceItemRelationRepository,fundId,standardBalanceItems);
        }
        return standardBalanceItems;
    }

    public Iterable<FundProfitLossItem> getProfitLossItems(Long fundId) {
        return getFinanceItems(fundProfitLossItemRepository, fundId);
    }

    public Iterable<StandardProfitLossItem> getProfitLossItemsStandard(Long fundId) {
        Iterable<StandardProfitLossItem> standardProfitLossItems = getFinanceItemsStandard(standardProfitLossItemRepository);
        if(fundId!=null){
            setMultiRelationForStandardItems(standardProfitLossItemRelationRepository,fundId,standardProfitLossItems);
        }
        return standardProfitLossItems;
    }

    public Iterable<FundCashFlowItem> getCashFlowItems(Long fundId) {
        return getFinanceItems(fundCashFlowItemRepository, fundId);
    }

    public Iterable<StandardCashFlowItem> getCashFlowItemsStandard(Long fundId) {
        Iterable<StandardCashFlowItem> standardCashFlowItems = getFinanceItemsStandard(standardCashFlowItemRepository);
        if(fundId!=null){
            setMultiRelationForStandardItems(standardCashFlowItemRelationRepository,fundId,standardCashFlowItems);
        }
        return standardCashFlowItems;
    }

    public Iterable<FundCapitalAccountItem> getCapitalAccountItems(Long fundId) {
        return getFinanceItems(fundCapitalAccountItemRepository, fundId);
    }

    public Iterable<StandardCapitalAccountItem> getCapitalAccountItemsStandard(Long fundId) {
        Iterable<StandardCapitalAccountItem> standardCapitalAccountItems =  getFinanceItemsStandard(standardCapitalAccountItemRepository);
        if(fundId!=null){
            setMultiRelationForStandardItems(standardCapitalAccountItemRelationRepository,fundId,standardCapitalAccountItems);
        }
        return standardCapitalAccountItems;
    }

    private <T extends BaseFinanceItem> Iterable<T> saveAllFinanceItems(FundFinanceItemRepository fundFinanceItemRepository, Long fundId, List<T> financeItems) {
        financeItems = financeItems.stream().filter(financeBalanceItem -> financeBalanceItem.getFinanceItem().getName() != null && !financeBalanceItem.getFinanceItem().getName().equals("")).collect(toList());
        for (int i = 0; i < financeItems.size(); i++) {
            financeItems.get(i).setOrderNum(i + 1);
        }
        List<Long> ids = financeItems.stream().filter(financeItem -> financeItem.getId() != null).mapToLong(BaseEntity::getId).boxed().collect(toList());
        if (ids.size() > 0) {
            fundFinanceItemRepository.deleteOther(fundId, ids);
        } else {
            fundFinanceItemRepository.deleteAll(fundId);
        }
        if (financeItems.size() > 0) {
            return fundFinanceItemRepository.save(financeItems);
        } else {
            return new ArrayList<>();
        }
    }

    public Iterable<FundBalanceItem> saveAllBalanceItems(Long fundId, List<FundBalanceItem> balanceItems) {
        return saveAllFinanceItems(fundBalanceItemRepository, fundId, balanceItems);
    }

    public Iterable<FundProfitLossItem> saveAllProfitLossItems(Long fundId, List<FundProfitLossItem> profitLossItems) {
        return saveAllFinanceItems(fundProfitLossItemRepository, fundId, profitLossItems);
    }

    public Iterable<FundCashFlowItem> saveAllCashFlowItems(Long fundId, List<FundCashFlowItem> CashFlowItems) {
        return saveAllFinanceItems(fundCashFlowItemRepository, fundId, CashFlowItems);
    }

    public Iterable<FundCapitalAccountItem> saveAllCapitalAccountItems(Long fundId, List<FundCapitalAccountItem> capitalAccountItems) {
        return saveAllFinanceItems(fundCapitalAccountItemRepository, fundId, capitalAccountItems);
    }

    private <T extends BaseFinanceItem> Iterable<T> saveAllFinanceItemsStandard(StandardFinanceItemRepository standardFinanceItemRepository, List<T> financeItems) {
        financeItems = financeItems.stream().filter(standardFinanceItem -> standardFinanceItem.getFinanceItem().getName() != null && !standardFinanceItem.getFinanceItem().getName().equals("")).collect(toList());
        for (int i = 0; i < financeItems.size(); i++) {
            financeItems.get(i).setOrderNum(i + 1);
        }
        List<Long> ids = financeItems.stream().filter(balanceItem -> balanceItem.getId() != null).mapToLong(BaseEntity::getId).boxed().collect(toList());
        if (ids.size() > 0) {
            standardFinanceItemRepository.deleteOther(ids);
        } else {
            standardFinanceItemRepository.deleteAll();
        }
        if (financeItems.size() > 0) {
            return standardFinanceItemRepository.save(financeItems);
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * 保存标准报表科目的勾稽关系
     * @param repository
     * @param standardFinanceItems
     * @param fundId
     * @param <T>
     * @param <R>
     */
    private <T extends BaseFinanceItem,R extends BaseStandardItemRelation> void saveMultiRelationForStandardItems(StandardItemRelationRepository repository,List<T> standardFinanceItems,Long fundId,Class<R> rClazz){
        Iterable<R> standardItemRelations = repository.findByFundIdAndItemDeleted(fundId,false);
        Map<Long,BaseStandardItemRelation> itemRelationMap = new HashMap<>();
        standardItemRelations.forEach(standardItemRelation->{
            itemRelationMap.put(standardItemRelation.getItem().getId(),standardItemRelation);
        });
        List<Long> ids = new ArrayList<>();
        standardFinanceItems.forEach(financeItem->{
            String relation = financeItem.getFinanceItem().getMultiRelation();
            if(relation!=null&&!relation.equals("")){
                Long itemId = financeItem.getId();
                ids.add(itemId);
                BaseStandardItemRelation itemRelation = itemRelationMap.get(itemId);
                if(itemRelation!=null){
                    itemRelation.setRelation(financeItem.getFinanceItem().getMultiRelation());
                    repository.save(itemRelation);
                }else{
                    try {
                        itemRelation = rClazz.newInstance();
                        Fund fund = new Fund();
                        fund.setId(fundId);
                        itemRelation.setFund(fund);
                        itemRelation.setItem(financeItem);
                        itemRelation.setRelation(financeItem.getFinanceItem().getMultiRelation());
                        repository.save(itemRelation);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        if(ids.size()>0){
            repository.deleteOther(fundId,ids);
        }else{
            repository.deleteAll(fundId);
        }

    }

    public void saveMultiRelationForBalanceItems(List<StandardBalanceItem> standardBalanceItems,Long fundId){
        saveMultiRelationForStandardItems(standardBalanceItemRelationRepository,standardBalanceItems,fundId,StandardBalanceItemRelation.class);
    }
    public void saveMultiRelationForProfitLossItems(List<StandardProfitLossItem> standardProfitLossItems,Long fundId){
        saveMultiRelationForStandardItems(standardProfitLossItemRelationRepository,standardProfitLossItems,fundId,StandardProfitLossItemRelation.class);
    }
    public void saveMultiRelationForCashFlowItems(List<StandardCashFlowItem> standardCashFlowItems,Long fundId){
        saveMultiRelationForStandardItems(standardCashFlowItemRelationRepository,standardCashFlowItems,fundId,StandardCashFlowItemRelation.class);
    }
    public void saveMultiRelationForCapitalAccountItems(List<StandardCapitalAccountItem> standardCapitalAccountItems,Long fundId){
        saveMultiRelationForStandardItems(standardCapitalAccountItemRelationRepository,standardCapitalAccountItems,fundId,StandardCapitalAccountItemRelation.class);
    }
    public Iterable<StandardBalanceItem> saveAllBalanceItemsStandard(List<StandardBalanceItem> balanceItems) {
        return saveAllFinanceItemsStandard(standardBalanceItemRepository, balanceItems);
    }

    public Iterable<StandardProfitLossItem> saveAllProfitLossItemsStandard(List<StandardProfitLossItem> ProfitLossItems) {
        return saveAllFinanceItemsStandard(standardProfitLossItemRepository, ProfitLossItems);
    }

    public Iterable<StandardCashFlowItem> saveAllCashFlowItemsStandard(List<StandardCashFlowItem> cashFlowItems) {
        return saveAllFinanceItemsStandard(standardCashFlowItemRepository, cashFlowItems);
    }

    public Iterable<StandardCapitalAccountItem> saveAllCapitalAccountItemsStandard(List<StandardCapitalAccountItem> capitalAccountItems) {
        return saveAllFinanceItemsStandard(standardCapitalAccountItemRepository, capitalAccountItems);
    }

    private <T extends BaseFinance,D extends BaseFinanceDetail,I extends BaseFinanceItem> T getBlankFinance(Iterable<I> balanceItems,Class<T> clazz,Class<D> detailClazz,Long fundId,String year, String financeType, String cycle){
        try{
            T finance = clazz.newInstance();
            Fund fund = new Fund();
            fund.setId(fundId);
            finance.setFund(fund);
            List<D> details = new ArrayList<>();
            balanceItems.forEach(balanceItem -> {
                try{
                    D detail = detailClazz.newInstance();
                    detail.setItem(balanceItem);
                    details.add(detail);
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
            finance.setDetails(details);
            finance.setYear(year);
            finance.setFinanceType(financeType);
            finance.setCycle(cycle);
            return finance;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public FundBalanceOrigin getBlankBalanceOrigin(Long fundId, String year, String financeType, String cycle) {
        Iterable<FundBalanceItem> financeItems = fundBalanceItemRepository.findByFundIdAndDeletedOrderByOrderNum(fundId, false);
        return getBlankFinance(financeItems,FundBalanceOrigin.class,FundBalanceOriginDetail.class,fundId,  year,  financeType,  cycle);
    }

    public FundProfitLossOrigin getBlankProfitLossOrigin(Long fundId, String year, String financeType, String cycle) {
        Iterable<FundProfitLossItem> financeItems = fundProfitLossItemRepository.findByFundIdAndDeletedOrderByOrderNum(fundId, false);
        return getBlankFinance(financeItems,FundProfitLossOrigin.class, FundProfitLossOriginDetail.class,fundId,  year,  financeType,  cycle);
    }

    public FundCashFlowOrigin getBlankCashFlowOrigin(Long fundId, String year, String financeType, String cycle) {
        Iterable<FundCashFlowItem> financeItems = fundCashFlowItemRepository.findByFundIdAndDeletedOrderByOrderNum(fundId, false);
        return getBlankFinance(financeItems,FundCashFlowOrigin.class, FundCashFlowOriginDetail.class,fundId,  year,  financeType,  cycle);
    }

    public FundCapitalAccountOrigin getBlankCapitalAccountOrigin(Long fundId, String year, String financeType, String cycle) {
        Iterable<FundCapitalAccountItem> financeItems = fundCapitalAccountItemRepository.findByFundIdAndDeletedOrderByOrderNum(fundId, false);
        return getBlankFinance(financeItems,FundCapitalAccountOrigin.class, FundCapitalAccountOriginDetail.class,fundId,  year,  financeType,  cycle);
    }

    public FundBalanceStandard getBlankBalanceStandard(Long fundId, String year, String financeType, String cycle) {
        Iterable<StandardBalanceItem> financeItems = standardBalanceItemRepository.findByDeletedOrderByOrderNum(false);
        FundBalanceStandard fundBalanceStandard = getBlankFinance(financeItems,FundBalanceStandard.class,FundBalanceStandardDetail.class,fundId,  year,  financeType,  cycle);
        setMultiRelationForStandardDetails(standardBalanceItemRelationRepository,fundId,fundBalanceStandard.getDetails());
        return fundBalanceStandard;
    }

    public FundProfitLossStandard getBlankProfitLossStandard(Long fundId, String year, String financeType, String cycle) {
        Iterable<StandardProfitLossItem> financeItems = standardProfitLossItemRepository.findByDeletedOrderByOrderNum(false);
        FundProfitLossStandard fundProfitLossStandard = getBlankFinance(financeItems,FundProfitLossStandard.class, FundProfitLossStandardDetail.class,fundId,  year,  financeType,  cycle);
        setMultiRelationForStandardDetails(standardProfitLossItemRelationRepository,fundId,fundProfitLossStandard.getDetails());
        return fundProfitLossStandard;
    }

    public FundCashFlowStandard getBlankCashFlowStandard(Long fundId, String year, String financeType, String cycle) {
        Iterable<StandardCashFlowItem> financeItems = standardCashFlowItemRepository.findByDeletedOrderByOrderNum(false);
        FundCashFlowStandard fundCashFlowStandard = getBlankFinance(financeItems,FundCashFlowStandard.class, FundCashFlowStandardDetail.class,fundId,  year,  financeType,  cycle);
        setMultiRelationForStandardDetails(standardCashFlowItemRelationRepository,fundId,fundCashFlowStandard.getDetails());
        return fundCashFlowStandard;
    }

    public FundCapitalAccountStandard getBlankCapitalAccountStandard(Long fundId, String year, String financeType, String cycle) {
        Iterable<StandardCapitalAccountItem> financeItems = standardCapitalAccountItemRepository.findByDeletedOrderByOrderNum(false);
        FundCapitalAccountStandard fundCapitalAccountStandard = getBlankFinance(financeItems,FundCapitalAccountStandard.class, FundCapitalAccountStandardDetail.class,fundId,  year,  financeType,  cycle);
        setMultiRelationForStandardDetails(standardCapitalAccountItemRelationRepository,fundId,fundCapitalAccountStandard.getDetails());
        return fundCapitalAccountStandard;
    }

    private <T extends BaseFinance<R>,R extends BaseFinanceDetail,I extends BaseFinanceItem> T getFinanceOrigin(FinanceRepository<T> financeRepository,FinanceRepositoryCustom<T> financeRepositoryCustom,FundFinanceItemRepository fundFinanceItemRepository,Class<R> detailClazz,Long fundId, String year, String financeType, String cycle){
        T financeOrigin = financeRepository.findOneByFundIdAndYearAndFinanceTypeAndCycle(fundId, year, financeType, cycle);
        if (financeOrigin != null) {
            List<Long> ids = financeOrigin.getDetails().stream().mapToLong(detail -> detail.getItem().getId()).boxed().collect(toList());
            Iterable<I> financeItems;
            if(ids.size()>0){
                financeItems = fundFinanceItemRepository.findOther(fundId, ids);
            }else{
                financeItems = fundFinanceItemRepository.findByFundIdAndDeletedOrderByOrderNum(fundId, false);
            }
            List<R> details = financeOrigin.getDetails();
            financeRepositoryCustom.detach(financeOrigin);
            financeItems.forEach(financeItem -> {
                try{
                    R detail = detailClazz.newInstance();
                    detail.setItem(financeItem);
                    details.add(detail);
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
            details.sort((o1, o2) -> {
                if (!o1.getItem().isDeleted() && o2.getItem().isDeleted()) {
                    return -1;
                } else if (o1.getItem().isDeleted() && !o2.getItem().isDeleted()) {
                    return 1;
                } else {
                    return o1.getItem().getOrderNum() - o2.getItem().getOrderNum();
                }
            });
        }
        return financeOrigin;
    }

    private <T extends BaseFinance<R>,R extends BaseFinanceDetail,I extends BaseFinanceItem> T getFinanceStandard(FinanceRepository<T> financeRepository,FinanceRepositoryCustom<T> financeRepositoryCustom,StandardFinanceItemRepository standardFinanceItemRepository,StandardItemRelationRepository standardItemRelationRepository,Class<R> detailClazz,Long fundId, String year, String financeType, String cycle){
        T financeOrigin = financeRepository.findOneByFundIdAndYearAndFinanceTypeAndCycle(fundId, year, financeType, cycle);
        if (financeOrigin != null) {
            List<Long> ids = financeOrigin.getDetails().stream().mapToLong(detail -> detail.getItem().getId()).boxed().collect(toList());
            Iterable<I> financeItems;
            if(ids.size()>0){
                financeItems = standardFinanceItemRepository.findOther(ids);
            }else{
                financeItems = standardFinanceItemRepository.findByDeletedOrderByOrderNum(false);
            }
            List<R> details = financeOrigin.getDetails();
            financeRepositoryCustom.detach(financeOrigin);
            financeItems.forEach(financeItem -> {
                try{
                    R detail = detailClazz.newInstance();
                    detail.setItem(financeItem);
                    details.add(detail);
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
            details.sort((o1, o2) -> {
                if (!o1.getItem().isDeleted() && o2.getItem().isDeleted()) {
                    return -1;
                } else if (o1.getItem().isDeleted() && !o2.getItem().isDeleted()) {
                    return 1;
                } else {
                    return o1.getItem().getOrderNum() - o2.getItem().getOrderNum();
                }
            });
            setMultiRelationForStandardDetails(standardItemRelationRepository,fundId,details);
        }
        return financeOrigin;
    }

    private <T extends BaseStandardItemRelation,R extends BaseFinanceItem,D extends BaseFinanceDetail<R>> void setMultiRelationForStandardDetails(StandardItemRelationRepository repository,Long fundId,Iterable<D> financeDetails){
        Iterable<T> relationEntitys = repository.findByFundIdAndItemDeleted(fundId,false);
        Map<Long,String> relationMap = new HashMap<>();
        relationEntitys.forEach(relationEntity->{
            relationMap.put(relationEntity.getItem().getId(),relationEntity.getRelation());
        });
        financeDetails.forEach(detail->{
            String relation = relationMap.get(detail.getItem().getId());
            if(relation!=null){
                detail.getItem().getFinanceItem().setMultiRelation(relation);
            }
        });
    }

    public FundBalanceOrigin getBalanceOrigin(Long fundId, String year, String financeType, String cycle) {
        return getFinanceOrigin(fundBalanceOriginRepository,fundBalanceOriginRepository,fundBalanceItemRepository,FundBalanceOriginDetail.class,fundId,year,financeType,cycle);
    }

    public FundProfitLossOrigin getProfitLossOrigin(Long fundId, String year, String financeType, String cycle) {
        return getFinanceOrigin(fundProfitLossOriginRepository,fundProfitLossOriginRepository,fundProfitLossItemRepository,FundProfitLossOriginDetail.class,fundId,year,financeType,cycle);
    }

    public FundCashFlowOrigin getCashFlowOrigin(Long fundId, String year, String financeType, String cycle) {
        return getFinanceOrigin(fundCashFlowOriginRepository,fundCashFlowOriginRepository,fundCashFlowItemRepository,FundCashFlowOriginDetail.class,fundId,year,financeType,cycle);
    }

    public FundCapitalAccountOrigin getCapitalAccountOrigin(Long fundId, String year, String financeType, String cycle) {
        return getFinanceOrigin(fundCapitalAccountOriginRepository,fundCapitalAccountOriginRepository,fundCapitalAccountItemRepository,FundCapitalAccountOriginDetail.class,fundId,year,financeType,cycle);
    }

    public FundBalanceStandard getBalanceStandard(Long fundId, String year, String financeType, String cycle) {
        return getFinanceStandard(fundBalanceStandardRepository,fundBalanceStandardRepository,standardBalanceItemRepository,standardBalanceItemRelationRepository,FundBalanceStandardDetail.class,fundId,year,financeType,cycle);
    }

    public FundProfitLossStandard getProfitLossStandard(Long fundId, String year, String financeType, String cycle) {
        return getFinanceStandard(fundProfitLossStandardRepository,fundProfitLossStandardRepository,standardProfitLossItemRepository,standardProfitLossItemRelationRepository,FundProfitLossStandardDetail.class,fundId,year,financeType,cycle);
    }

    public FundCashFlowStandard getCashFlowStandard(Long fundId, String year, String financeType, String cycle) {
        return getFinanceStandard(fundCashFlowStandardRepository,fundCashFlowStandardRepository,standardCashFlowItemRepository,standardCashFlowItemRelationRepository,FundCashFlowStandardDetail.class,fundId,year,financeType,cycle);
    }
    public FundCapitalAccountStandard getCapitalAccountStandard(Long fundId, String year, String financeType, String cycle) {
        return getFinanceStandard(fundCapitalAccountStandardRepository,fundCapitalAccountStandardRepository,standardCapitalAccountItemRepository,standardCapitalAccountItemRelationRepository,FundCapitalAccountStandardDetail.class,fundId,year,financeType,cycle);
    }

    public void setCapitalAccountPlatformDetailsOrigin(Long relationId,FundCapitalAccountOrigin fundCapitalAccount){
        this.setCapitalAccountPlatformDetails(relationId,fundCapitalAccount,fundCapitalAccountPlatformOriginRepository);
    }
    public void setCapitalAccountPlatformDetailsStandard(Long relationId,FundCapitalAccountStandard fundCapitalAccount){
        this.setCapitalAccountPlatformDetails(relationId,fundCapitalAccount,fundCapitalAccountPlatformStandardRepository);
    }
    private <T extends BaseCapitalAccount<TD>,TD extends BaseCapitalAccountDetail,TP extends BaseCapitalAccountPlatform<T,TPD>,TPD extends BaseCapitalAccountPlatformDetail> void setCapitalAccountPlatformDetails(Long relationId,T fundCapitalAccount,CapitalAccountPlatformRepository<TP> capitalAccountPlatformRepository){
            if(fundCapitalAccount.getId()!=null){
                TP platform = capitalAccountPlatformRepository.findOneByInvestRelationIdAndCapitalAccountId(relationId,fundCapitalAccount.getId());
                if(fundCapitalAccount.getDetails().size()>0){
                    if(platform!=null){
                        fundCapitalAccount.getDetails().get(0).setPlatformAmount(platform.getPlatformPercent());
                    }else{
                        fundCapitalAccount.getDetails().get(0).setPlatformAmount(BigDecimal.ZERO);
                    }
                }
                Map<Long,BigDecimal> platformMap = new HashMap<>();
                if(platform!=null){
                    platform.getDetails().forEach((platformDetail)->{
                        platformMap.put(platformDetail.getItem().getId(),platformDetail.getAmount());
                    });
                }
                fundCapitalAccount.getDetails().forEach((fundCapitalAccountDetail)->{
                    BaseFinanceItem item = fundCapitalAccountDetail.getItem();
                    if(item!=null){
                        BigDecimal platformAmount = platformMap.get(item.getId());
                        if(platformAmount!=null){
                            fundCapitalAccountDetail.setPlatformAmount(platformAmount);
                        }
                    }
                });
            }
    }

    public <X extends BaseFinanceItem,T extends BaseCapitalAccount,R extends BaseCapitalAccountDetail<X>> void setCapitalAccountFirstLine(T fundCapitalAccount,Class<R> detailClazz,Class<X> itemClazz){
        try{
            BigDecimal gpPercent = fundCapitalAccount.getGpPercent();
            BigDecimal lpPercent = fundCapitalAccount.getLpPercent();
            if(gpPercent==null) gpPercent = BigDecimal.ZERO;
            if(lpPercent==null) lpPercent = BigDecimal.ZERO;
            R detail = detailClazz.newInstance();
            detail.setAmount(new BigDecimal(100));
            detail.setGpAmount(gpPercent);
            detail.setLpAmount(lpPercent);
            X item = itemClazz.newInstance();
            item.setFinanceItem(new FinanceItem());
            item.getFinanceItem().setName("比例(%)");
            item.getFinanceItem().setAlign(1);
            item.getFinanceItem().setBold(true);
            item.getFinanceItem().setBackgroundColor("#cccccc");
            detail.setItem(item);
            fundCapitalAccount.getDetails().add(0,detail);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private <T extends BaseFinance<D>,R extends FinanceRepository,D extends BaseFinanceDetail> void saveFinance(R repository,T finance){
        finance.setFinanceDate(finance.buildFinanceDate());
        finance.setDetails(finance.getDetails().stream().filter(detail -> detail.getAmount() != null).collect(toList()));
        repository.save(finance);
    }
    private <T extends BaseCapitalAccount<D>,R extends FinanceRepository,D extends BaseCapitalAccountDetail> BigDecimal saveCapitalAccount(R repository,T capitalAccount){
        capitalAccount.setFinanceDate(capitalAccount.buildFinanceDate());
        BigDecimal platformPercent = null;
        if(capitalAccount.getDetails().size()>0){
            capitalAccount.setGpPercent(capitalAccount.getDetails().get(0).getGpAmount());
            capitalAccount.setLpPercent(capitalAccount.getDetails().get(0).getLpAmount());
            platformPercent=capitalAccount.getDetails().get(0).getPlatformAmount();
        }
        capitalAccount.getDetails().remove(0);
        capitalAccount.setDetails(capitalAccount.getDetails().stream().filter(detail -> detail.getAmount() != null).collect(toList()));
        repository.save(capitalAccount);
        return platformPercent;
    }
    private <T extends BaseCapitalAccount<D>,D extends BaseCapitalAccountDetail,P extends BaseCapitalAccountPlatform<T,PD>,PD extends BaseCapitalAccountPlatformDetail,R extends CapitalAccountPlatformRepository<P>>void saveCapitalAccountPlatform(T capitalAccount,Long relationId,BigDecimal platformPercent,R repository,Class<P> platformClazz,Class<PD> platformDetailClass){
       try{
           P platform = repository.findOneByInvestRelationIdAndCapitalAccountId(relationId,capitalAccount.getId());
           if(platform==null){
               platform = platformClazz.newInstance();
               platform.setCapitalAccount(capitalAccount);
               platform.setInvestRelation(investRelationRepository.findOne(relationId));
           }
           final P platform1 = platform;
           platform1.setDetails(new ArrayList<>());
           if(capitalAccount.getDetails().size()>0){
               platform1.setPlatformPercent(platformPercent);
               capitalAccount.getDetails().forEach((capitalAccountDetail)->{
                   try{
                       PD detail = platformDetailClass.newInstance();
                       detail.setItem(capitalAccountDetail.getItem());
                       detail.setAmount(capitalAccountDetail.getPlatformAmount());
                       detail.setMemo(capitalAccountDetail.getMemo());
                       platform1.getDetails().add(detail);
                   }catch (Exception e){
                       e.printStackTrace();
                   }
               });
           }
           repository.save(platform);
       }catch (Exception e){
           e.printStackTrace();
       }

    }

    public void saveBalanceOrigin(FundBalanceOrigin fundBalanceOrigin) {
        saveFinance(fundBalanceOriginRepository,fundBalanceOrigin);
    }

    public void saveProfitLossOrigin(FundProfitLossOrigin fundProfitLossOrigin) {
        saveFinance(fundProfitLossOriginRepository,fundProfitLossOrigin);
    }

    public void saveCashFlowOrigin(FundCashFlowOrigin fundCashFlowOrigin) {
        saveFinance(fundProfitLossOriginRepository,fundCashFlowOrigin);
    }

    public void saveCapitalAccountOrigin(Long relationId,FundCapitalAccountOrigin fundCapitalAccountOrigin){
        BigDecimal platformPercent = saveCapitalAccount(fundCapitalAccountOriginRepository,fundCapitalAccountOrigin);
        saveCapitalAccountPlatform(fundCapitalAccountOrigin,relationId,platformPercent,fundCapitalAccountPlatformOriginRepository,FundCapitalAccountPlatformOrigin.class,FundCapitalAccountPlatformOriginDetail.class);
    }

    public void saveBalanceStandard(FundBalanceStandard fundBalanceStandard) {
        saveFinance(fundBalanceStandardRepository,fundBalanceStandard);
    }

    public void saveProfitLossStandard(FundProfitLossStandard fundProfitLossStandard) {
        saveFinance(fundProfitLossStandardRepository,fundProfitLossStandard);
    }

    public void saveCashFlowStandard(FundCashFlowStandard fundCashFlowStandard) {
        saveFinance(fundProfitLossStandardRepository,fundCashFlowStandard);
    }
    public void saveCapitalAccountStandard(Long relationId,FundCapitalAccountStandard fundCapitalAccountStandard){
        BigDecimal platformPercent = saveCapitalAccount(fundCapitalAccountStandardRepository,fundCapitalAccountStandard);
        saveCapitalAccountPlatform(fundCapitalAccountStandard,relationId,platformPercent,fundCapitalAccountPlatformStandardRepository,FundCapitalAccountPlatformStandard.class,FundCapitalAccountPlatformStandardDetail.class);
    }

    private <T extends BaseFinanceItem> Map<String, Object> getFinancesForView(FinanceRepository financeRepository,Iterable<T> financeItems, Long fundId, LocalDate beginDate, LocalDate endDate){
        Map<String, Object> result = new HashMap<>();
        List<BaseFinance<BaseFinanceDetail>> finances = financeRepository.findByFundIdAndFinanceDateGreaterThanEqualAndFinanceDateLessThanEqual(fundId, beginDate, endDate);
        List<KeyEntity> keyEntitys = new ArrayList<>();
        Map<String, DataEntity> detailMap = new HashMap<>();
        finances.forEach(finance -> {
            String key = finance.key();
            String financeType = finance.getFinanceType();
            KeyEntity keyEntity = new KeyEntity(key, financeType,finance.isAudit());
            keyEntitys.add(keyEntity);
            finance.getDetails().forEach(detail -> detailMap.put(key + "_" + detail.getItem().getId(), new DataEntity(detail.getAmount(), detail.getMemo())));
        });
        List<Map<String, Object>> datas = new ArrayList<>();
        financeItems.forEach(financeItem -> {
            Map<String, Object> data = new HashMap<>();
            data.put("item", financeItem.getFinanceItem());
            data.put("itemId", financeItem.getId());
            datas.add(data);
        });
        finances.forEach(finance -> {
            String key = finance.key();
            datas.forEach(data -> {
                Long itemId = (Long) data.get("itemId");
                DataEntity dataEntity = detailMap.get(key + "_" + itemId);
                if (dataEntity != null) {
                    data.put(key, dataEntity);
                } else {
                    data.put(key, new DataEntity(null, null));
                }
            });
        });
        result.put("keys", keyEntitys);
        result.put("datas", datas);
        return result;
    }

    private <T extends BaseFinanceItem,CA extends BaseCapitalAccount> Map<String, Object> getCapitalAccountForView(Long relationId,FinanceRepository<CA> financeRepository,Iterable<T> financeItems, Long fundId, LocalDate beginDate, LocalDate endDate,CapitalAccountPlatformRepository capitalAccountPlatformRepository){
        Map<String, Object> result = new HashMap<>();
        List<CA> finances = financeRepository.findByFundIdAndFinanceDateGreaterThanEqualAndFinanceDateLessThanEqual(fundId, beginDate, endDate);
        List<KeyEntity> keyEntitys = new ArrayList<>();
        Map<String, CapitalAccountEntity> detailMap = new HashMap<>();
        List<Long> ids = new ArrayList<>();
        finances.forEach(finance -> {
            String key = finance.key();
            String financeType = finance.getFinanceType();
            KeyEntity keyEntity = new KeyEntity(key, financeType,finance.isAudit());
            keyEntitys.add(keyEntity);
            BigDecimal gpPercent = finance.getGpPercent();
            BigDecimal lpPercent = finance.getLpPercent();
            if (gpPercent==null) gpPercent = BigDecimal.ZERO;
            if(lpPercent==null) lpPercent = BigDecimal.ZERO;
            detailMap.put(key + "_0",new CapitalAccountEntity(new BigDecimal(100), gpPercent,lpPercent));
            finance.getDetails().forEach(detail ->{
                BaseCapitalAccountDetail caDetail = (BaseCapitalAccountDetail)detail;
                detailMap.put(key + "_" + caDetail.getItem().getId(), new CapitalAccountEntity(caDetail.getAmount(), caDetail.getGpAmount(),caDetail.getLpAmount()));
            } );
            ids.add(finance.getId());
        });
        List<BaseCapitalAccountPlatform> platforms = capitalAccountPlatformRepository.findByInvestRelationIdAndCapitalAccountIdIn(relationId,ids);
        Map<String, PlatformAccountEntity> platformMap = new HashMap<>();
        platforms.forEach(platform->{
            String key = platform.getCapitalAccount().key();
            BigDecimal platformPercent = platform.getPlatformPercent();
            if(platformPercent==null) platformPercent = BigDecimal.ZERO;
            platformMap.put(key+"_0",new PlatformAccountEntity(platformPercent,""));
            platform.getDetails().forEach(detail->{
                BaseCapitalAccountPlatformDetail caDetail = (BaseCapitalAccountPlatformDetail)detail;
                platformMap.put(key+"_"+caDetail.getItem().getId(),new PlatformAccountEntity(caDetail.getAmount(),caDetail.getMemo()));
            });
        });

        List<Map<String, Object>> datas = new ArrayList<>();
        Map<String, Object> firstData = new HashMap<>();
        FinanceItem item = new FinanceItem();
        item.setName("比例(%)");
        item.setAlign(1);
        item.setBold(true);
        item.setBackgroundColor("#cccccc");
        firstData.put("item", item);
        firstData.put("itemId", 0L);
        datas.add(firstData);
        financeItems.forEach(financeItem -> {
            Map<String, Object> data = new HashMap<>();
            data.put("item", financeItem.getFinanceItem());
            data.put("itemId", financeItem.getId());
            datas.add(data);
        });
        finances.forEach(finance -> {
            String key = finance.key();
            datas.forEach(data -> {
                Long itemId = (Long) data.get("itemId");
                CapitalAccountEntity capitalAccountEntity = detailMap.get(key + "_" + itemId);
                if (capitalAccountEntity != null) {
                    PlatformAccountEntity platformAccountEntity = platformMap.get(key + "_" + itemId);
                    if(platformAccountEntity!=null){
                        capitalAccountEntity.setPlatformAmount(platformAccountEntity.getPlatformAmount());
                        capitalAccountEntity.setMemo(platformAccountEntity.getMemo());
                    }
                    data.put(key, capitalAccountEntity);
                } else {
                    data.put(key, new CapitalAccountEntity(null, null,null));
                }
            });
        });
        result.put("keys", keyEntitys);
        result.put("datas", datas);
        return result;
    }

    public Map<String, Object> getBalanceOriginsForView(Long fundId, LocalDate beginDate, LocalDate endDate) {
        Iterable<FundBalanceItem> financeItems = fundBalanceItemRepository.findByFundIdAndDeletedOrderByOrderNum(fundId, false);
        return getFinancesForView(fundBalanceOriginRepository,financeItems,fundId,beginDate,endDate);
    }
    public Map<String, Object> getBalanceStandardForView(Long fundId, LocalDate beginDate, LocalDate endDate) {
        Iterable<StandardBalanceItem> financeItems = standardBalanceItemRepository.findByDeletedOrderByOrderNum(false);
        return getFinancesForView(fundBalanceStandardRepository,financeItems,fundId,beginDate,endDate);
    }

    public Map<String, Object> getProfitLossOriginsForView(Long fundId, LocalDate beginDate, LocalDate endDate) {
        Iterable<FundProfitLossItem> financeItems = fundProfitLossItemRepository.findByFundIdAndDeletedOrderByOrderNum(fundId, false);
        return getFinancesForView(fundProfitLossOriginRepository,financeItems,fundId,beginDate,endDate);
    }
    public Map<String, Object> getProfitLossStandardForView(Long fundId, LocalDate beginDate, LocalDate endDate) {
        Iterable<StandardProfitLossItem> financeItems = standardProfitLossItemRepository.findByDeletedOrderByOrderNum(false);
        return getFinancesForView(fundProfitLossStandardRepository,financeItems,fundId,beginDate,endDate);
    }

    public Map<String, Object> getCashFlowOriginsForView(Long fundId, LocalDate beginDate, LocalDate endDate) {
        Iterable<FundCashFlowItem> financeItems = fundCashFlowItemRepository.findByFundIdAndDeletedOrderByOrderNum(fundId, false);
        return getFinancesForView(fundCashFlowOriginRepository,financeItems,fundId,beginDate,endDate);
    }

    public Map<String, Object> getCashFlowStandardForView(Long fundId, LocalDate beginDate, LocalDate endDate) {
        Iterable<StandardCashFlowItem> financeItems = standardCashFlowItemRepository.findByDeletedOrderByOrderNum(false);
        return getFinancesForView(fundCashFlowStandardRepository,financeItems,fundId,beginDate,endDate);
    }

    public Map<String, Object> getCapitalAccountOriginsForView(Long relationId,Long fundId, LocalDate beginDate, LocalDate endDate) {
        Iterable<FundCapitalAccountItem> financeItems = fundCapitalAccountItemRepository.findByFundIdAndDeletedOrderByOrderNum(fundId, false);
        return getCapitalAccountForView(relationId,fundCapitalAccountOriginRepository,financeItems,fundId,beginDate,endDate,fundCapitalAccountPlatformOriginRepository);
    }

    public Map<String, Object> getCapitalAccountStandardForView(Long relationId,Long fundId, LocalDate beginDate, LocalDate endDate) {
        Iterable<StandardCapitalAccountItem> financeItems = standardCapitalAccountItemRepository.findByDeletedOrderByOrderNum(false);
        return getCapitalAccountForView(relationId,fundCapitalAccountStandardRepository,financeItems,fundId,beginDate,endDate,fundCapitalAccountPlatformStandardRepository);
    }

    public Iterable importFinanceItem(Long fundId,String type,Long sourceFundId,String sourceType,List<String> sourceRelations){
        final List<FinanceItem> financeItems = new ArrayList<>();
        if(sourceFundId!=null&&sourceFundId>0){
            Iterable<BaseFinanceItem> fundFinanceItems = getOriginFinanceItems(sourceFundId,sourceType);
            fundFinanceItems.forEach(fundFinanceItem->{
                financeItems.add(fundFinanceItem.getFinanceItem());
            });
        }else{
            Iterable<BaseFinanceItem> standardFinanceItems = getStandardFinanceItems(sourceType);
            standardFinanceItems.forEach(standardFinanceItem->{
                financeItems.add(standardFinanceItem.getFinanceItem());
            });
        }
        if(!sourceRelations.contains("inner")||!sourceRelations.contains("outer")){
            financeItems.forEach(financeItem -> {
                if(!sourceRelations.contains("inner")){
                    financeItem.setInnerRelation("");
                }
                if(!sourceRelations.contains("outer")){
                    financeItem.setOuterRelation("");
                }
            });
        }
        if(fundId!=null&&fundId>0){
            //TODO 跨表钩稽关系复制
            Fund fund = new Fund();
            fund.setId(fundId);
            if(type.equals("BS")){
                List<FundBalanceItem> fundBalanceItems =getBaseFinanceItems(financeItems,FundBalanceItem.class);
                fundBalanceItems.forEach(fundBalanceItem -> fundBalanceItem.setFund(fund));
                return this.saveAllBalanceItems(fundId,fundBalanceItems);
            }else if(type.equals("CF")){
                List<FundCashFlowItem> fundCashFlowItems =getBaseFinanceItems(financeItems,FundCashFlowItem.class);
                fundCashFlowItems.forEach(fundCashFlowItem -> fundCashFlowItem.setFund(fund));
                return this.saveAllCashFlowItems(fundId,fundCashFlowItems);
            }else if(type.equals("PL")){
                List<FundProfitLossItem> fundProfitLossItems =getBaseFinanceItems(financeItems,FundProfitLossItem.class);
                fundProfitLossItems.forEach(fundProfitLossItem -> fundProfitLossItem.setFund(fund));
                return this.saveAllProfitLossItems(fundId,fundProfitLossItems);
            }else if(type.equals("CA")){
                List<FundCapitalAccountItem> fundCapitalAccountItems =getBaseFinanceItems(financeItems,FundCapitalAccountItem.class);
                fundCapitalAccountItems.forEach(fundCapitalAccountItem -> fundCapitalAccountItem.setFund(fund));
                return this.saveAllCapitalAccountItems(fundId,fundCapitalAccountItems);
            }
        }else{
            if(type.equals("BS")){
                List<StandardBalanceItem> standardBalanceItems =getBaseFinanceItems(financeItems,StandardBalanceItem.class);
                return this.saveAllBalanceItemsStandard(standardBalanceItems);
            }else if(type.equals("CF")){
                List<StandardCashFlowItem> standardCashFlowItems =getBaseFinanceItems(financeItems,StandardCashFlowItem.class);
                return this.saveAllCashFlowItemsStandard(standardCashFlowItems);
            }else if(type.equals("PL")){
                List<StandardProfitLossItem> standardProfitLossItems =getBaseFinanceItems(financeItems,StandardProfitLossItem.class);
                return this.saveAllProfitLossItemsStandard(standardProfitLossItems);
            }else if(type.equals("CA")){
                List<StandardCapitalAccountItem> standardCapitalAccountItems =getBaseFinanceItems(financeItems,StandardCapitalAccountItem.class);
                return this.saveAllCapitalAccountItemsStandard(standardCapitalAccountItems);
            }
        }
        return null;
    }


    private <T extends BaseFinanceItem> List<T> getBaseFinanceItems( List<FinanceItem> financeItems,Class<T> tClass){
        return financeItems.stream().map(financeItem -> {
            try{
                T t = tClass.newInstance();
                t.setDeleted(false);
                t.setFinanceItem(financeItem);
                return t;
            }catch (Exception e){
                return null;
            }
        }).collect(toList());
    }
    private Iterable<BaseFinanceItem> getStandardFinanceItems(String sourceType){
        StandardFinanceItemRepository repository = null;
        if(sourceType.equals("BS")){
            repository = standardBalanceItemRepository;
        }else if(sourceType.equals("CF")){
            repository = standardCashFlowItemRepository;
        }else if(sourceType.equals("PL")){
            repository = standardProfitLossItemRepository;
        }else if(sourceType.equals("CA")){
            repository = standardCapitalAccountItemRepository;
        }
        if(repository!=null){
            return repository.findByDeletedOrderByOrderNum(false);
        }else{
            return null;
        }
    }

    private Iterable<BaseFinanceItem> getOriginFinanceItems(Long fundId,String sourceType){
        FundFinanceItemRepository repository = null;
        if(sourceType.equals("BS")){
            repository = fundBalanceItemRepository;
        }else if(sourceType.equals("CF")){
            repository = fundCashFlowItemRepository;
        }else if(sourceType.equals("PL")){
            repository = fundProfitLossItemRepository;
        }else if(sourceType.equals("CA")){
            repository = fundCapitalAccountItemRepository;
        }
        if(repository!=null){
            return repository.findByFundIdAndDeletedOrderByOrderNum(fundId,false);
        }else{
            return null;
        }
    }

    public static class DataEntity {
        private BigDecimal amount;
        private String memo;

        public DataEntity(BigDecimal amount, String memo) {
            this.amount = amount;
            this.memo = memo;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }
    }

    public static class PlatformAccountEntity {
        private BigDecimal platformAmount;
        private String memo;

        public PlatformAccountEntity(BigDecimal platformAmount,String memo){
            this.platformAmount = platformAmount;
            this.memo = memo;
        }
        public BigDecimal getPlatformAmount() {
            return platformAmount;
        }

        public void setPlatformAmount(BigDecimal platformAmount) {
            this.platformAmount = platformAmount;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }
    }
    public static class CapitalAccountEntity {
        private BigDecimal amount;
        private BigDecimal gpAmount;
        private BigDecimal lpAmount;
        private BigDecimal platformAmount;
        private String memo;

        public CapitalAccountEntity(BigDecimal amount,BigDecimal gpAmount,BigDecimal lpAmount) {
            this.amount = amount;
            this.gpAmount = gpAmount;
            this.lpAmount = lpAmount;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public BigDecimal getGpAmount() {
            return gpAmount;
        }

        public void setGpAmount(BigDecimal gpAmount) {
            this.gpAmount = gpAmount;
        }

        public BigDecimal getLpAmount() {
            return lpAmount;
        }

        public void setLpAmount(BigDecimal lpAmount) {
            this.lpAmount = lpAmount;
        }

        public BigDecimal getPlatformAmount() {
            return platformAmount;
        }

        public void setPlatformAmount(BigDecimal platformAmount) {
            this.platformAmount = platformAmount;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }
    }

    public static class KeyEntity {
        private String key;
        private String financeType;
        private boolean isAudit;

        public KeyEntity(String key, String financeType,boolean isAudit) {
            this.key = key;
            this.financeType = financeType;
            this.isAudit = isAudit;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getFinanceType() {
            return financeType;
        }

        public void setFinanceType(String financeType) {
            this.financeType = financeType;
        }

        public boolean isAudit() {
            return isAudit;
        }

        public void setAudit(boolean audit) {
            isAudit = audit;
        }
    }
}
