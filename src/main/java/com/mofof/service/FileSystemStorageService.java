package com.mofof.service;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.mofof.util.ImageUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    public FileSystemStorageService(String path) {
        this.rootLocation = Paths.get(path);
    }

    @Override
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    @Override
    public void store(MultipartFile file, String fileName) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            	Files.copy(file.getInputStream(), this.rootLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }
    
    @Override
    public void storeImage(MultipartFile file, String fileName) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            Files.copy(file.getInputStream(), this.rootLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            
            ImageUtils.resizeImage(this.rootLocation.resolve(fileName).toFile(),this.rootLocation.resolve("icon_"+fileName).toFile() , 400,180);
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    @Override
    public void store(InputStream file, String fileName) {
        try {
            Files.copy(file, this.rootLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + fileName, e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        	createDirectory("upload/file/personal");
        	createDirectory("upload/administrator");
        	createDirectory("upload/teamMember");
    }
    
    public void createDirectory(String directory){
    	if(!Files.exists(Paths.get(directory))){
    		try {
                Files.createDirectory(Paths.get(directory));
            } catch (IOException e) {
                throw new StorageException("Could not initialize storage", e);
            }
    	}
    }

    @Override
    public boolean fileExists(String dir) {
        Path file = load(dir);
        if (Files.exists(file)) {
            return true;
        }
        return false;
    }

    @Override
    public void newFolder(String folder) {
        try {
            Files.createDirectories(this.rootLocation.resolve(folder));
        } catch (IOException e) {
            throw new StorageException("Failed to create file " + folder, e);
        }
    }

    @Override
    public void removeFile(String folder) {
        try {
            Files.delete(this.rootLocation.resolve(folder));
        } catch (IOException e) {
            throw new StorageException("Failed to remove folder " + folder, e);
        }
    }

    @Override
    public void removeDirectory(String folder) {
        try {
            FileUtils.deleteDirectory(this.rootLocation.resolve(folder).toFile());
        } catch (IOException e) {
            throw new StorageException("Failed to remove folder " + folder, e);
        }
    }

    @Override
    public void renameFolder(String source, String folder) {
        try {
            Path sourcePath = this.rootLocation.resolve(source);
            Files.move(sourcePath, sourcePath.resolveSibling(folder));
        } catch (IOException e) {
            throw new StorageException("Failed to rename folder " + folder, e);
        }
    }

    @Override
    public void moveFolder(String source, String target) {
        try {
            Path sourcePath = Paths.get(source);
            Path targetPath = Paths.get(target);
            Files.move(sourcePath, targetPath);
        } catch (IOException e) {
            throw new StorageException("Failed to moveFolder folder " + source, e);
        }
    }
}
