package com.ricardovz.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Spring boot application used to serve API services
 */
@Slf4j
@SpringBootApplication
@EnableMongoRepositories()
public class Application {

    /**
     * Initializer of teh application
     *
     * @param args initial arguments
     */
    public static void main(String[] args) {

        log.debug("Starting api.ricardovz.com services");

        SpringApplication.run(Application.class, args);
    }
}
