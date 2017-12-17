package com.epam.controller;

import com.epam.models.User;
import com.epam.services.interfaces.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.PUT)
    public User createUser(@RequestParam("user") String userDtoString) throws IOException {
        User user = new ObjectMapper().readValue(userDtoString,
                User.class);
        userService.save(user);
        return user;
    }

    @RequestMapping(value = "/{id:[\\d]*}", method = RequestMethod.POST)
    public User updateUser(@PathVariable long id, @RequestParam("user") String userDtoString) throws IOException {
        User newUser = new ObjectMapper().readValue(userDtoString,
                User.class);
        return userService.update(id, newUser);
    }

    @RequestMapping(value = "/{id:[\\d]*}", method = RequestMethod.GET)
    public User getUser(@PathVariable long id) {
        User user = userService.getById(id);
        return user;
    }

    @RequestMapping(value = "/{id:[\\d]*}", method = RequestMethod.DELETE)
    public boolean deleteUser(@PathVariable long id) {
        userService.delete(id);
        return true;
    }
}

