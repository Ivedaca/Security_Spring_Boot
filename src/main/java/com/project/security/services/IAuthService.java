package com.project.security.services;

import com.project.security.persistence.entities.UserEntity;
import com.project.security.services.models.dtos.LoginDTO;
import com.project.security.services.models.dtos.ResponseDTO;

import java.util.HashMap;

public interface IAuthService {

    public HashMap<String, String> login(LoginDTO login) throws Exception;

    public ResponseDTO register(UserEntity user) throws Exception;
}
