package com.teja.springboot.thymeleafdemo.security;

import com.teja.springboot.thymeleafdemo.dao.UserRepository;
import com.teja.springboot.thymeleafdemo.entity.User;
import com.teja.springboot.thymeleafdemo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =userRepository.getUserByUsername(username);
        if(user==null) {
            throw new UsernameNotFoundException("Could not find User");
        }
        return new MyUserDetails(user);
    }

}
