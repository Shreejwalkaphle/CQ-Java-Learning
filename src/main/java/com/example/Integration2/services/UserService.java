package com.example.Integration2.services;

import com.example.Integration2.models.Users;
import com.example.Integration2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public String updateData(Users user) {
        Optional<Users> existingUserOpt = userRepository.findById(user.getId());

        if (existingUserOpt.isPresent()) {
            Users existingUser = existingUserOpt.get();
            existingUser.setName(user.getName());
            existingUser.setRole(user.getRole());
            existingUser.setPassword(user.getPassword());

            userRepository.save(existingUser);
            return "User updated successfully";
        } else {
            return "User not found";
        }
    }

    public String deleteData(int userId) {
        Optional<Users> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
            return "User deleted successfully";
        } else {
            return "User not found";
        }
    }


    public String saveData(Users user){
        userRepository.save(user);
        return "user"+ user +" saved successfully";
    }
    public Optional<Users> retrieveData(int id){
        Optional<Users> user = userRepository.findById(id);
        return user;
    }



}
