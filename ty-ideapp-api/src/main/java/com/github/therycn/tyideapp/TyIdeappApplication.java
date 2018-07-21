package com.github.therycn.tyideapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * IDEAPP Application.
 * 
 * @author TheryLeopard
 *
 */
@SpringBootApplication
@EnableJpaRepositories
public class TyIdeappApplication {

    public static void main(String[] args) {
        SpringApplication.run(TyIdeappApplication.class, args);
    }
}
