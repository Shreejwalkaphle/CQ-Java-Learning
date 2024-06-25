package com.example.Integration2.services;

import com.example.Integration2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public String serviceMethod(){
        return  " hi from service method";
    }
}
