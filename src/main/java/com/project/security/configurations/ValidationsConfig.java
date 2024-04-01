package com.project.security.configurations;

import com.project.security.services.models.validations.UserValidation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationsConfig {

    @Bean
    public UserValidation userValidation(){

        return new UserValidation();
    }

}
