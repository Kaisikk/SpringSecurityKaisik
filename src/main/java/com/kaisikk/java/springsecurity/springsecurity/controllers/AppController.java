package com.kaisikk.java.springsecurity.springsecurity.controllers;

import com.kaisikk.java.springsecurity.springsecurity.model.Application;
import com.kaisikk.java.springsecurity.springsecurity.services.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Application> allApplications() {
        return service.allApplications();
    }

    @GetMapping("/{id}")
        public Application applicationById(@PathVariable int id){
        return service.applicationById(id);
        }
    }


