package com.project.security.services.models.validations;

import com.project.security.persistence.entities.UserEntity;
import com.project.security.services.models.dtos.ResponseDTO;

// The validation is possible to do from UserEntity with tags
//but this way allow answer control.
public class UserValidation {

    public ResponseDTO validate(UserEntity user){

        ResponseDTO response = new ResponseDTO();

        response.setNumOfErrors(0);

        if (user.getFirstName() == null  ||
            user.getFirstName().length() < 3 ||
            user.getFirstName().length() > 15
        ){
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("First Name don't be Empty, should characters be > 15 and don't be < 3");
        }
        if (user.getLastName() == null  ||
                user.getLastName().length() < 3 ||
                user.getLastName().length() > 30
        ){
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("Last Name don't be null, should characters be > 15 and don't be < 3");
        }
        if (user.getEmail() == null ||
            //Regular expression to validate email
            !user.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){

            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("Email is wrong");
        }
        if (user.getPassword() == null ||
           //Regular expression to validate password
           !user.getPassword().matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,16}$")) {

            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("The password must has 8-16 characters, minimum: one number, one minuscula and one capital letter");
        }
        return response;
    }

}
