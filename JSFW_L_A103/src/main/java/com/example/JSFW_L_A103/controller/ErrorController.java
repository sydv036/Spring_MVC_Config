package com.example.JSFW_L_A103.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authentication")
public class ErrorController {

    @GetMapping("/403")
    public String getError403() {
        return "error/403";
    }

    @GetMapping("/500")
    public String getError500() {
        return "error/500";
    }

}
