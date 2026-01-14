package com.example.Calculator.Service;

import com.example.Calculator.Database.CalculatorDatabase;
import com.example.Calculator.Database.UserDatabase;
import com.example.Calculator.model.Calculator;
import com.example.Calculator.model.LoginRequest;
import com.example.Calculator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserDatabase userDatabase;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public ResponseEntity<User> createUser(User user){
        user.setActive(true);
        user.setPassword(encoder.encode(user.getPassword()));
        userDatabase.save(user);
        return new ResponseEntity<>(user,  HttpStatus.CREATED);
    }

    /*public ResponseEntity<String> loginUser(LoginRequest loginRequest){
        try{
            Optional<User> user = userDatabase.findByEmail(loginRequest.getEmail());
            if(user.isPresent()){
                if(user.get().getPassword().equals(loginRequest.getPassword())){
                    return new ResponseEntity<>("Login Successful",  HttpStatus.OK);
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Invalid Email or Password",  HttpStatus.NOT_FOUND);
    }*/

}
