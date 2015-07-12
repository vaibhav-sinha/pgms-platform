package com.pgms.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by user-1 on 27/6/15.
 */
@Controller
public class AdminController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/partials/category")
    public String partialCategory() {
        return "partials/category";
    }

    @RequestMapping("/partials/complaintStatus")
    public String partialComplaintStatus() {
        return "partials/complaintStatus";
    }

    @RequestMapping("/partials/verificationStatus")
    public String partialVerificationStatus() {
        return "partials/verificationStatus";
    }

    @RequestMapping("/partials/reviewStatus")
    public String partialReviewStatus() {
        return "partials/reviewStatus";
    }

    @RequestMapping("/partials/department")
    public String partialDepartment() {
        return "partials/department";
    }

    @RequestMapping("/partials/designation")
    public String partialDesignation() {
        return "partials/designation";
    }

    @RequestMapping("/partials/location")
    public String partialLocation() {
        return "partials/location";
    }

    @RequestMapping("/partials/officer")
    public String partialOfficer() {
        return "partials/officer";
    }
}
