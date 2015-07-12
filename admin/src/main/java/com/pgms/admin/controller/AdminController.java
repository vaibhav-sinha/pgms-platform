package com.pgms.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by user-1 on 27/6/15.
 */
@Controller
/*@RequestMapping("/ui")*/
public class AdminController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/home")
    public String admin() {
        return "admin";
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/designation")
    public String designation() {
        return "designation";
    }

    @RequestMapping("/complaintStatus")
    public String complaintStatus() {
        return "complaintStatus";
    }

    @RequestMapping("/verificationStatus")
    public String verificationStatus() {
        return "verificationStatus";
    }

    @RequestMapping("/reviewStatus")
    public String reviewStatus() {
        return "reviewStatus";
    }

    @RequestMapping("/category")
    public String category() {
        return "category1";
    }

    @RequestMapping("/location")
    public String location() {
        return "location";
    }

    @RequestMapping("/department")
    public String department() {
        return "department";
    }

    @RequestMapping("/officer")
    public String officer() {
        return "officer";
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
