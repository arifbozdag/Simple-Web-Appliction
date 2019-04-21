package com.lbs.bilkent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void addUser(String username, LocalDate birthDate){
        User user = new User(username, birthDate);
        userRepository.save(user);
    }

    public List<User> getUsers(){return userRepository.findAll();}
}
