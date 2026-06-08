package com.authentication.SpringSec.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.authentication.SpringSec.Model.Users;
import com.authentication.SpringSec.Service.Userservice;

@RestController
public class UserController {
    @Autowired
    private Userservice service;
    @PostMapping("/register")
    public Users register(@RequestBody Users user){
        return service.register(user);
    }
}
