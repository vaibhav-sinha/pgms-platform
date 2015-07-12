package com.pgms.admin.controller;

import com.pgms.service.api.VerificationStatusService;
import com.pgms.shared.model.VerificationStatus;
import com.pgms.shared.model.EntryStatus;
import com.pgms.shared.pojo.PgmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by user-1 on 28/6/15.
 */
@RestController
@RequestMapping("/api/verificationStatus")
public class VerificationStatusController {

    @Autowired
    VerificationStatusService verificationStatusService;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public PgmsResponse<VerificationStatus> getVerificationStatus(@PathVariable Long id) {
        PgmsResponse<VerificationStatus> pgmsResponse = new PgmsResponse<>();
        if(id == null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to get verificationStatus. VerificationStatus id is null");
            return pgmsResponse;
        }
        try {
            VerificationStatus verificationStatus = verificationStatusService.getVerificationStatus(id);
            if(verificationStatus == null) {
                pgmsResponse.setSuccess(false);
                pgmsResponse.setMessage("Failed to get verificationStatus. VerificationStatus with id = " + id + " does not exist");
            }
            else {
                pgmsResponse.setData(verificationStatus);
                pgmsResponse.setSuccess(true);
                pgmsResponse.setMessage("Successfully got verificationStatus with ID " + id);
            }
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public PgmsResponse<VerificationStatus> saveVerificationStatus(@RequestBody VerificationStatus verificationStatus) {
        PgmsResponse<VerificationStatus> pgmsResponse = new PgmsResponse<>();
        if(verificationStatus == null || verificationStatus.getId() != null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to save verificationStatus. Either verificationStatus is null or verificationStatus id is not null");
            return pgmsResponse;
        }
        try {
            verificationStatus.setEntryStatus(EntryStatus.ACTIVE);
            verificationStatus = verificationStatusService.saveVerificationStatus(verificationStatus);
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully saved verificationStatus with ID " + verificationStatus.getId());
            pgmsResponse.setData(verificationStatus);
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public PgmsResponse<VerificationStatus> updateVerificationStatus(@RequestBody VerificationStatus verificationStatus) {
        PgmsResponse<VerificationStatus> pgmsResponse = new PgmsResponse<>();
        if(verificationStatus == null || verificationStatus.getId() == null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to update verificationStatus. Either verificationStatus or verificationStatus id is null");
            return pgmsResponse;
        }
        try {
            if(verificationStatus.getEntryStatus() == null) {
                verificationStatus.setEntryStatus(EntryStatus.ACTIVE);
            }
            verificationStatusService.saveVerificationStatus(verificationStatus);
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully updated verificationStatus with ID " + verificationStatus.getId());
            pgmsResponse.setData(verificationStatus);
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public PgmsResponse<VerificationStatus> deleteVerificationStatus(@RequestBody VerificationStatus verificationStatus) {
        PgmsResponse<VerificationStatus> pgmsResponse = new PgmsResponse<>();
        if(verificationStatus == null || verificationStatus.getId() == null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to delete verificationStatus. Either verificationStatus or verificationStatus id is null");
            return pgmsResponse;
        }
        verificationStatus = verificationStatusService.getVerificationStatus(verificationStatus.getId());
        verificationStatus.setEntryStatus(EntryStatus.DELETED);
        return updateVerificationStatus(verificationStatus);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public PgmsResponse<List<VerificationStatus>> getAllVerificationStatus() {
        PgmsResponse<List<VerificationStatus>> pgmsResponse = new PgmsResponse<>();
        try {
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully got all categories");
            pgmsResponse.setData(verificationStatusService.getAllVerificationStatus());
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @RequestMapping(value = "/getAllActive", method = RequestMethod.GET)
    public PgmsResponse<List<VerificationStatus>> getAllActiveVerificationStatus() {
        PgmsResponse<List<VerificationStatus>> pgmsResponse = new PgmsResponse<>();
        try {
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully got all categories");
            pgmsResponse.setData(verificationStatusService.getAllActiveVerificationStatus());
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

}
