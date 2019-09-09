package com.mofof.repository;

import com.mofof.entity.investor.Investor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author HanWeiFeng 2017年6月4日
 */
public interface InvestorRepository extends CrudRepository<Investor, Long> {
    List<Investor> findAllByPlatformOrderByIdDesc(boolean platform);

    //@Query("select i from Investor i where i.individual.chineseName like '%?%' ")
    Iterable<Investor> findAllByIndividualChineseNameLike(String chineseName);
    Iterable<Investor> findAllByOrganizationChineseNameLike(String chineseName);

    List<Investor> findAllByOrderByIdDesc();
}
