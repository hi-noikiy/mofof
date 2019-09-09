package com.mofof.repository;

import org.springframework.data.repository.CrudRepository;

import com.mofof.entity.file.DirMetadata;

public interface DirMetadataRepository extends CrudRepository<DirMetadata, Long> {

  DirMetadata findByDirectoryId(Long id);

}
