package com.example.Calculator.Service;

import com.example.Calculator.Database.UserDatabase;
import com.example.Calculator.model.MyUserDetails;
import com.example.Calculator.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class LoginService implements UserDetailsService {

    UserDatabase userDatabase;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDatabase.findByEmail(email);
        return new MyUserDetails(user);
    }
}
