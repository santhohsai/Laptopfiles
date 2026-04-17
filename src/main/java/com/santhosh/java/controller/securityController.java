package com.santhosh.java.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("api/security")
public class securityController
{

    @GetMapping("/base")
    public String response()
    {
        return "index";
    }

}
