package com.mofof.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    void store(MultipartFile file);

    void newFolder(String folder);

    void removeFile(String folder);

    void renameFolder(String source, String folder);

    void moveFolder(String source, String target);

    void store(MultipartFile file, String fileName);

    void store(InputStream file, String fileName);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

    boolean fileExists(String dir);

    void removeDirectory(String folder);

	void storeImage(MultipartFile file, String fileName);

}
