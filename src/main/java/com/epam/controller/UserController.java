package com.epam.controller;

import com.epam.models.User;
import com.epam.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.PUT)
    public String createUser(ModelMap model) {
        User user = User.builder()
                .build();
        // TODO: upload from model all others fields of user.
        userService.save(user);
        return "user";
    }

    @RequestMapping(value = "/{id:[\\d]*}", method = RequestMethod.POST)
    public String updateUser(@PathVariable long id, ModelMap model) {
        User user = User.builder()
                .id((int) id)
                .build();
        // TODO: upload from model all others fields of user.
        userService.update(user);
        return "user";
    }

    @RequestMapping(value = "/{id:[\\d]*}", method = RequestMethod.GET)
    public String getUser(@PathVariable long id, ModelMap model) {
        userService.getById(id);
        return "user";
    }

    @RequestMapping(value = "/{id:[\\d]*}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable long id, ModelMap model) {
        userService.delete(id);
        return "user";
    }

}

