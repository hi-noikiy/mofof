package com.mofof.repository;

import com.mofof.entity.system.Perm;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

/**
 * @author
 */
public interface PermRepository extends CrudRepository<Perm, Long> {
    boolean existsByRoleIdAndValue(Long roleId, String value);
    Set<Perm> findPermsByRoleId(Long roleId);
}
