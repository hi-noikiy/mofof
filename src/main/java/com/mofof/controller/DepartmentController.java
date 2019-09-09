package com.mofof.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mofof.entity.platform.Platform;
import com.mofof.entity.relation.PlatformDepartmentRelation;
import com.mofof.entity.system.Department;
import com.mofof.repository.PlatformDepartmentRelationRepository;
import com.mofof.service.DepartmentService;
import com.mofof.service.PlatformService;
import com.mofof.service.UserService;
import com.mofof.util.GsonBuilderFactory;

/**
 * Created by Qian, Wenliang on 2017/8/17.
 */
@RestController
//@RequestMapping(path = "/department")
public class DepartmentController {

  @Autowired
  DepartmentService departmentService;
  @Autowired
  PlatformDepartmentRelationRepository pdrr;
  @Autowired
  PlatformService platformService;
  @Autowired
  UserService userService;

  @GetMapping(path = "/department/all")
  public String allDepartments() {
    List<Department> departments = departmentService.findAll();
    for (Department department : departments) {
      List<PlatformDepartmentRelation> pdrs = pdrr
          .findByDepartmentId(department.getId());
      Set<Platform> platforms = new HashSet<Platform>();
      for (PlatformDepartmentRelation pdr : pdrs) {
        platforms.add(pdr.getPlatform());
      }
      department.setPlatforms(platforms);
    }

    return GsonBuilderFactory.createGsonBuilder(
        Arrays.asList("createTime", "parent", "creator", "role", "departments",
            "managedDepartments", "userAccount", "calEvents"))
        .toJson(departments);
  }

  @PostMapping("/departments")
  public String subDepartment(@RequestBody Department department) {
    Department depart = department.getParent();
    if (depart != null) {
      Long id = depart.getId();
      Department dep = departmentService.findById(id);
      if (dep != null) {
        department.setParent(dep);
      }
    }

    departmentService.save(department);
    return GsonBuilderFactory.createGsonBuilder(
        Arrays.asList("createTime", "parent", "creator", "role", "departments",
            "managedDepartments", "userAccount", "calEvents"))
        .toJson(departmentService.save(department));
  }

  @PutMapping("/departments/{id}")
  public String update(@PathVariable long id, @RequestBody Department dep) {
    PlatformDepartmentRelation pdr;
    Department department = departmentService.findById(id);
    department.setPlatforms(null);
    department.setName(dep.getName());
    Set<Platform> platforms = dep.getPlatforms();
    //删除原来部门对应的投资平台
    pdrr.deleteByDepartmentId(id);
   
    for (Platform platform : platforms) {      
      pdr = new PlatformDepartmentRelation();
      platform = platformService.findOne(platform.getId());
      pdr.setDepartment(department);
      pdr.setPlatform(platform);
      pdrr.save(pdr);
    }
    
    return GsonBuilderFactory.createGsonBuilder(
        Arrays.asList("createTime", "parent", "creator", "role", "departments",
            "managedDepartments", "userAccount", "calEvents"))
        .toJson(departmentService.save(department));
  }

  @DeleteMapping("/departments/{id}")
  public String delete(@PathVariable long id) {
    Department department = departmentService.findById(id);
    department.setUser(null);
    department.setManagedByusers(null);
    departmentService.save(department);

    departmentService.deleteDepartment(id);
    return String.valueOf(id);
  }
  
  @PostMapping("/departments/{id}/move")
  public String move(@PathVariable long id, @RequestBody Department department) {
    
    Department parentDepartment = departmentService.findById(department.getId());
    if (parentDepartment != null) {
      Department dep = departmentService.findById(id);
      dep.setParent(parentDepartment);
      return GsonBuilderFactory.createGsonBuilder(
          Arrays.asList("createTime", "parent", "creator", "role", "departments",
              "managedDepartments", "userAccount", "calEvents"))
          .toJson(departmentService.save(dep));
    }
    return null;
  }
  
  @PostMapping("/departments/{id}/rename")
  public String rename(@PathVariable long id, @RequestBody Department department) {
    Department dep = departmentService.findById(id);
    dep.setName(department.getName());    
    return GsonBuilderFactory.createGsonBuilder(
        Arrays.asList("createTime", "parent", "creator", "role", "departments",
            "managedDepartments", "userAccount", "calEvents"))
        .toJson(departmentService.save(dep));
  }
  
  @GetMapping("/departments/{id}/platform")
  public String platforms(@PathVariable long id) {
    Department department = departmentService.findById(id);
    List<PlatformDepartmentRelation> pdrs = pdrr
        .findByDepartmentId(department.getId());
    Set<Platform> platforms = new HashSet<Platform>();
    for (PlatformDepartmentRelation pdr : pdrs) {
      platforms.add(pdr.getPlatform());
    }
    return GsonBuilderFactory.createGsonBuilder(
        Arrays.asList("createTime", "creator"))
        .toJson(platforms);
  }

  @PostMapping(path = "/save")
  public Department saveRecord(@RequestBody Department fund) {
    return departmentService.save(fund);
  }

//    @GetMapping(path = "/getInvestors")
//    public Iterable<Investor> getInvestors(Long id) {
//        return departmentService.getInvestors(id);
//    }

//    @PostMapping(path = "/departments")
//    public Iterable<Department> findAllDepartments() {
//        return departmentService.findAll();
//    }

  @GetMapping(path = "/newDepartment")
  public Department newDepartment(Long id) {
    return departmentService.newDepartment(id);
  }

  @GetMapping(path = "/deleteDepartment")
  public Department deleteDepartment(Long id) {
    return departmentService.deleteDepartment(id);
  }

  @GetMapping(path = "/renameDepartment")
  public Department renameDepartment(Long id, String name) {
    return departmentService.renameDepartment(id, name);
  }

}
