package com.pgms.admin.controller;

import com.pgms.admin.utils.AdminUtils;
import com.pgms.service.api.OfficerService;
import com.pgms.shared.model.*;
import com.pgms.shared.model.Officer;
import com.pgms.shared.pojo.OfficerCreateRequest;
import com.pgms.shared.pojo.OfficerVO;
import com.pgms.shared.pojo.PgmsResponse;
import com.pgms.shared.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by user-1 on 28/6/15.
 */
@RestController
@RequestMapping("/api/officer")
public class OfficerController {

    @Autowired
    OfficerService officerService;

    @Autowired
    Mapper mapper;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public PgmsResponse<Officer> getOfficer(@PathVariable Long id) {
        PgmsResponse<Officer> pgmsResponse = new PgmsResponse<>();
        if(id == null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to get officer. Officer id is null");
            return pgmsResponse;
        }
        try {
            Officer officer = officerService.getOfficer(id);
            if(officer == null) {
                pgmsResponse.setSuccess(false);
                pgmsResponse.setMessage("Failed to get officer. Officer with id = " + id + " does not exist");
            }
            else {
                pgmsResponse.setData(officer);
                pgmsResponse.setSuccess(true);
                pgmsResponse.setMessage("Successfully got officer with ID " + id);
            }
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public PgmsResponse<OfficerVO> saveOfficer(@RequestBody OfficerCreateRequest officer) {
        PgmsResponse<OfficerVO> pgmsResponse = new PgmsResponse<>();
        Officer savedOfficer;
        if(officer == null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to save officer. Officer is null");
            return pgmsResponse;
        }
        Officer existing = officerService.findOfficerByUsername(officer.getUsername());
        if(existing != null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to save officer. Officer with this username already exists");
            return pgmsResponse;
        }
        Officer newOfficer = mapper.map(officer, Officer.class);
        newOfficer.setEntryStatus(EntryStatus.ACTIVE);
        newOfficer.setAccountStatus(AccountStatus.ACTIVE);
        String salt = AdminUtils.generateSalt();
        try {
            String encryptedPassword = AdminUtils.encrypt(salt, officer.getPassword());
            newOfficer.setSalt(salt);
            newOfficer.setEncryptedPassword(encryptedPassword);
            savedOfficer = officerService.saveOfficer(newOfficer);
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully saved officer with ID " + savedOfficer.getId());
            OfficerVO officerVO = mapper.map(savedOfficer, OfficerVO.class);
            pgmsResponse.setData(officerVO);
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
            if(officer.getEntryStatus() == null) {
                officer.setEntryStatus(EntryStatus.ACTIVE);
            }
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
        officer = officerService.getOfficer(officer.getId());
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

