package com.mofof.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mofof.entity.file.DirMetadata;
import com.mofof.repository.DirMetadataRepository;

@Service
public class DirMetadataService {

  @Autowired
  DirMetadataRepository dirMetadataRepository;
  
  public DirMetadata save(DirMetadata dirMetadata) {
    
    return dirMetadataRepository.save(dirMetadata);
  }

  public DirMetadata findByDirectoryId(Long id) {
    
    return dirMetadataRepository.findByDirectoryId(id);
  }
}
