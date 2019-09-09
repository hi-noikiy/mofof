package com.mofof.repository;

import com.mofof.entity.dict.ext.Province;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by hzh on 2018/12/23.
 */
public interface ProvinceRepository extends CrudRepository<Province,Long> {

    List<Province> findAll();
}
