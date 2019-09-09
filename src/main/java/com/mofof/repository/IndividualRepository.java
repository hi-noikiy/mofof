package com.mofof.repository;

import com.mofof.entity.common.Individual;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by hzh on 2017/8/6.
 */
public interface IndividualRepository extends CrudRepository<Individual, Long> {


    /**
     * 使用该方法需在参数加上'%'通配符
     *
     * @param value
     * @return
     */
    List<Individual> findAllByChineseNameLikeOrderByIdDesc(String value);

    /**
     * 使用该方法需在参数加上'%'通配符
     *
     * @param value
     * @return
     */
    List<Individual> findAllByEnglishNameLikeOrderByIdDesc(String value);


    Individual findById(Long id);


}
