package com.kaisikk.java.springsecurity.springsecurity.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Application {

    private Integer id;

    private String name;

    private String author;

    private String version;

}
