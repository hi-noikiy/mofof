package com.mofof.repository;

import com.mofof.entity.platform.Platform;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author hzh
 * @date 2019-06-27
 */
public interface PlatformRepository extends CrudRepository<Platform, Long>, JpaSpecificationExecutor<Platform>{
    List<Platform> findAllByOrderByIdDesc();
}
