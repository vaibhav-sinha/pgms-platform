package com.pgms.manager.controller;

import com.pgms.manager.security.UserDetail;
import com.pgms.service.api.*;
import com.pgms.shared.model.*;
import com.pgms.shared.pojo.PgmsComplaintFilter;
import com.pgms.shared.pojo.PgmsResponse;
import com.pgms.shared.pojo.PgmsUpdateFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Date;
import java.util.List;

/**
 * Created by user-1 on 28/6/15.
 */

@Controller
public class ManagerController {

    @Autowired
    ComplaintService complaintService;
    @Autowired
    ComplaintStatusService complaintStatusService;
    @Autowired
    VerificationStatusService verificationStatusService;
    @Autowired
    ReviewStatusService reviewStatusService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    UpdateService updateService;
    @Autowired
    OfficerService officerService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @PreAuthorize("hasAnyRole('ROLE_OFFICER', 'ROLE_CMO', 'ROLE_CALL_CENTRE')")
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(@AuthenticationPrincipal UserDetail userDetail, HttpServletRequest request) {
        if(request.isUserInRole("ROLE_OFFICER")) {
            return "redirect:/officer/dashboard";
        }
        else if(request.isUserInRole("ROLE_CMO")) {
            return "redirect:/cmo/dashboard";
        }
        else if(request.isUserInRole("ROLE_CALL_CENTRE")) {
            return "redirect:/cc/dashboard";
        }
        else {
            return "error";
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_OFFICER', 'ROLE_CMO', 'ROLE_CALL_CENTRE')")
    @RequestMapping(value = "/complaint/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PgmsResponse<Complaint> complaints(@AuthenticationPrincipal UserDetail userDetail, @PathVariable Long id) {
        PgmsResponse<Complaint> pgmsResponse = new PgmsResponse<>();
        pgmsResponse.setSuccess(true);
        pgmsResponse.setData(complaintService.findComplaintById(id));
        return pgmsResponse;
    }

    @PreAuthorize("hasAnyRole('ROLE_OFFICER', 'ROLE_CMO', 'ROLE_CALL_CENTRE')")
    @RequestMapping(value = "/complaints", method = RequestMethod.POST)
    @ResponseBody
    public PgmsResponse<List<Complaint>> complaints(@AuthenticationPrincipal UserDetail userDetail, @RequestBody PgmsComplaintFilter pgmsComplaintFilter) {
        pgmsComplaintFilter.setUserRole(userDetail.getRole());
        PgmsResponse<List<Complaint>> pgmsResponse = new PgmsResponse<>();
        pgmsResponse.setSuccess(true);
        pgmsResponse.setData(complaintService.getComplaintsForFilter(pgmsComplaintFilter));
        return pgmsResponse;
    }

    @PreAuthorize("hasAnyRole('ROLE_OFFICER', 'ROLE_CMO', 'ROLE_CALL_CENTRE')")
    @RequestMapping(value = "/complaints/count", method = RequestMethod.POST)
    @ResponseBody
    public PgmsResponse<Long> complaintsCount(@AuthenticationPrincipal UserDetail userDetail, @RequestBody PgmsComplaintFilter pgmsComplaintFilter) {
        pgmsComplaintFilter.setUserRole(userDetail.getRole());
        PgmsResponse<Long> pgmsResponse = new PgmsResponse<>();
        pgmsResponse.setSuccess(true);
        pgmsResponse.setData(complaintService.getComplaintsCountForFilter(pgmsComplaintFilter));
        return pgmsResponse;
    }

    @PreAuthorize("hasAnyRole('ROLE_OFFICER', 'ROLE_CMO', 'ROLE_CALL_CENTRE')")
    @RequestMapping(value = "/updateComplaint", method = RequestMethod.POST)
    @ResponseBody
    public PgmsResponse<Complaint> updateComplaint(@RequestBody Complaint complaint) {
        PgmsResponse<Complaint> pgmsResponse = new PgmsResponse<>();
        Complaint savedComplaint = complaintService.saveComplaint(complaint);
        pgmsResponse.setData(savedComplaint);
        pgmsResponse.setSuccess(true);
        return pgmsResponse;
    }

    @PreAuthorize("hasAnyRole('ROLE_OFFICER', 'ROLE_CMO', 'ROLE_CALL_CENTRE')")
    @RequestMapping(value = "/statusList", method = RequestMethod.GET)
    public String statusList(HttpServletRequest request) {
        if(request.isUserInRole("ROLE_OFFICER")) {
            return "redirect:/officer/statusList";
        }
        if(request.isUserInRole("ROLE_CMO")) {
            return "redirect:/cmo/statusList";
        }
        if(request.isUserInRole("ROLE_CALL_CENTRE")) {
            return "redirect:/cc/statusList";
        }
        return "error";
    }

    @PreAuthorize("hasAnyRole('ROLE_OFFICER', 'ROLE_CMO', 'ROLE_CALL_CENTRE')")
    @RequestMapping(value = "/departmentList", method = RequestMethod.GET)
    @ResponseBody
    public PgmsResponse<List<Department>> departmentList() {
        PgmsResponse<List<Department>> pgmsResponse = new PgmsResponse<>();
        pgmsResponse.setSuccess(true);
        pgmsResponse.setData(departmentService.getAllActiveDepartments());
        return pgmsResponse;
    }

    @PreAuthorize("hasAnyRole('ROLE_OFFICER', 'ROLE_CMO', 'ROLE_CALL_CENTRE')")
    @RequestMapping(value = "/getUpdateHistory/{complaintId}", method = RequestMethod.GET)
    @ResponseBody
    public PgmsResponse<List<Update>> getUpdateHistory(@PathVariable Long complaintId) {
        PgmsUpdateFilter pgmsUpdateFilter = new PgmsUpdateFilter();
        pgmsUpdateFilter.setComplaintId(complaintId);

        PgmsResponse<List<Update>> pgmsResponse = new PgmsResponse<>();
        pgmsResponse.setSuccess(true);
        pgmsResponse.setData(updateService.getUpdatesForFilter(pgmsUpdateFilter));
        return pgmsResponse;
    }

    @PreAuthorize("hasAnyRole('ROLE_OFFICER', 'ROLE_CMO', 'ROLE_CALL_CENTRE')")
    @RequestMapping(value = "/saveUpdate", method = RequestMethod.POST)
    @ResponseBody
    public PgmsResponse<Update> saveUpdate(@AuthenticationPrincipal UserDetail userDetail, @RequestBody Update update) {
        update.setUpdatedOn(new Date());
        update.setOfficer(officerService.getOfficer(userDetail.getId()));
        Complaint complaint = update.getComplaint();
        if(update.getUserAction() == UserAction.FORWARD) {
            complaint.setDepartment(update.getNewDepartment());
        }
        else if(update.getUserAction() == UserAction.COMPLAINT_STATUS_CHANGE) {
            complaint.setComplaintStatus(update.getNewComplaintStatus());
            if(complaint.getComplaintStatus() == complaintStatusService.getClosedComplaintStatus()) {
                complaint.setVerificationStatus(verificationStatusService.getDefaultVerificationStatus());
            }
        }
        else if(update.getUserAction() == UserAction.VERIFICATION_STATUS_CHANGE) {
            complaint.setVerificationStatus(update.getNewVerificationStatus());
            if(complaint.getVerificationStatus() == verificationStatusService.getClosedVerificationStatus()) {
                complaint.setReviewStatus(reviewStatusService.getDefaultReviewStatus());
            }
        }
        else if(update.getUserAction() == UserAction.REVIEW_STATUS_CHANGE) {
            complaint.setReviewStatus(update.getNewReviewStatus());
            if(complaint.getReviewStatus() == reviewStatusService.getReopenReviewStatus()) {
                complaint.setReopened(true);
                complaint.setComplaintStatus(complaintStatusService.getDefaultComplaintStatus());
                complaint.setVerificationStatus(null);
                complaint.setReviewStatus(null);
            }
        }

        complaintService.saveComplaint(complaint);

        PgmsResponse<Update> pgmsResponse = new PgmsResponse<>();
        pgmsResponse.setSuccess(true);
        pgmsResponse.setData(updateService.saveUpdate(update));
        return pgmsResponse;
    }
}
