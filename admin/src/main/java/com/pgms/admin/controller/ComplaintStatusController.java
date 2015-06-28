package com.pgms.admin.controller;

import com.pgms.service.api.ComplaintStatusService;
import com.pgms.shared.model.ComplaintStatus;
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
@RequestMapping("/complaintStatus")
public class ComplaintStatusController {

    @Autowired
    ComplaintStatusService complaintStatusService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public PgmsResponse<ComplaintStatus> saveComplaintStatus(@RequestBody ComplaintStatus complaintStatus) {
        PgmsResponse<ComplaintStatus> pgmsResponse = new PgmsResponse<>();
        if(complaintStatus == null || complaintStatus.getId() != null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to save complaintStatus. Either complaintStatus is null or complaintStatus id is not null");
            return pgmsResponse;
        }
        try {
            complaintStatus.setEntryStatus(EntryStatus.ACTIVE);
            complaintStatus = complaintStatusService.saveComplaintStatus(complaintStatus);
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully saved complaintStatus with ID " + complaintStatus.getId());
            pgmsResponse.setData(complaintStatus);
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public PgmsResponse<ComplaintStatus> updateComplaintStatus(@RequestBody ComplaintStatus complaintStatus) {
        PgmsResponse<ComplaintStatus> pgmsResponse = new PgmsResponse<>();
        if(complaintStatus == null || complaintStatus.getId() == null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to update complaintStatus. Either complaintStatus or complaintStatus id is null");
            return pgmsResponse;
        }
        try {
            complaintStatusService.saveComplaintStatus(complaintStatus);
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully updated complaintStatus with ID " + complaintStatus.getId());
            pgmsResponse.setData(complaintStatus);
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public PgmsResponse<ComplaintStatus> deleteComplaintStatus(@RequestBody ComplaintStatus complaintStatus) {
        PgmsResponse<ComplaintStatus> pgmsResponse = new PgmsResponse<>();
        if(complaintStatus == null || complaintStatus.getId() == null) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage("Failed to delete complaintStatus. Either complaintStatus or complaintStatus id is null");
            return pgmsResponse;
        }
        complaintStatus.setEntryStatus(EntryStatus.DELETED);
        return updateComplaintStatus(complaintStatus);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public PgmsResponse<List<ComplaintStatus>> getAllComplaintStatus() {
        PgmsResponse<List<ComplaintStatus>> pgmsResponse = new PgmsResponse<>();
        try {
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully got all categories");
            pgmsResponse.setData(complaintStatusService.getAllComplaintStatus());
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @RequestMapping(value = "/getAllActive", method = RequestMethod.GET)
    public PgmsResponse<List<ComplaintStatus>> getAllActiveComplaintStatus() {
        PgmsResponse<List<ComplaintStatus>> pgmsResponse = new PgmsResponse<>();
        try {
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully got all categories");
            pgmsResponse.setData(complaintStatusService.getAllActiveComplaintStatus());
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }
}