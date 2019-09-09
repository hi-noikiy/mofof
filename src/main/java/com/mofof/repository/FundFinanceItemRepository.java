package com.mofof.repository;

import com.mofof.entity.finance.BaseFinanceItem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Created by chenerliang on 2017/8/22.
 */
@NoRepositoryBean
public interface FundFinanceItemRepository<T extends BaseFinanceItem> extends CrudRepository<T, Long> {
    Iterable<T> findByFundIdAndDeletedOrderByOrderNum(Long fundId, boolean deleted);

    @Modifying
    @Query("update #{#entityName} set deleted=true where fund.id=?1 and id not in ?2")
    void deleteOther(Long fundId, List<Long> ids);

    @Query("from #{#entityName} where deleted=false and fund.id=?1 and id not in ?2")
    Iterable<T> findOther(Long fundId, List<Long> ids);

    @Modifying
    @Query("update #{#entityName} set deleted=true where fund.id=?1")
    void deleteAll(Long fundId);
}
