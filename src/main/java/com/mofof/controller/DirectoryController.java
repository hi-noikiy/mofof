package com.mofof.controller;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mofof.entity.file.DirMetadata;
import com.mofof.entity.file.Directory;
import com.mofof.entity.file.File;
import com.mofof.entity.system.User;
import com.mofof.entity.system.UserAccount;
import com.mofof.service.DirMetadataService;
import com.mofof.service.DirectoryService;
import com.mofof.service.FileService;
import com.mofof.service.UserAccountService;
import com.mofof.util.GsonBuilderFactory;

@RestController
public class DirectoryController {

  @Autowired
  DirectoryService directoryService;
  @Autowired
  UserAccountService userAccountService;
  @Autowired
  FileService fileService;
  @Autowired
  DirMetadataService dirMetadataService;

  @GetMapping("/users/directories")
  public String index() {
    Session session = SecurityUtils.getSubject().getSession();
    long id = (long) session.getAttribute("userAccountId");
    UserAccount userAccount = userAccountService.findById(id);
    User user = userAccount.getUser();
    
    Directory directory = directoryService.find(user.getId());
    Directory dir = new Directory();
    
    List<Directory> dirChildren = directory.getChildren();
    Iterator<Directory> iterator = dirChildren.iterator();
    while (iterator.hasNext()) {
      Directory d = iterator.next();
      if(Directory.TRASH_BIN.equals(d.getName())) {
        dir = d;
        iterator.remove();
      }
    } 
    dirChildren.add(dir);   
    
    return GsonBuilderFactory.createGsonBuilder(Arrays.asList("createTime", "creator", "user", "parent", "files", "directory"))
        .toJson(directory);
  }
  
  @PostMapping("/users/directories")
  public String createDirectory(@RequestBody Directory directory) {
    Session session = SecurityUtils.getSubject().getSession();
    long id = (long) session.getAttribute("userAccountId");
    UserAccount userAccount = userAccountService.findById(id);
    User user = userAccount.getUser();

    Directory dir = directoryService.findByName(directory.getName());
    //创建文件夹时parentid为null时不创建
    if (directory.getParent() != null) {
      long parentId = directory.getParent().getId();
      Directory parentDirectory = directoryService.findByParentId(parentId);
      if (dir == null && parentDirectory != null) {
        directory.setUser(user); 
        dir = directoryService.save(directory);
      }
    }
    return GsonBuilderFactory.createGsonBuilder(Arrays.asList("createTime", "creator", "user", "parent", "files", "directory"))
        .toJson(dir);
  }


  @PostMapping("/users/directories/{parentId}")
  public String create(@PathVariable long parentId, @RequestBody Directory directory) {
    Session session = SecurityUtils.getSubject().getSession();
    long id = (long) session.getAttribute("userAccountId");
    UserAccount userAccount = userAccountService.findById(id);
    User user = userAccount.getUser();
    //在一个文件夹下不能创建同名文件夹
    Directory dir = directoryService.findByParentIdAndName(parentId, directory.getName());
    if (dir == null) {     
      Directory parentDir = directoryService.findById(parentId);
      if (parentDir != null) {
        DirMetadata dirMetadata = directory.getDirMetadata();
        if (dirMetadata == null) {
          dirMetadata = new DirMetadata();
        }
        
        directory.setUser(user);
        directory.setParent(parentDir);
        directory.setDirMetadata(null);
        dir = directoryService.save(directory);
        dirMetadata.setDirectory(dir);
        dirMetadata = dirMetadataService.save(dirMetadata);        

//        directory.setUser(user);
//        directory.setParent(parentDir);
//        dir = directoryService.save(directory);
        dir.setDirMetadata(dirMetadataService.findByDirectoryId(dir.getId()));
 
      }     
    }
    
    return GsonBuilderFactory.createGsonBuilder(Arrays.asList("createTime", "creator", "user", "parent", "files", "directory"))
        .toJson(dir);

  }

