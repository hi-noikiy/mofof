package com.mofof.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mofof.entity.file.Directory;
import com.mofof.entity.file.File;
import com.mofof.entity.file.FileMetadata;
import com.mofof.repository.DirectoryRepository;
import com.mofof.repository.FileMetadataRepository;
import com.mofof.repository.FileRepository;

@Service
public class FileService {

  @Autowired
  FileRepository fileRepository;
  @Autowired
  DirectoryRepository directoryRepository;
  @Autowired
  FileStorageService fileStorageService;
  @Autowired
  FileMetadataRepository fileMetadataRepository;

  public File save(File file) {   
    return null;
  }

  public File findByDirPathAndId(String dirPath, long id) throws IOException {
    File file = fileRepository.findByDirPathAndId(dirPath, id);
    return file;   
  }

  public FileMetadata upload2(long dirId, MultipartFile file) { 
    return null;
  }
  public FileMetadata upload(long dirId, MultipartFile file) { 
    FileMetadata fileMetadata = new FileMetadata();
    Directory directory = directoryRepository.findOne(dirId);
    String dirpath = "";
    if (directory != null) {
      dirpath = directory.getName() +"/";
      while (directory.getParent() != null) {
        directory = directory.getParent();
        dirpath = directory.getName() +"/" + dirpath;       
      }
    
      Path filePath = fileStorageService.storeFile(file);
      //ServletUriComponentsBuilder.fromCurrentContextPath().path(path).path(fileName).toUriString();
      
      fileMetadata.setName(filePath.getFileName().toString());
      fileMetadata.setFileSize(String.valueOf(file.getSize()));
      fileMetadata.setContentType(file.getContentType());
      fileMetadata = fileMetadataRepository.save(fileMetadata);
      File file2 = new File();
      file2.setDirPath(dirpath);
      file2.setDirectory(directory);
      file2.setFileMetadata(fileMetadata);
      file2.setPath(filePath.toString());
      fileRepository.save(file2);
    }
    return fileMetadata;
  }

  public Set<File> findByDirectoryId(long dirId) {
    return fileRepository.findByDirectoryId(dirId);
  }

  public File findById(long id) {    
    return fileRepository.findOne(id);
  }
}
