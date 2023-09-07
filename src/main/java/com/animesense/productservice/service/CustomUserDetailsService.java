package com.animesense.productservice.service;

import com.animesense.productservice.models.CustomUserDetails;
import com.animesense.productservice.models.User;
import com.animesense.productservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user=userRepository.findByUsername(username);
        return user.map(CustomUserDetails::new).orElseThrow(()->new UsernameNotFoundException("user not found "+username));
    }
}
