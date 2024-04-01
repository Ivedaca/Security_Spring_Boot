package com.project.security.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override   //Cors setting
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")   //"/**" All registry
                .allowedOrigins("http://localhost:4200")  //4200 Angular application by default, in this point must be front-end url
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS") //methods allowed  on the app
                .allowedHeaders("Origin","Content-Type","Accept","Authorization")
                .allowCredentials(true) //This point request credentials, this is a private route
                .maxAge(3600);

        registry.addMapping("/auth/**")   //All registry
                .allowedOrigins("*")  // Here (*) you accept all route
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                .allowedHeaders("Origin","Content-Type","Accept","Authorization")
                .allowCredentials(false) //This is to public route
                .maxAge(3600);
    }
}
