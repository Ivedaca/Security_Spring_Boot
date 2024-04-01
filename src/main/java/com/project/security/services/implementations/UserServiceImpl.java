package com.project.security.services.implementations;

import com.project.security.persistence.entities.UserEntity;
import com.project.security.persistence.repositories.UserRepository;
import com.project.security.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    //It's just to test private route (public route)
    @Override
    public List<UserEntity> findAllUsers() {

        return userRepository.findAll();
    }
}
