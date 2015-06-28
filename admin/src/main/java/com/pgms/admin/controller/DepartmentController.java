package com.pgms.admin.controller;

import com.pgms.service.api.DepartmentService;
import com.pgms.shared.model.Department;
import com.pgms.shared.model.EntryStatus;
import com.pgms.shared.pojo.PgmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by user-1 on 28/6/15.
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public PgmsResponse<Department> saveDepartment(@RequestBody Department department) {
        PgmsResponse<Department> pgmsResponse = new PgmsResponse<>();
        if(department == null || department.getId() != null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to save department. Either department is null or department id is not null");
            return pgmsResponse;
        }
        try {
            department.setEntryStatus(EntryStatus.ACTIVE);
            department = departmentService.saveDepartment(department);
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully saved department with ID " + department.getId());
            pgmsResponse.setData(department);
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public PgmsResponse<Department> updateDepartment(@RequestBody Department department) {
        PgmsResponse<Department> pgmsResponse = new PgmsResponse<>();
        if(department == null || department.getId() == null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to update department. Either department or department id is null");
            return pgmsResponse;
        }
        try {
            departmentService.saveDepartment(department);
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully updated department with ID " + department.getId());
            pgmsResponse.setData(department);
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public PgmsResponse<Department> deleteDepartment(@RequestBody Department department) {
        PgmsResponse<Department> pgmsResponse = new PgmsResponse<>();
        if(department == null || department.getId() == null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to delete department. Either department or department id is null");
            return pgmsResponse;
        }
        department.setEntryStatus(EntryStatus.DELETED);
        return updateDepartment(department);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public PgmsResponse<List<Department>> getAllDepartment() {
        PgmsResponse<List<Department>> pgmsResponse = new PgmsResponse<>();
        try {
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully got all categories");
            pgmsResponse.setData(departmentService.getAllDepartments());
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @RequestMapping(value = "/getAllActive", method = RequestMethod.GET)
    public PgmsResponse<List<Department>> getAllActiveDepartment() {
        PgmsResponse<List<Department>> pgmsResponse = new PgmsResponse<>();
        try {
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully got all categories");
            pgmsResponse.setData(departmentService.getAllActiveDepartments());
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }
}

