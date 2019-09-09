package com.mofof.repository;

import com.mofof.entity.system.MenuItem;

import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author
 */
public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {
    boolean existsByRoleIdAndName(Long roleId, String name);
    Set<MenuItem> findByRoleId(Long roleId);
    @Modifying
    @Query("delete from MenuItem where role_id= ?1")
    void deleteByRoleId(@Param(value = "id") long id);
    MenuItem findByIcon(String icon);
    boolean existsByRoleIdAndIcon(long rMofofId, String string);
    boolean existsByIcon(String icon);
}
