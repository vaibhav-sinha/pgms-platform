package com.pgms.manager.controller;

import com.pgms.manager.security.UserDetail;
import com.pgms.service.api.ComplaintStatusService;
import com.pgms.shared.json.JsonConverter;
import com.pgms.shared.model.ComplaintStatus;
import com.pgms.shared.pojo.PgmsResponse;
import com.pgms.shared.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

/**
 * Created by user-1 on 18/7/15.
 */
@Controller
@RequestMapping(value = "/officer")
@PreAuthorize("hasRole('ROLE_OFFICER')")
public class OfficerController {

    @Autowired
    JsonConverter jsonConverter;

    @Autowired
    ComplaintStatusService complaintStatusService;

    @RequestMapping("/dashboard")
    public ModelAndView dashboard(@AuthenticationPrincipal UserDetail userDetail) throws IOException {
        ModelAndView modelAndView = new ModelAndView("officer_dashboard");
        modelAndView.addObject("user", userDetail);
        modelAndView.addObject("userJson", jsonConverter.toJson(userDetail));
        return modelAndView;
    }

    @RequestMapping("/partials/inbox")
    public String partialInbox() {
        return "partials/inbox";
    }

    @RequestMapping("/partials/complaint")
    public String partialComplaint() {
        return "partials/complaint";
    }

    @RequestMapping("/statusList")
    @ResponseBody
    public PgmsResponse<List<ComplaintStatus>> statusList() {
        PgmsResponse<List<ComplaintStatus>> pgmsResponse = new PgmsResponse<>();
        pgmsResponse.setData(complaintStatusService.getAllActiveComplaintStatus());
        pgmsResponse.setSuccess(true);
        return pgmsResponse;
    }
}
