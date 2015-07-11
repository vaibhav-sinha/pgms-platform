package com.pgms.service.impl;

import com.pgms.service.api.DepartmentService;
import com.pgms.service.entity.DepartmentEntity;
import com.pgms.service.repository.DepartmentRepository;
import com.pgms.shared.model.Department;
import com.pgms.shared.model.EntryStatus;
import com.pgms.shared.util.Mapper;
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

    @Autowired
    Mapper mapper;

    @Override
    public Department saveDepartment(Department department) {
        DepartmentEntity departmentEntity = mapper.map(department, DepartmentEntity.class);
        return mapper.map(departmentRepository.save(departmentEntity), Department.class);
    }

    @Override
    public Department getDepartment(Long id) {
        return mapper.map(departmentRepository.findOne(id), Department.class);
    }

    @Override
    public List<Department> getAllDepartments() {
        return mapper.mapAsList(departmentRepository.findAll(), Department.class);
    }

    @Override
    public List<Department> getAllActiveDepartments() {
        return mapper.mapAsList(departmentRepository.findByEntryStatus(EntryStatus.ACTIVE), Department.class);
    }
}
