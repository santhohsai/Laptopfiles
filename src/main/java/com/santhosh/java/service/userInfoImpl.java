package com.santhosh.java.service;

import com.santhosh.java.Repository.UserRepo;
import com.santhosh.java.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userInfoImpl implements UserInfoInterface, UserDetailsService {

    @Autowired
    UserRepo userepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserInfo addUser(UserInfo newuser) {
        if(userepo.findByUsername(newuser.getUsername()).isPresent()) {
            throw new RuntimeException("User already exists: " + newuser.getUsername());
        }
        newuser.setPassword(passwordEncoder.encode(newuser.getPassword()));
        return userepo.save(newuser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = userepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));

        return new User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")) // must have at least 1 role
        );
    }
}
