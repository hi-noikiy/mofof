package com.mofof.controller;



import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import com.mofof.entity.file.Directory;
import com.mofof.entity.file.File;
import com.mofof.entity.file.FileMetadata;
import com.mofof.service.DirectoryService;
import com.mofof.service.FileService;
import com.mofof.util.GsonBuilderFactory;


@RestController
public class FileController {
  @Autowired
  DirectoryService directoryService;
  @Autowired
  FileService fileService;

  @PostMapping("/users/directories/{dirId}/files")
  public FileMetadata upload(@PathVariable long dirId, @RequestParam("file") MultipartFile file) {
    return fileService.upload(dirId, file);   
  }
  
  public void download() {
    
  }
  
  @GetMapping("/users/files/{id}")
  public void show(@PathVariable long id,HttpServletResponse response) throws IOException {    
    File file = fileService.findById(id);
    if (file != null) {
      response.setContentType(file.getFileMetadata().getContentType());
      // response.setHeader("Content-Disposition", "attachment;
      // filename=\"test.pdf\"");
      InputStream inputStream = new FileInputStream(new java.io.File(file.getPath()));
      int nRead;
      while ((nRead = inputStream.read()) != -1) {
        response.getWriter().write(nRead);
      }
    }
  }
  
  @GetMapping("/users/files/{dirPath1}/{dirPath2}/{id:[0-9]+}")
  public void show(@PathVariable String dirPath1, @PathVariable String dirPath2, @PathVariable long id,HttpServletResponse response) throws IOException {
    String dirPath = dirPath1 + "/" + dirPath2 + "/";
    File file = fileService.findByDirPathAndId(dirPath, id);
    if (file != null) {
      response.setContentType(file.getFileMetadata().getContentType());
      // response.setHeader("Content-Disposition", "attachment;
      // filename=\"test.pdf\"");
      InputStream inputStream = new FileInputStream(new java.io.File(file.getPath()));
      int nRead;
      while ((nRead = inputStream.read()) != -1) {
        response.getWriter().write(nRead);
      }
    }
  }
}
