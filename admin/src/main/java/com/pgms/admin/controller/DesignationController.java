package com.pgms.admin.controller;

import com.pgms.service.api.DesignationService;
import com.pgms.shared.model.ComplaintStatus;
import com.pgms.shared.model.Designation;
import com.pgms.shared.model.Designation;
import com.pgms.shared.model.EntryStatus;
import com.pgms.shared.pojo.PgmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by user-1 on 28/6/15.
 */
@RestController
@RequestMapping("/api/designation")
public class DesignationController {

    @Autowired
    DesignationService designationService;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public PgmsResponse<Designation> getDesignation(@PathVariable Long id) {
        PgmsResponse<Designation> pgmsResponse = new PgmsResponse<>();
        if(id == null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to get designation. Designation id is null");
            return pgmsResponse;
        }
        try {
            Designation designation = designationService.getDesignation(id);
            if(designation == null) {
                pgmsResponse.setSuccess(false);
                pgmsResponse.setMessage("Failed to get designation. Designation with id = " + id + " does not exist");
            }
            else {
                pgmsResponse.setData(designation);
                pgmsResponse.setSuccess(true);
                pgmsResponse.setMessage("Successfully got designation with ID " + id);
            }
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

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
            if(designation.getEntryStatus() == null) {
                designation.setEntryStatus(EntryStatus.ACTIVE);
            }
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
        designation = designationService.getDesignation(designation.getId());
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

