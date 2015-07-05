package com.pgms.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by user-1 on 27/6/15.
 */
@Controller
@RequestMapping("/ui")
public class AdminController {

    @RequestMapping("/")
    public String admin() {
        return "admin";
    }
}
