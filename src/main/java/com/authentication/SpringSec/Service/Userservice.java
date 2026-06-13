package com.authentication.SpringSec.Service;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.authentication.SpringSec.Model.Users;
import com.authentication.SpringSec.repo.Userrepo;

@Service
public class Userservice {
    private Userrepo repo;
    AuthenticationManager authmanager;
    private Jwtservice jwtservice;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    public Userservice(Userrepo repo, AuthenticationManager authmanager, Jwtservice jwtservice) {
        this.repo = repo;
        this.authmanager = authmanager;
        this.jwtservice = jwtservice;
    }
    public Users register(Users user){
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }
    public String verify(Users user) {
        org.springframework.security.core.Authentication authentication = authmanager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtservice.generatetoken(user.getUsername());
        }
        return "fail";
    }
}
