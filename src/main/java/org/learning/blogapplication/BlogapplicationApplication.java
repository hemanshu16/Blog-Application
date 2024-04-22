package org.learning.blogapplication;

import org.learning.blogapplication.entities.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;

@SpringBootApplication
public class BlogapplicationApplication {

    public static void main(String[] args) {
        User user = new User();
        SpringApplication.run(BlogapplicationApplication.class, args);
    }

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

}
