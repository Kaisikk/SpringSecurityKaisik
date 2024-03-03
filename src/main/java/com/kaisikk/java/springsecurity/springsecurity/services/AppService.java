package com.kaisikk.java.springsecurity.springsecurity.services;

import com.github.javafaker.Faker;
import com.kaisikk.java.springsecurity.springsecurity.model.Application;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class AppService {

    private List<Application> applications;

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

}
