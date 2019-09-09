//package com.mofof.controller;
//
//import com.mofof.entity.file.DirectoryOld;
//import com.mofof.service.FileService;
//import com.mofof.service.StorageProperties;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.core.io.Resource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * Created by Qian, Wenliang on 17/7/27.
// */
//@RestController
//@RequestMapping(path = "/file")
//@EnableConfigurationProperties(StorageProperties.class)
//public class FileController {
//
//    @Autowired
//    FileService fileService;
//
////	@GetMapping(path="/personal/getDir")
////    public Directory getPersonalDir(String name){
////        return fileService.finePersonalDirectoryByName(name);
////    }
//
//    @PostMapping(path = "/personal/asyncGetDir")
//    public DirectoryOld getPersonalDirAsync(String name) {
//        return fileService.findPersonalDirectoryByName(name);
//    }
//
//    @GetMapping(path = "/personal/getDirById")
//    public DirectoryOld getPersonalDir(Long id) {
//        return fileService.getDirectoryById(id);
//    }
//
////	@GetMapping(path="/personal/newDir")
////    public Directory newPersonalDir(Long id){
////        return fileService.newDirectory(id);
////    }
//
//    @GetMapping(path = "/personal/asyncNewDir")
//    public DirectoryOld newPersonalDirAsync(Long id) {
//        return fileService.newPersonalDirAsync(id);
//    }
//
////	@GetMapping(path="/personal/removeDir")
////    public Directory removePersonalDir(Long id){
////        return fileService.removeDirectory(id);
////    }
//
//    @GetMapping(path = "/personal/asyncRemoveDir")
//    public DirectoryOld removePersonalDirAsync(Long id) {
//        return fileService.removeDirectoryAsync(id);
//    }
//
////	@GetMapping(path="/personal/renameDir")
////    public Directory renameDirectory(Long id,String name) {
////        return fileService.renameDirectory(id,name);
////    }
//
//    @GetMapping(path = "/personal/asyncRenameDir")
//    public DirectoryOld renameDirectoryAsync(Long id, String name) {
//        return fileService.renameDirectoryAsync(id, name);
//    }
//
//    @GetMapping(path = "/personal/moveDir")
//    public DirectoryOld moveDirectoryAsync(Long id, Long targetId) {
//        return fileService.moveDirectory(id, targetId);
//    }
//
//    @GetMapping(path = "/personal/removeFile")
//    public DirectoryOld removePersonalFile(Long id, Long parentId) {
//        return fileService.removeFile(id, parentId);
//    }
//
//    @GetMapping(path = "/personal/renameFile")
//    public DirectoryOld renameFile(Long id, Long parentId, String filename, String oldFileName, String filetype) {
//        return fileService.renameFile(id, parentId, filename, oldFileName, filetype);
//    }
//
//    @PostMapping(path = "/personal/uploadPersonalFile")
//    public DirectoryOld newRecord(@RequestParam("file") MultipartFile file, @RequestParam("parentId") Long parentId, HttpServletRequest request) {
//        return fileService.uploadFile(file, parentId);
//    }
//    
//    @PostMapping(path = "/personal/uploadPersonalFolder")
//    public DirectoryOld uploadPersonalFolder(@RequestParam("file") MultipartFile file, @RequestParam("parentId") Long parentId, HttpServletRequest request) {
//        return fileService.uploadFolder(file, parentId);
//    }
//
//    @GetMapping("/personal/files/{parentid}/{filename:.+}")
//    @ResponseBody
//    public ResponseEntity<Resource> serveFile(@PathVariable("parentid") Long parentId, @PathVariable("filename") String filename) {
//        Resource file = fileService.downloadFile(filename, parentId);
//        return ResponseEntity
//                .ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
//                .body(file);
//    }
//
//    @GetMapping("/personal/preview/{parentid}/{filename:.+}")
//    @ResponseBody
//    public ResponseEntity<Resource> previewFile(@PathVariable("parentid") Long parentId, @PathVariable("filename") String filename) {
//        Resource file = fileService.downloadFile(filename, parentId);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.parseMediaType("application/pdf"));
//        headers.setContentDispositionFormData(filename, filename);
////        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
//        ResponseEntity<Resource> response = new ResponseEntity<Resource>(file, headers, HttpStatus.OK);
//        return response;
////        return ResponseEntity
////                .ok()
////                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
////                .body(file);
//    }
//
//}
