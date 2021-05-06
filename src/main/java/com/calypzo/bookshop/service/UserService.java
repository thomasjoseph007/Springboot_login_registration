package com.calypzo.bookshop.service;

import com.calypzo.bookshop.model.User;
import com.calypzo.bookshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String addusers(User u) {
        try {
            userRepository.save(u);
            return"success";
        }catch (Exception e){
            return "failed";
        }

    }
    public boolean login(String email, String pswd) {
        System.out.println(" email "+email);
        Optional<User> option = Optional.ofNullable(userRepository.findByEmail(email));

        System.out.println("password from form  ="+pswd);
        User user=option.get();

        System.out.println("email id = "+user.getEmail());
        System.out.println("password from table  "+user.getPassword());
        System.out.println("ROLE ="+user.getRole());
        boolean isPasswordMatches = passwordEncoder.matches(pswd,user.getPassword());
        if (isPasswordMatches)
            return true;
        else
            return false;
    }
}
