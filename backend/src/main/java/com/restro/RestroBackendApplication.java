package com.restro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestroBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestroBackendApplication.class, args);
        System.out.println("=================================================");
        System.out.println("  Restro Spring Boot Backend Started Successfully! ");
        System.out.println("  API Base URL: http://localhost:8081/api         ");
        System.out.println("=================================================");
    }
}
