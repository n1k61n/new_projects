package com.example.agency.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;



@Service
@RequiredArgsConstructor
public class UserServiceImpl {

    private static UserServiceImpl instance;
    private Set<String> users;


    public static synchronized UserServiceImpl getInstance(){
        if(instance == null){
            instance = new UserServiceImpl();
        }
        return instance;
    }

    public Set<String> getUsers() {
        return users;
    }

    public void setUser(String userName) throws Exception {
        if(users.contains(userName)){
            throw new Exception("USer already exist with username" + userName);
        }
        users.add(userName);
    }
}
