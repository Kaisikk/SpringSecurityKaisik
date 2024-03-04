package com.kaisikk.java.springsecurity.springsecurity.services;

import com.github.javafaker.Faker;
import com.kaisikk.java.springsecurity.springsecurity.model.Application;
import com.kaisikk.java.springsecurity.springsecurity.model.MyUser;
import com.kaisikk.java.springsecurity.springsecurity.model.repos.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class AppService {

    private List<Application> applications;

    private UserRepository repository;

    private PasswordEncoder passwordEncoder;

    /**
     * CreateListOfObject
     */
    // гарантирует вызов метода 1 раз после инициализации свойств компонентов
    @PostConstruct
    public void loadAppInDb(){
        Faker faker = new Faker();

        applications = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> Application.builder()
                        .id(i)
                        .name(faker.app().name())
                        .author(faker.app().author())
                        .version(faker.app().version())
                        .build()).toList();
    }

    /**
     * GetListOfApplications
     *
     * @return
     */
    public List<Application> allApplications(){
        return applications;
    }

    /**
     * GetApplicationById
     *
     * @param id
     * @return
     */
    public Application applicationById(int id){
        return applications.stream()
                .filter(app -> app.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void addUser(MyUser user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }

}
