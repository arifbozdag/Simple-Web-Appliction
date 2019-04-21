package com.lbs.bilkent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController{

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "myusers", method = RequestMethod.GET)
    public List<User> getUsers() {return userRepository.findAll();}

    @RequestMapping(value = "myusers/{username}", method = RequestMethod.GET)
    public User getUsersByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }

    @RequestMapping( value = "myusers/{id}", method= RequestMethod.DELETE)
    public List<User> deleteUser(@PathVariable String id){
        userRepository.delete(id);
        return userRepository.findAll();
    }


}
