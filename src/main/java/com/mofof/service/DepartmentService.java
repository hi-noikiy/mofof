package com.mofof.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mofof.entity.system.Department;
import com.mofof.repository.DepartmentRepository;
import com.mofof.repository.InvestorRepository;

/**
 * Created by Qian,Wenliang on 2017/8/16.
 */
@Service
@Transactional
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    InvestorRepository investorRepository;

    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    public List<Department> findAll() {
        return departmentRepository.findAllByParentIdIsNullOrderByIdDesc();
    }

    public Department findById(Long id) {
        return departmentRepository.findOne(id);
    }

    public Department deleteDepartment(Long id) {
        Department departmentCategory = departmentRepository.findOne(id);
        if (departmentCategory != null) {
          departmentRepository.delete(departmentCategory);
        }       
        return departmentCategory;
    }

    public Department renameDepartment(Long id, String name) {
        Department department = departmentRepository.findOne(id);
        department.setName(name);
        return departmentRepository.save(department);
    }

    public Department newDepartment(Long id) {
        Iterable<Department> departments = departmentRepository.findAllByOrderByIdDesc();
        String newDepartment = "新的部门";
        for (int i = 0; i < 10000; i++) {
            String fileName = newDepartment + (i == 0 ? "" : "(" + i + ")");
            boolean nameExists = false;
            for (Iterator<Department> it = departments.iterator(); it.hasNext(); ) {
                Department department = it.next();
                if (fileName.equals(department.getName())) {
                    nameExists = true;
                    break;
                }
            }
            if (!nameExists) {
                newDepartment = fileName;
                break;
            }
        }
        Department department = new Department();
        department.setName(newDepartment);
        if (id != null) {
            Department parent = departmentRepository.findOne(id);
            department.setParent(parent);
        }
        return departmentRepository.save(department);
    }

//    public List<Investor> getInvestors(Long id) {
//        List<Investor> investors = investorRepository.findAllByPlatformOrderByIdDesc(true);
//        Department department = departmentRepository.findOne(id);
//        if (department.getInvestors() != null && investors != null) {
//            for (Iterator<Investor> iIt = investors.iterator(); iIt.hasNext(); ) {
//                Investor investor = iIt.next();
//                for (Iterator<Investor> dIt = department.getInvestors().iterator(); dIt.hasNext(); ) {
//                    Investor dInvestor = dIt.next();
//                    if (investor.getId() == dInvestor.getId()) {
//                        investor.setManagedByDept(true);
//                        break;
//                    }
//                }
//            }
//        }
//        return investors;
//    }

}
