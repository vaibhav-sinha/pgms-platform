package com.pgms.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by user-1 on 27/6/15.
 */
@Controller
@RequestMapping("/ui")
public class AdminController {

    @RequestMapping("/home")
    public String admin() {
        return "admin";
    }

    @RequestMapping("/index")
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
}
