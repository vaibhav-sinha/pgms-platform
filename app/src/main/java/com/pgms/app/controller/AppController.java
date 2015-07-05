package com.pgms.app.controller;

import com.pgms.service.api.*;
import com.pgms.shared.model.*;
import com.pgms.shared.pojo.ComplaintSubmitRequest;
import com.pgms.shared.pojo.ComplaintSubmitResponse;
import com.pgms.shared.pojo.PgmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

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

    @Autowired
    CategoryService categoryService;

    @Autowired
    LocationService locationService;

    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public String getSubmitPage() {
        return "submit";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
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
        if(complaintSubmitRequest.getCategory() != null) {
            complaint.setCategory(categoryService.getCategory(complaintSubmitRequest.getCategory().getId()));
        }
        complaint.setDepartment(departmentService.getDepartment(complaintSubmitRequest.getDepartment().getId()));
        complaint.setDescription(complaintSubmitRequest.getDescription());
        complaint.setLocation(locationService.getLocation(complaintSubmitRequest.getLocation().getId()));
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

    @ResponseBody
    @RequestMapping(value = "/getAllCategories", method = RequestMethod.GET)
    public PgmsResponse<List<Category>> getAllCategory() {
        PgmsResponse<List<Category>> pgmsResponse = new PgmsResponse<>();
        try {
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully got all categories");
            pgmsResponse.setData(categoryService.getAllCategories());
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/getAllLocations", method = RequestMethod.GET)
    public PgmsResponse<List<Location>> getAllLocation() {
        PgmsResponse<List<Location>> pgmsResponse = new PgmsResponse<>();
        try {
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully got all locations");
            pgmsResponse.setData(locationService.getAllLocation());
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/getAllDepartments", method = RequestMethod.GET)
    public PgmsResponse<List<Department>> getAllDepartment() {
        PgmsResponse<List<Department>> pgmsResponse = new PgmsResponse<>();
        try {
            pgmsResponse.setSuccess(true);
            pgmsResponse.setMessage("Successfully got all departments");
            pgmsResponse.setData(departmentService.getAllDepartments());
        }
        catch (Exception e) {
            pgmsResponse.setSuccess(false);
            pgmsResponse.setMessage(e.getMessage());
        }
        return pgmsResponse;
    }
}
