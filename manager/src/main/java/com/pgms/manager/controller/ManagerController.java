package com.pgms.manager.controller;

import com.pgms.manager.security.UserDetail;
import com.pgms.service.api.ComplaintService;
import com.pgms.shared.model.Complaint;
import com.pgms.shared.pojo.PgmsComplaintFilter;
import com.pgms.shared.pojo.PgmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

/**
 * Created by user-1 on 28/6/15.
 */
@Controller
public class ManagerController {

    @Autowired
    ComplaintService complaintService;

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

    @RequestMapping("/complaints")
    @ResponseBody
    public PgmsResponse<List<Complaint>> complaints(@RequestBody PgmsComplaintFilter pgmsComplaintFilter) {
        PgmsResponse<List<Complaint>> pgmsResponse = new PgmsResponse<>();
        pgmsResponse.setSuccess(true);
        pgmsResponse.setData(complaintService.getComplaintsForFilter(pgmsComplaintFilter));
        return pgmsResponse;
    }
}
