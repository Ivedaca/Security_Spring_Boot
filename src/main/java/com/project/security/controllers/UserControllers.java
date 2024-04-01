package com.project.security.controllers;

import com.project.security.persistence.entities.UserEntity;
import com.project.security.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//It's just to test private route (public route)
@RestController
@RequestMapping("/user")
public class UserControllers {

    @Autowired
    IUserService userService;

    @GetMapping("/find-all")
    private ResponseEntity<List<UserEntity>> getAllUsers(){
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }
}
