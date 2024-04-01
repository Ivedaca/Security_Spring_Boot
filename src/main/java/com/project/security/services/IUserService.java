package com.project.security.services;

import com.project.security.persistence.entities.UserEntity;

import java.util.List;

//It's just to test private route (public route)
public interface IUserService {

    public List<UserEntity> findAllUsers();
}
