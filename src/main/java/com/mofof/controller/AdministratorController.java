package com.mofof.controller;

import com.mofof.entity.administrator.Administrator;
import com.mofof.entity.administrator.AdministratorTeam;
import com.mofof.entity.administrator.PhotoFile;
import com.mofof.entity.administrator.TeamMemberWithAgency;
import com.mofof.entity.fund.Fund;
import com.mofof.service.AdministratorService;
import com.mofof.service.FileSystemStorageService;
import com.mofof.service.StorageService;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ChenErliang on 17/6/6.
 */
@RestController
@RequestMapping(path = "/administrator")
public class AdministratorController {
    private static final Logger log = LoggerFactory.getLogger(AdministratorController.class);

    @Autowired
    AdministratorService administratorService;

    StorageService storageService = new FileSystemStorageService("upload/administrator");
    
    @PostMapping(path = "/save")
    public Administrator saveRecord(@RequestBody Administrator administrator) {
        return administratorService.save(administrator);
    }

    @GetMapping(path = "/all")
    public Iterable<Administrator> allAdministrators() {
        log.info("======> all me");
        Iterable<Administrator> r = administratorService.findAll();
        for(Administrator a : r) {
            log.info("admin => {}", a);
        }
        return administratorService.findAll();
    }

    @GetMapping(path = "/administrator")
    public Administrator getAdministrator(Long id) {
        return administratorService.findById(id);
    }

    @GetMapping(path = "/teams")
    public Iterable<AdministratorTeam> findTeamsByAdminId(Long id) {
        return administratorService.findTeamsByAdminId(id);
    }

    @GetMapping(path = "/team")
    public AdministratorTeam findTeamById(Long id) {
        return administratorService.findTeamById(id);
    }

    @PostMapping(path = "/saveTeams")
    public void saveAdminTeams(@RequestBody AdministratorTeam[][] administratorTeams) {
        administratorService.saveAdministratorTeams(administratorTeams);
    }
    
    // @PostMapping(path = "/saveEditTeams")
    // public void saveAdminTeams(@RequestBody AdministratorTeam[] editTeams) {
    //     administratorService.saveEditTeams(editTeams);
    // }

    @GetMapping(path = "/teamsWithAgency")
    public Iterable<TeamMemberWithAgency> findTeamsNotLinkedToAdminId(Long id) {
        return administratorService.findTeamsNotLinkedToAdminId(id);
    }

    @PostMapping(path = "/addTeams")
    public Long addAdminTeams(@RequestBody AdministratorTeam[] administratorTeams) {
        return administratorService.addAdministratorTeams(administratorTeams);
    }

    @GetMapping(path = "/groups")
    public Iterable<Fund> getFundByAdministratorId(Long id) {
        return administratorService.getFundByAdministratorId(id);
    }

    @GetMapping(path = "/searchGroupByFundName")
    public Iterable<Fund> getFundByFundName(String name) {
        return administratorService.getFundByFundName(name);
    }

    @GetMapping(path = "/funds")
    public Iterable<Fund> getFundNotLinkedToAnyAdmin() {
        return administratorService.getFundNotLinkedToAnyAdmin();
    }

    @PostMapping(path = "/addGroups")
    public Long addAdminGroups(@RequestBody Fund[] funds) {
        return administratorService.addAdministratorGroups(funds);
    }

    @GetMapping(path = "/delinkGroup")
    public Long delinkAdminGroup(Long id) {
        return administratorService.delinkAdminGroup(id);
    }
    
    @GetMapping(path = "/delinkTeam")
    public void delinkAdminTeam(Long id) {
        administratorService.delinkAdminTeam(id);
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
    
    @PostMapping(path = "/uploadAdminPic")
    public PhotoFile newRecord(@RequestParam("file") MultipartFile file, @RequestParam("fileName") String fileName, HttpServletRequest request) {
        PhotoFile photoFile = new PhotoFile();
        storageService.storeImage(file, fileName);
        photoFile.setMainPhoto(false);

        photoFile.setFileName(fileName);
        return photoFile;
    }
}
