package com.mofof.repository;

import org.springframework.data.repository.CrudRepository;

import com.mofof.entity.file.FileMetadata;

public interface FileMetadataRepository extends CrudRepository<FileMetadata, Long>{

}
