package com.pgms.admin.controller;

import com.pgms.service.api.OfficerService;
import com.pgms.shared.model.Officer;
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
@RequestMapping("/officer")
public class OfficerController {

    @Autowired
    OfficerService officerService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public PgmsResponse<Officer> createOfficer(@RequestBody Officer officer) {
        PgmsResponse<Officer> pgmsResponse = new PgmsResponse<>();
        if(officer == null || officer.getId() != null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to create officer. Either officer is null or officer id is not null");
            return pgmsResponse;
        }
        try {
            officer = officerService.createOfficer(officer);
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully saved officer with ID " + officer.getId());
            pgmsResponse.setData(officer);
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public PgmsResponse<Officer> saveOfficer(@RequestBody Officer officer) {
        PgmsResponse<Officer> pgmsResponse = new PgmsResponse<>();
        if(officer == null || officer.getId() != null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to save officer. Either officer is null or officer id is not null");
            return pgmsResponse;
        }
        try {
            officer = officerService.saveOfficer(officer);
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully saved officer with ID " + officer.getId());
            pgmsResponse.setData(officer);
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public PgmsResponse<Officer> updateOfficer(@RequestBody Officer officer) {
        PgmsResponse<Officer> pgmsResponse = new PgmsResponse<>();
        if(officer == null || officer.getId() == null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to update officer. Either officer or officer id is null");
            return pgmsResponse;
        }
        try {
            officerService.saveOfficer(officer);
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully updated officer with ID " + officer.getId());
            pgmsResponse.setData(officer);
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public PgmsResponse<Officer> deleteOfficer(@RequestBody Officer officer) {
        PgmsResponse<Officer> pgmsResponse = new PgmsResponse<>();
        if(officer == null || officer.getId() == null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to delete officer. Either officer or officer id is null");
            return pgmsResponse;
        }
        officer.setEntryStatus(EntryStatus.DELETED);
        return updateOfficer(officer);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public PgmsResponse<List<Officer>> getAllOfficer() {
        PgmsResponse<List<Officer>> pgmsResponse = new PgmsResponse<>();
        try {
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully got all categories");
            pgmsResponse.setData(officerService.getAllOfficer());
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @RequestMapping(value = "/getAllActive", method = RequestMethod.GET)
    public PgmsResponse<List<Officer>> getAllActiveOfficer() {
        PgmsResponse<List<Officer>> pgmsResponse = new PgmsResponse<>();
        try {
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully got all categories");
            pgmsResponse.setData(officerService.getAllActiveOfficer());
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }
}

