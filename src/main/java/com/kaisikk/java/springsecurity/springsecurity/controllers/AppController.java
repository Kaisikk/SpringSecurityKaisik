package com.kaisikk.java.springsecurity.springsecurity.controllers;

import com.kaisikk.java.springsecurity.springsecurity.model.Application;
import com.kaisikk.java.springsecurity.springsecurity.model.MyUser;
import com.kaisikk.java.springsecurity.springsecurity.services.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/apps")
public class AppController {

    @Autowired
    private AppService service;

    @GetMapping("/welcome")
    public String weclome(){
        return "Welcome to the unprotected page";
    }

    @GetMapping("/all-app")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<Application> allApplications() {
        return service.allApplications();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
        public Application applicationById(@PathVariable int id){
        return service.applicationById(id);
        }

        @PostMapping("/new-user")
        public String addUser(@RequestBody MyUser user){
        service.addUser(user);
        return "User is saved";
        }
    }


