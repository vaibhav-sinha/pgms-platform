package com.pgms.admin.controller;

import com.pgms.service.api.DesignationService;
import com.pgms.shared.model.Designation;
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
@RequestMapping("/designation")
public class DesignationController {

    @Autowired
    DesignationService designationService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public PgmsResponse<Designation> saveDesignation(@RequestBody Designation designation) {
        PgmsResponse<Designation> pgmsResponse = new PgmsResponse<>();
        if(designation == null || designation.getId() != null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to save designation. Either designation is null or designation id is not null");
            return pgmsResponse;
        }
        try {
            designation.setEntryStatus(EntryStatus.ACTIVE);
            designation = designationService.saveDesignation(designation);
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully saved designation with ID " + designation.getId());
            pgmsResponse.setData(designation);
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public PgmsResponse<Designation> updateDesignation(@RequestBody Designation designation) {
        PgmsResponse<Designation> pgmsResponse = new PgmsResponse<>();
        if(designation == null || designation.getId() == null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to update designation. Either designation or designation id is null");
            return pgmsResponse;
        }
        try {
            designationService.saveDesignation(designation);
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully updated designation with ID " + designation.getId());
            pgmsResponse.setData(designation);
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public PgmsResponse<Designation> deleteDesignation(@RequestBody Designation designation) {
        PgmsResponse<Designation> pgmsResponse = new PgmsResponse<>();
        if(designation == null || designation.getId() == null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to delete designation. Either designation or designation id is null");
            return pgmsResponse;
        }
        designation.setEntryStatus(EntryStatus.DELETED);
        return updateDesignation(designation);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public PgmsResponse<List<Designation>> getAllDesignation() {
        PgmsResponse<List<Designation>> pgmsResponse = new PgmsResponse<>();
        try {
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully got all categories");
            pgmsResponse.setData(designationService.getAllDesignation());
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @RequestMapping(value = "/getAllActive", method = RequestMethod.GET)
    public PgmsResponse<List<Designation>> getAllActiveDesignation() {
        PgmsResponse<List<Designation>> pgmsResponse = new PgmsResponse<>();
        try {
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully got all categories");
            pgmsResponse.setData(designationService.getAllActiveDesignation());
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }
}

