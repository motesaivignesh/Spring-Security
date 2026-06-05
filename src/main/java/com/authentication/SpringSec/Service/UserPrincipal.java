package com.authentication.SpringSec.Service;

import java.util.Collection;
import java.util.Collections;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.authentication.SpringSec.Model.Users;

public class UserPrincipal implements UserDetails{
    private Users user; 
    public UserPrincipal(Users user2) {
        //TODO Auto-generated constructor stub
        this.user=user2;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public @Nullable String getPassword() {
        return user.getPassword();
        
    }
    @Override
    public String getUsername() {
        return user.getUsername();
        
    }
    @Override
public boolean isAccountNonExpired() {
    return true; // MUST BE TRUE
}

@Override
public boolean isAccountNonLocked() {
    return true; // MUST BE TRUE
}

@Override
public boolean isCredentialsNonExpired() {
    return true; // MUST BE TRUE
}

@Override
public boolean isEnabled() {
    return true; // MUST BE TRUE
}


}
