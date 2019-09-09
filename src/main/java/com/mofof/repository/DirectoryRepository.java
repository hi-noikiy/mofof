package com.mofof.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mofof.entity.file.Directory;

/**
 * Created by Qian, Wenliang on 17/7/27.
 */
public interface DirectoryRepository extends CrudRepository<Directory, Long> {
    Directory findByName(String name);

    Directory findByParentId(long parentId);
    @Query(value = "select * from directory where parent_id IS NULL and user_id = :id ", nativeQuery = true)
    Directory find(@Param("id") long id);
    Directory findByParentIdAndName(long parentId, String name);
}
