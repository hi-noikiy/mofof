package com.mofof.repository;

import com.mofof.entity.common.Organization;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author HanWeiFeng 2017年6月27日
 */
public interface OrganizationRepository extends CrudRepository<Organization, Long> {

    List<Organization> findAllByChineseNameOrderByIdDesc(String value);

    List<Organization> findAllByEnglishNameOrderByIdDesc(String value);
}
