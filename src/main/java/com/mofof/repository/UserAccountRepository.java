package com.mofof.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.mofof.entity.system.UserAccount;

/**
 * Created by Qian, Wenliang on 2017/8/24.
 */
public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {
    Set<UserAccount> findAllByOrderByIdDesc();
   // Iterable<UserAccount> findAllByStatusOrderByIdDesc(int status);
    UserAccount findByUserId(long id);
    UserAccount findByUsername(String username);
    UserAccount findById(long id);
    UserAccount findByPassword(String oldPass);
}
