package com.epam.controller;

import com.epam.dto.UserDto;
import com.epam.models.User;
import com.epam.services.interfaces.UserDtoService;
import com.epam.services.interfaces.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserDtoService userDtoService;

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userDtoService.save(userDto);
    }

    @PostMapping(value = "/{id:[\\d]*}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUser(@PathVariable long id, @RequestBody UserDto userDto) {
        return userDtoService.update(id, userDto);
    }

    @GetMapping(value = "/{id:[\\d]*}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUser(@PathVariable long id) {
        return userDtoService.getById(id);
    }

    @DeleteMapping(value = "/{id:[\\d]*}")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteUser(@PathVariable long id) {
        return userDtoService.delete(id);
    }
}

