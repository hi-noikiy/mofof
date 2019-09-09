package com.mofof.repository;


import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.mofof.entity.file.File;

/**
 * Created by Qian, Wenliang on 17/7/27.
 */
public interface FileRepository extends CrudRepository<File, Long> {

  File findByDirPathAndId(String dirPath, long id);

  Set<File> findByDirectoryId(long dirId);

}
