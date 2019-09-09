package com.mofof.repository;

import com.mofof.entity.administrator.Administrator;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ChenErliang on 17/5/4.
 */
public interface AdministratorRepository extends CrudRepository<Administrator, Long> {
    Iterable<Administrator> findAllByOrderByIdDesc();
}
