package com.authentication.SpringSec.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class Hellocontroller {
    @GetMapping("/")
    public String hello(HttpServletRequest request){
        return "lets get started with Spring-Security"+request.getSession().getId();
    }
}
