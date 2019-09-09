package com.mofof.repository;

import com.mofof.entity.finance.BaseStandardItemRelation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Created by chenerliang on 2017/8/22.
 */
@NoRepositoryBean
public interface StandardItemRelationRepository<T extends BaseStandardItemRelation> extends CrudRepository<T, Long> {

    Iterable<T> findByFundIdAndItemDeleted(Long fundId, boolean deleted);

    @Modifying
    @Query("delete from #{#entityName} where fund.id=?1 and item.id not in ?2")
    void deleteOther(Long fundId, List<Long> ids);

    @Modifying
    @Query("delete from #{#entityName}  where fund.id=?1")
    void deleteAll(Long fundId);

}
