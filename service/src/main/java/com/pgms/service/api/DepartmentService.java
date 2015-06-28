package com.pgms.service.api;

import com.pgms.shared.model.Department;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

/**
 * Created by user-1 on 27/6/15.
 */
public interface DepartmentService {

    Department saveDepartment(Department department);
    Department getDepartment(Long id);
    List<Department> getAllDepartments();
    List<Department> getAllActiveDepartments();
}
