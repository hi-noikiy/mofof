package com.mofof.repository;

import com.mofof.entity.fund.Fund;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by ChenErliang on 17/5/5.
 */
public interface FundRepository extends CrudRepository<Fund, Long>, JpaSpecificationExecutor<Fund> {
//    findAll()

    Iterable<Fund> findAllByOrderByIdDesc();

    Iterable<Fund> findAllByInvestStatusOrderByIdDesc(String investStatus);

    Iterable<Fund> findByAdministratorId(Long id);

    Iterable<Fund> findByOrganizationChineseNameLikeIgnoreCase(String chineseName);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update fund set administrator_id=null where id=?1")
    void delinkAdminGroup(Long id);

    @Query(nativeQuery = true, value = "select * from fund where administrator_id is null")
    Iterable<Fund> getFundNotLinkedToAnyAdmin();
}
