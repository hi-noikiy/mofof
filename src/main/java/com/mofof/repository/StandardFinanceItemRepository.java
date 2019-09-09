package com.mofof.repository;

import com.mofof.entity.finance.BaseFinanceItem;
import com.mofof.entity.finance.StandardProfitLossItem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Created by chenerliang on 2017/8/22.
 */
@NoRepositoryBean
public interface StandardFinanceItemRepository<T extends BaseFinanceItem> extends CrudRepository<T, Long> {
    Iterable<T> findByDeletedOrderByOrderNum(boolean deleted);

    @Modifying
    @Query("update #{#entityName} set deleted=true where id not in ?1")
    void deleteOther(List<Long> ids);

    @Query("from #{#entityName} where deleted=false and id not in ?1")
    Iterable<T> findOther(List<Long> ids);

    @Modifying
    @Query("update #{#entityName} set deleted=true")
    void deleteAll();
}
