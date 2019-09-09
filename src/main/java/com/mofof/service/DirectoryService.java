package com.mofof.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mofof.entity.file.DirMetadata;
import com.mofof.entity.file.Directory;
import com.mofof.entity.system.UserAccount;
import com.mofof.repository.DirMetadataRepository;
import com.mofof.repository.DirectoryRepository;

@Service
public class DirectoryService {

  @Autowired
  DirectoryRepository directoryRepository;
  @Autowired
  DirMetadataRepository dirMetadataRepository;
  
//  public Directory createDir(long parentId, Directory directory) {
//    Directory parent = directoryRepository.findOne(parentId);
//    if (parent != null) {
//      
//      directory.setParent(parent);
//    }
//    directory.setAuthority(Directory.A_AUTHORITY);
//    directory.setFileType(Directory.PERSON_FILE_TYPE);
//    return directoryRepository.save(directory);
//  }
  public Directory findByParentId(long parentId) {
    return directoryRepository.findByParentId(parentId);
  }
  
  public Directory save(Directory directory) {
    return directoryRepository.save(directory);
  }

  public Directory findById(long id) {
    return directoryRepository.findOne(id);
  }

  public Directory find(long id) {
    return directoryRepository.find(id);
  }

  public void delete(long id) {
    directoryRepository.delete(id);
  }
  
  public Directory findByParentIdAndName(long parentId, String name) {
    return directoryRepository.findByParentIdAndName(parentId, name); 
   }

  public Directory findByName(String name) {   
    return directoryRepository.findByName(name);
  }
  /**
   * 创建个人文件夹
   * @param userAccount
   * @return
   */
  public Directory create(UserAccount userAccount) {   
    
    //创建个人文件夹
    Directory directory = new Directory();   
    DirMetadata dirMetadata = DirMetadata.userRootDir();
    directory.setName(userAccount.getUser().getName());
    directory.setUser(userAccount.getUser());
    dirMetadata.setDirectory(directory);
    dirMetadataRepository.save(dirMetadata);
    //创建回收站
    Directory directory2 = new Directory();    
    DirMetadata dirMetadata2 = DirMetadata.trashBin();
    directory2.setName(Directory.TRASH_BIN);
    directory2.setParent(directory);
    dirMetadata2.setDirectory(directory2);
    dirMetadataRepository.save(dirMetadata2);     
    
    return directory;
  }
  
}
