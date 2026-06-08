package com.authentication.SpringSec.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.authentication.SpringSec.Model.Users;
import com.authentication.SpringSec.repo.Userrepo;

@Service
public class Userservice {
    @Autowired
    private Userrepo repo;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    public Users register(Users user){
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }
}
