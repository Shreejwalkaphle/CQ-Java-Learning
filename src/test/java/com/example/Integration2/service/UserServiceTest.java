package com.example.Integration2.service;

import com.example.Integration2.models.Users;
import com.example.Integration2.repository.UserRepository;
import com.example.Integration2.services.UserService;
import com.example.Integration2.testdata.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;

    @Test
    public void testUserExist(){
//        given
        Users newUser = TestData.createuser();
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(newUser));
        // When
        Optional<Users> retrievedUser = userRepository.findById(1);

        // Then
        assertEquals(Optional.of(newUser), retrievedUser);
    }
    @Test
    public void testUserDoesNotExist(){
        Mockito.when(userRepository.findById(1)).thenReturn(null);
        // When
        Optional<Users> retrievedUser = userRepository.findById(1);

        // Then
        assertEquals(null, retrievedUser);
    }

}
