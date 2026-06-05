package com.authentication.SpringSec.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.authentication.SpringSec.Model.Users;
import com.authentication.SpringSec.repo.Userrepo;
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private Userrepo repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
          Users user = repo.findByUsername(username);
          if(user==null){
            throw new UsernameNotFoundException("User not found");
          }
          return new UserPrincipal(user);
    }

}
