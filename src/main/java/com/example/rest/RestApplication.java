package com.example.rest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(

        title = "Hotel Service Demo", contact = @Contact(

        name = "Udod Vladyslav", email = "udod.vladyslav@lll.kpi.ua"),

        description = "Hotel Service Demo that provides CRUD operations for hotel", version = "1.1"

), servers = {

        @Server(url = "http://localhost:8080", description = "local test server")})

@SpringBootApplication
public class RestApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }
}
