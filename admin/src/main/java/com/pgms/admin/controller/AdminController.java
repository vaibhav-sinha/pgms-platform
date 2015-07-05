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

    @RequestMapping("/designation")
    public String designation() {
        return "designation";
    }

    @RequestMapping("/complaintStatus")
    public String complaintStatus() {
        return "complaintStatus";
    }

    @RequestMapping("/category")
    public String category() {
        return "category";
    }
}
