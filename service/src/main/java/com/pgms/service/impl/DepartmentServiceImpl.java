package com.pgms.service.impl;

import com.pgms.service.api.DepartmentService;
import com.pgms.service.repository.DepartmentRepository;
import com.pgms.shared.model.Department;
import com.pgms.shared.model.EntryStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by user-1 on 28/6/15.
 */
@Component
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department getDepartment(Long id) {
        return departmentRepository.findOne(id);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public List<Department> getAllActiveDepartments() {
        return departmentRepository.findByEntryStatus(EntryStatus.ACTIVE);
    }
}
