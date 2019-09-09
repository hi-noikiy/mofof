package com.mofof.repository;

import com.mofof.entity.dict.ext.Bank;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 *
 * Created by hzh on 2018/12/22.
 */
public interface BankRepository extends CrudRepository<Bank, Long> {

    List<Bank> findAll();

    List<Bank> findAllByBankType(String bankType);
}