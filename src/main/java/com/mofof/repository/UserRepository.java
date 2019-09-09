package com.mofof.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.mofof.entity.system.User;

/**
 * Created by ChenErliang on 2017/5/3.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    Iterable<User> findAllByOrderByIdDesc();
    Iterable<User> findAllByStatusOrderByIdDesc(int i);
    User findByPhone(String oldPhone);
    Set<User> findByRoleId(long id);
}
