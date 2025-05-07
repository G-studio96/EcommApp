package com.lauder.app.ecommapp;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootConfiguration(proxyBeanMethods = false)
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.lauder.app.ecommapp.repo")
@EnableMethodSecurity
public class EcommAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommAppApplication.class, args);
    }

}
