//package com.mofof.service;
//
//import com.mofof.entity.file.DirectoryOld;
//import com.mofof.entity.file.FileOld;
//import com.mofof.repository.DirectoryRepository;
//import com.mofof.repository.FileRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.Iterator;
//
//@Service
//@Transactional
//public class FileServiceOld {
//    @Autowired
//    DirectoryRepository directoryRepository;
//    @Autowired
//    FileRepository fileRepository;
//
//    public DirectoryOld findPersonalDirectoryByName(String name) {
//        //测试代码，等用户模块完成后移除
//        DirectoryOld directory = directoryRepository.findByName("张三");
//        if (directory == null) {
//            directory = new DirectoryOld();
//            directory.setName("张三");
//            directory.setAuthority(1);
//            directory.setFileType(0);
//            String path = "upload/file/personal";
//            StorageService storageService = new FileSystemStorageService(path);
//            storageService.newFolder(directory.getName());
//            directoryRepository.save(directory);
//        }
//        return directoryRepository.findByName(name);
//    }
//
//    public DirectoryOld getDirectoryById(Long id) {
//        return directoryRepository.findOne(id);
//    }
//
//    public DirectoryOld findDirectoryById(Long id) {
//        return directoryRepository.findOne(id);
//    }
//
//    public FileOld findFileById(Long id) {
//        return fileRepository.findOne(id);
//    }
//
//    public DirectoryOld newDirectory(Long id) {
//        DirectoryOld parent = findDirectoryById(id);
//        String path = "upload/file/personal";
//        DirectoryOld rootDirectory = getRootDirectory(parent);
//        path = getDirectoryPath(path, parent);
//        StorageService storageService = new FileSystemStorageService(path);
//        String newFileName = "新建文件夹";
//        for (int i = 0; i < 10000; i++) {
//            String fileName = newFileName + (i == 0 ? "" : "(" + i + ")");
//            if (!storageService.fileExists(fileName)) {
//                storageService.newFolder(fileName);
//                newFileName = fileName;
//                break;
//            }
//        }
//        DirectoryOld directory = new DirectoryOld();
//        directory.setParent(parent);
//        directory.setName(newFileName);
//        directory.setFileType(0);
//        directory.setAuthority(1);
//        directoryRepository.save(directory);
//        parent.getSubDirectorys().add(directory);
//        return rootDirectory;
//    }
//
//    public DirectoryOld newPersonalDirAsync(Long id) {
//        DirectoryOld parent = findDirectoryById(id);
//        String path = "upload/file/personal";
//        path = getDirectoryPath(path, parent);
//        StorageService storageService = new FileSystemStorageService(path);
//        String newFileName = "新建文件夹";
//        for (int i = 0; i < 10000; i++) {
//            String fileName = newFileName + (i == 0 ? "" : "(" + i + ")");
//            if (!storageService.fileExists(fileName)) {
//                storageService.newFolder(fileName);
//                newFileName = fileName;
//                break;
//            }
//        }
//        DirectoryOld directory = new DirectoryOld();
//        directory.setParent(parent);
//        directory.setName(newFileName);
//        directory.setFileType(0);
//        directory.setAuthority(1);
//        return directoryRepository.save(directory);
////		parent.getSubDirectorys().add(directory);
////		return rootDirectory;
//    }
//
//    public DirectoryOld removeDirectory(Long id) {
//        DirectoryOld directory = findDirectoryById(id);
//        String path = "upload/file/personal";
//        DirectoryOld rootDirectory = directory;
//        if (directory.getParent() != null) {
//            path = getDirectoryPath(path, directory.getParent());
//            rootDirectory = getRootDirectory(directory.getParent());
//        }
//        StorageService storageService = new FileSystemStorageService(path);
//        storageService.removeDirectory(directory.getName());
//        for (Iterator<DirectoryOld> it = directory.getParent().getSubDirectorys().iterator(); it.hasNext(); ) {
//            if (it.next().getId() == id) {
//                it.remove();
//                break;
//            }
//        }
//        return rootDirectory;
//    }
//
//    public DirectoryOld removeDirectoryAsync(Long id) {
//        DirectoryOld directory = findDirectoryById(id);
//        String path = "upload/file/personal";
//        DirectoryOld rootDirectory = directory;
//        if (directory.getParent() != null) {
//            path = getDirectoryPath(path, directory.getParent());
////			rootDirectory = getRootDirectory(directory.getParent());
//        }
//        StorageService storageService = new FileSystemStorageService(path);
//        storageService.removeDirectory(directory.getName());
//        for (Iterator<DirectoryOld> it = directory.getParent().getSubDirectorys().iterator(); it.hasNext(); ) {
//            if (it.next().getId() == id) {
//                it.remove();
//                break;
//            }
//        }
//        directoryRepository.delete(directory);
//        return rootDirectory;
//    }
//
//    public DirectoryOld renameDirectory(Long id, String name) {
//        DirectoryOld dbDirectory = findDirectoryById(id);
//        String path = "upload/file/personal";
//        DirectoryOld rootDirectory = dbDirectory;
//        if (dbDirectory.getParent() != null) {
//            path = getDirectoryPath(path, dbDirectory.getParent());
//            rootDirectory = getRootDirectory(dbDirectory.getParent());
//        }
//        StorageService storageService = new FileSystemStorageService(path);
//        storageService.renameFolder(dbDirectory.getName(), name);
//        dbDirectory.setName(name);
//        directoryRepository.save(dbDirectory);
//        return rootDirectory;
//    }
//
//    public DirectoryOld renameDirectoryAsync(Long id, String name) {
//        DirectoryOld dbDirectory = findDirectoryById(id);
//        String path = "upload/file/personal";
////		Directory rootDirectory = dbDirectory;
//        if (dbDirectory.getParent() != null) {
//            path = getDirectoryPath(path, dbDirectory.getParent());
////			rootDirectory = getRootDirectory(dbDirectory.getParent());
//        }
//        StorageService storageService = new FileSystemStorageService(path);
//        storageService.renameFolder(dbDirectory.getName(), name);
//        dbDirectory.setName(name);
//        return directoryRepository.save(dbDirectory);
//    }
//
//    public DirectoryOld moveDirectory(Long id, Long targetId) {
//        DirectoryOld dbDirectory = findDirectoryById(id);
//        DirectoryOld targetDirectory = findDirectoryById(targetId);
//        String path = "upload/file/personal";
//        String targetPath = "upload/file/personal";
//        path = getDirectoryPath(path, dbDirectory);
//        targetPath = getDirectoryPath(targetPath, targetDirectory);
//        StorageService storageService = new FileSystemStorageService(path);
//        storageService.moveFolder(path, targetPath + "/" + dbDirectory.getName());
//        dbDirectory.setParent(targetDirectory);
//        return directoryRepository.save(dbDirectory);
//    }
//
//    public DirectoryOld removeFile(Long id, Long parentId) {
//        DirectoryOld directory = findDirectoryById(parentId);
//        FileOld file = findFileById(id);
//        String path = "upload/file/personal";
////		Directory rootDirectory = directory;
//        if (directory.getParent() != null) {
//            path = getDirectoryPath(path, directory.getParent());
////			rootDirectory = getRootDirectory(directory.getParent());
//        }
//        path = path + "/" + directory.getName();
//        StorageService storageService = new FileSystemStorageService(path);
//        storageService.removeFile(file.getFilename() + "." + file.getFiletype());
//        fileRepository.delete(file);
//        for (Iterator<FileOld> it = directory.getFiles().iterator(); it.hasNext(); ) {
//            if (it.next().getId() == id) {
//                it.remove();
//                break;
//            }
//        }
//        return directory;
//    }
//
//    public DirectoryOld renameFile(Long id, Long parentId, String newName, String oldName, String filetype) {
//        DirectoryOld directory = findDirectoryById(parentId);
//        FileOld file = findFileById(id);
//        String path = "upload/file/personal";
////		Directory rootDirectory = directory;
//        if (directory.getParent() != null) {
//            path = getDirectoryPath(path, directory.getParent());
////			rootDirectory = getRootDirectory(directory.getParent());
//        }
//        path = path + "/" + directory.getName();
//        StorageService storageService = new FileSystemStorageService(path);
//        storageService.renameFolder(oldName + "." + filetype, newName + "." + filetype);
//        directory.setName(directory.getName());
//        file.setFilename(newName);
//        fileRepository.save(file);
//        return directory;
//    }
//
//    public DirectoryOld uploadFile(MultipartFile file, Long parentId) {
//        DirectoryOld directory = findDirectoryById(parentId);
//        String path = "upload/file/personal";
////		Directory rootDirectory = directory;
//        if (directory.getParent() != null) {
//            path = getDirectoryPath(path, directory.getParent());
////			rootDirectory = getRootDirectory(directory.getParent());
//        }
//        path = path + "/" + directory.getName();
//        StorageService storageService = new FileSystemStorageService(path);
//        storageService.store(file);
//        FileOld mofofFile = new FileOld();
//        String originalFilename = file.getOriginalFilename();
//        String fileName = originalFilename.substring(0, originalFilename.lastIndexOf("."));
//        String fileType = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
//        mofofFile.setDirectory(directory);
//        mofofFile.setFilename(fileName);
//        mofofFile.setFilesize(file.getSize());
//        mofofFile.setFiletype(fileType);
//        fileRepository.save(mofofFile);
//        directory.getFiles().add(mofofFile);
//        return directory;
//    }
//    
//    public DirectoryOld uploadFolder(MultipartFile file, Long parentId) {
//        DirectoryOld directory = findDirectoryById(parentId);
//        String path = "upload/file/personal";
////		Directory rootDirectory = directory;
//        if (directory.getParent() != null) {
//            path = getDirectoryPath(path, directory.getParent());
////			rootDirectory = getRootDirectory(directory.getParent());
//        }
//        path = path + "/" + directory.getName();
//        DirectoryOld parent = directory;
//        String[] pathArray = file.getOriginalFilename().split("/");
//        for(int i=0;i<pathArray.length-1;i++){
//        	String dir = pathArray[i];
//        	StorageService storageService = new FileSystemStorageService(path);
//            if (!storageService.fileExists(dir)) {
//                storageService.newFolder(dir);
//                path = path + "/" + dir;
//                DirectoryOld newDirectory = new DirectoryOld();
//                newDirectory.setParent(parent);
//                newDirectory.setName(dir);
//                newDirectory.setFileType(0);
//                newDirectory.setAuthority(1);
//                DirectoryOld d = directoryRepository.save(newDirectory);
//                parent.getSubDirectorys().add(d);
//                parent = d;
//            }else{
//            	System.out.println(parent.getSubDirectorys().size());
//            	for(DirectoryOld subDirectory:parent.getSubDirectorys()){
//            		if(subDirectory.getName().equals(dir)){
//            			parent = subDirectory;
//            			path = path + "/" + dir;
//            			break;
//            		}
//            	}
//            }
//    	}
//        
//        FileOld mofofFile = new FileOld();
//        String originalFilename = pathArray[pathArray.length-1];
//        String fileName = originalFilename.substring(0, originalFilename.lastIndexOf("."));
//        String fileType = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
//        StorageService storageService = new FileSystemStorageService(path);
//        storageService.store(file,originalFilename);
//        mofofFile.setDirectory(parent);
//        mofofFile.setFilename(fileName);
//        mofofFile.setFilesize(file.getSize());
//        mofofFile.setFiletype(fileType);
//        fileRepository.save(mofofFile);
////        directory.getFiles().add(mofofFile);
//        return findDirectoryById(parentId);
//    }
//
//    public Resource downloadFile(String filename, Long parentId) {
//        DirectoryOld directory = findDirectoryById(parentId);
//        String path = "upload/file/personal";
//        if (directory.getParent() != null) {
//            path = getDirectoryPath(path, directory.getParent());
//        }
//        path = path + "/" + directory.getName();
//        StorageService storageService = new FileSystemStorageService(path);
//        return storageService.loadAsResource(filename);
//    }
//
//    private String getDirectoryPath(String path, DirectoryOld directory) {
//        if (directory.getParent() != null) {
//            path = getDirectoryPath(path, directory.getParent());
//        }
//        path = path + "/" + directory.getName();
//        return path;
//    }
//
//    private DirectoryOld getRootDirectory(DirectoryOld directory) {
//        DirectoryOld rootDirectory = null;
//        if (directory.getParent() == null) {
//            rootDirectory = directory;
//        } else {
//            getRootDirectory(directory.getParent());
//        }
//        return rootDirectory;
//    }
//}