  @PutMapping("/users/directories/{id}")
  public String update(@PathVariable long id, @RequestBody Directory directory) {
    Directory dir = directoryService.findById(id);
    dir.setName(directory.getName());
    return GsonBuilderFactory.createGsonBuilder(Arrays.asList("createTime", "creator", "user", "parent", "files", "directory"))
        .toJson(directoryService.save(dir));
  }

  @DeleteMapping("/users/directories/{id}")
  public long delete(@PathVariable long id) {
    directoryService.delete(id);
    return id;
  }

  @PostMapping("/users/directories/{parentId}/to/{newDirParentId}")
  public String move(@PathVariable long parentId, @PathVariable long newDirParentId) {
    Directory dir = directoryService.findByParentId(parentId);
    Directory directory = directoryService.findById(newDirParentId);
    if (directory != null) {
      dir.setParent(directory);
    }
    return GsonBuilderFactory.createGsonBuilder(Arrays.asList("createTime", "creator", "user", "parent", "files", "directory"))
        .toJson(directoryService.save(dir));
  }
  //移动文件夹
  @PostMapping("/users/directories/mv/{newDirParentId}")
  public String move(@PathVariable long newDirParentId, @RequestBody Directory directory) {
    Directory dir = directoryService.findById(directory.getId());
    Directory parentDirectory = directoryService.findById(newDirParentId);
    if (parentDirectory != null) {
      dir.setParent(parentDirectory);
    }
    return GsonBuilderFactory.createGsonBuilder(Arrays.asList("createTime", "creator", "user", "parent", "files", "directory"))
        .toJson(directoryService.save(dir));
  }

  @GetMapping("/users/directories/{dirId}/files")
  public String indexFiles(@PathVariable long dirId) {
    Set<File> files = fileService.findByDirectoryId(dirId);
    return GsonBuilderFactory.createGsonBuilder(Arrays.asList("createTime", "creator", "user", "parent", "files", "directory"))
        .toJson(files);
  }
  
  //拷贝文件夹 目标位置新建一个文件夹， 拷贝文件夹内的子文件夹
  @GetMapping("/users/directories/{id}/cp/{parentId}")
  public String copy(@PathVariable long id, @PathVariable long parentId) {
    Directory dir =  new Directory();
    Directory directory = directoryService.findById(id);
    BeanUtils.copyProperties(directory, dir);
    DirMetadata dirM = dir.getDirMetadata();
    dir.setCreateTime(null);
    dir.setId(null);
    dir.setChildren(null);
    Directory parentDirectory = directoryService.findById(parentId);
    dir.setParent(parentDirectory);   
    dir.setDirMetadata(null);
    dir = directoryService.save(dir);
        
    DirMetadata dirMetadata = new DirMetadata();
    BeanUtils.copyProperties(dirM, dirMetadata, new String[] {"id"});
    if (dirMetadata != null) {     
      dirMetadata.setCreateTime(null);
      dirMetadata.setDirectory(dir);    
      dirMetadata = dirMetadataService.save(dirMetadata);
    }     
    cpDir(directory, dir);
       
    return GsonBuilderFactory.createGsonBuilder(Arrays.asList("createTime", "creator", "user", "parent", "files", "directory"))
        .toJson(dir);
  }
  
  public void cpDir(Directory oldDir, Directory newDir) {
    List<Directory> children = oldDir.getChildren();
    if(children != null) {
      for (Directory directory : children) {
        Directory dir =  new Directory();
        BeanUtils.copyProperties(directory, dir, new String[] {"id", "parent_id"});
        dir.setCreateTime(null);
        dir.setId(null);
        dir.setChildren(null);
        dir.setParent(newDir);
        dir = directoryService.save(dir);
        
        DirMetadata dirMetadata = dir.getDirMetadata();
        if (dirMetadata != null) {
          dirMetadata.setCreateTime(null);
          dirMetadata.setDirectory(dir);
          dirMetadata = dirMetadataService.save(dirMetadata);
        }            
        cpDir(directory, dir);
      }
    }else {
      return;
    }
  } 
}
