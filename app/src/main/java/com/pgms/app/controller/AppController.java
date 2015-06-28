package com.pgms.app.controller;

import com.pgms.service.api.ComplaintService;
import com.pgms.service.api.ComplaintStatusService;
import com.pgms.service.api.SubmitterService;
import com.pgms.shared.model.Complaint;
import com.pgms.shared.model.EntryStatus;
import com.pgms.shared.model.Submitter;
import com.pgms.shared.pojo.ComplaintSubmitRequest;
import com.pgms.shared.pojo.ComplaintSubmitResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by user-1 on 27/6/15.
 */
@Controller
public class AppController {

    @Autowired
    ComplaintService complaintService;

    @Autowired
    SubmitterService submitterService;

    @Autowired
    ComplaintStatusService complaintStatusService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getSubmitPage() {
        return "submit";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ComplaintSubmitResponse submitComplaint(@RequestBody ComplaintSubmitRequest complaintSubmitRequest) {
        Submitter submitter = submitterService.getSubmitterByEmailOrMobile(complaintSubmitRequest.getEmail(), complaintSubmitRequest.getMobile());
        if(submitter == null) {
            submitter = new Submitter();
        }
        submitter.setEmail(complaintSubmitRequest.getEmail());
        submitter.setMobile(complaintSubmitRequest.getMobile());
        submitter.setAddress(complaintSubmitRequest.getAddress());
        submitter.setName(complaintSubmitRequest.getName());
        submitter.setPincode(complaintSubmitRequest.getPincode());
        submitter = submitterService.saveSubmitter(submitter);

        Complaint complaint = new Complaint();
        complaint.setCategory(complaintSubmitRequest.getCategory());
        complaint.setDepartment(complaintSubmitRequest.getDepartment());
        complaint.setDescription(complaintSubmitRequest.getDescription());
        complaint.setLocation(complaintSubmitRequest.getLocation());
        complaint.setSubmitter(submitter);
        complaint.setUrgent(complaintSubmitRequest.getUrgent());
        complaint.setCreatedOn(new Date());
        complaint.setEntryStatus(EntryStatus.ACTIVE);
        complaint.setComplaintStatus(complaintStatusService.getDefaultComplaintStatus());

        ComplaintSubmitResponse response = new ComplaintSubmitResponse();
        try {
            complaint = complaintService.saveComplaint(complaint);
            response.setSuccess(true);
            response.setMessage("Complaint saved successfully. Your complaint reference number is " + complaint.getId());
            response.setId(complaint.getId());
        }
        catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
