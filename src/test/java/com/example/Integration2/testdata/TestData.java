package com.example.Integration2.testdata;

import com.example.Integration2.models.Users;

public class TestData {
    public static Users createuser(){
        Users user = new Users();
        user.setId(1);
        user.setPassword("dummy password");
        user.setName("dummy password");
        user.setRole("dummy role");
        return user;
    }
}
