package com.project.security.services.implementations;

import com.project.security.persistence.entities.UserEntity;
import com.project.security.persistence.repositories.UserRepository;
import com.project.security.services.IAuthService;
import com.project.security.services.IJWTUtilityService;
import com.project.security.services.models.dtos.LoginDTO;
import com.project.security.services.models.dtos.ResponseDTO;
import com.project.security.services.models.validations.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IJWTUtilityService jwtUtilityService;

    @Autowired
    private UserValidation userValidation;

    @Override
    public HashMap<String, String> login(LoginDTO login) throws Exception {
        try{
            HashMap<String, String> jwt = new HashMap<>();
            //find user by email
            Optional<UserEntity> user = userRepository.findByEmail(login.getEmail());
            if (user.isEmpty()){
                jwt.put("Error", "User not registered!");
                return jwt;
            }

            //Authentication
            if (verifyPassword(login.getPassword(), user.get().getPassword())){
                jwt.put("jwt", jwtUtilityService.generateJWT(user.get().getId()));
            } else{
                jwt.put("Error", "Authentication failed");
            }
            return jwt;
        } catch (Exception e){
            throw new Exception(e.toString());
        }
    }

    public ResponseDTO register(UserEntity user) throws Exception {
        try {
            ResponseDTO response = userValidation.validate(user);

            if (response.getNumOfErrors() > 0){
                return response;
            }

            List<UserEntity> getAllUsers = userRepository.findAll();

            for (UserEntity repeatFields : getAllUsers){
                if(repeatFields != null){
                    response.setNumOfErrors(1);
                    response.setMessage("User already exists!");
                    return response;
                }
            }

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12); //Encryption level
            user.setPassword(encoder.encode(user.getPassword())); //Here will be encrypt password
            userRepository.save(user);
            response.setMessage("User created successfully!");

            return response;

        }catch (Exception e){
            throw new Exception(e.toString());
        }
    }

    //Verify password
    private boolean verifyPassword(String enteredPassword, String storedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(enteredPassword, storedPassword);
    }
}
