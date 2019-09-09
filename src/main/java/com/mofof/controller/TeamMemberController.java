package com.mofof.controller;

import com.mofof.entity.administrator.*;
import com.mofof.service.FileSystemStorageService;
import com.mofof.service.StorageProperties;
import com.mofof.service.StorageService;
import com.mofof.service.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Qian, Wenliang on 17/5/27.
 */
@RestController
@RequestMapping(path = "/member")
@EnableConfigurationProperties(StorageProperties.class)
public class TeamMemberController {

    StorageService storageService = new FileSystemStorageService("upload/teamMember");

    @Autowired
    TeamMemberService teamMemberService;

    @PostMapping(path = "/new")
    public TeamMember newRecord(@RequestBody TeamMember member) {
        return teamMemberService.save(member);
    }

    @PostMapping(path = "/uploadMemberPic")
    public PhotoFile newRecord(@RequestParam("file") MultipartFile file, @RequestParam("fileName") String fileName, HttpServletRequest request) {
        PhotoFile photoFile = new PhotoFile();
        storageService.storeImage(file, fileName);
        photoFile.setMainPhoto(false);

        photoFile.setFileName(fileName);
        return photoFile;
    }

    //    @PostMapping(path="/uploadMemberPic")
//    public List<PhotoFile> newRecord(@RequestParam("file") MultipartFile[] files,@RequestParam("fileNames") String[] fileNames,HttpServletRequest request) {
//    	List<PhotoFile> photoFiles=new ArrayList<PhotoFile>();
//    	for(int i=0;i<files.length;i++){
//    		InputStream stream = new ByteArrayInputStream(files[i].getBytes(StandardCharsets.UTF_8));
//        	storageService.store(stream,fileNames[i]);
//        	PhotoFile photoFile = new PhotoFile();
//        	photoFile.setMainPhoto(true);//暂时只支持单图片
//        	photoFile.setFileName(fileNames[i]);
//        	photoFiles.add(photoFile);
//    	}
//    	return photoFiles;
//    }
//    
    @PostMapping(path = "/saveProfessionals")
    public Iterable<Professional> saveProfessionals(@RequestBody Professional[] professionals) {
        return teamMemberService.saveProfessionals(professionals);
    }

    @PostMapping(path = "/saveEducations")
    public Iterable<Education> saveEducations(@RequestBody Education[] educations) {
        return teamMemberService.saveEducations(educations);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @GetMapping(path = "/all")
    public Iterable<TeamMember> allMembers() {
        return teamMemberService.findAll();
    }

    @GetMapping(path = "/member")
    public TeamMember getMember(Long id) {
        return teamMemberService.findById(id);
    }

    @GetMapping(path = "/contact")
    public Contact getContact(Long id) {
        return teamMemberService.findContactById(id);
    }

    @PostMapping(path = "/newContact")
    public Contact newRecord(@RequestBody Contact contact) {
        return teamMemberService.saveContact(contact);
    }

    @GetMapping(path = "/professional")
    public Iterable<Professional> getProfessional(Long id) {
        return teamMemberService.findProfessionalById(id);
    }

    @GetMapping(path = "/education")
    public Iterable<Education> getEducation(Long id) {
        return teamMemberService.findEducationById(id);
    }

    @PostMapping(path = "/updateDesc")
    public TeamMember updateDescription(Long id, String description) {
        return teamMemberService.updateDescription(id, description);
    }

    @GetMapping(path = "/agency")
    public Iterable<AdministratorTeam> getAgency(Long id) {
        return teamMemberService.findAgencyById(id);
    }
}
