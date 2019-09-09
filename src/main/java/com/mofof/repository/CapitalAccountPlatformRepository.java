package com.mofof.repository;

import com.mofof.entity.finance.BaseCapitalAccountPlatform;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Created by ChenErliang on 2017/8/6.
 */
@NoRepositoryBean
public interface CapitalAccountPlatformRepository<T extends BaseCapitalAccountPlatform> extends CrudRepository<T, Long> {

    T findOneByInvestRelationIdAndCapitalAccountId(Long relationId, Long capitalAccountId);

   // @Query("from #{#entityName} where investRelation.id=?1 and capitalAccount.id in ?2")
   List<T> findByInvestRelationIdAndCapitalAccountIdIn(Long relationId, List<Long> ids);
}
