package com.pgms.manager.controller;

import com.pgms.manager.security.UserDetail;
import com.pgms.service.api.VerificationStatusService;
import com.pgms.shared.model.VerificationStatus;
import com.pgms.shared.pojo.PgmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by user-1 on 18/7/15.
 */
@Controller
@RequestMapping(value = "/cc")
@PreAuthorize("hasRole('ROLE_CALL_CENTRE')")
public class CallCentreController {

    @Autowired
    VerificationStatusService verificationStatusService;

    @RequestMapping("/dashboard")
    public ModelAndView dashboard(@AuthenticationPrincipal UserDetail userDetail) {
        ModelAndView modelAndView = new ModelAndView("cc_dashboard");
        modelAndView.addObject("user", userDetail);
        return modelAndView;
    }

    @RequestMapping("/statusList")
    @ResponseBody
    public PgmsResponse<List<VerificationStatus>> statusList() {
        PgmsResponse<List<VerificationStatus>> pgmsResponse = new PgmsResponse<>();
        pgmsResponse.setData(verificationStatusService.getAllActiveVerificationStatus());
        pgmsResponse.setSuccess(true);
        return pgmsResponse;
    }
}
