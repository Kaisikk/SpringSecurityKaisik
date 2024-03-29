package com.kaisikk.java.springsecurity.springsecurity.services;

import com.kaisikk.java.springsecurity.springsecurity.config.security.MyUserDetails;
import com.kaisikk.java.springsecurity.springsecurity.model.MyUser;
import com.kaisikk.java.springsecurity.springsecurity.model.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Kaisikk
 *
 * Сервис для получения юзеров из базы
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> user = repository.findByName(username);
        return user.map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}
