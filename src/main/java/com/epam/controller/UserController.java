package com.epam.controller;

import com.epam.dto.UserDto;
import com.epam.dto.interfaces.UserDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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


    @GetMapping(value = "/all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getallUsers() {
        return userDtoService.all();
    }

    @DeleteMapping(value = "/{id:[\\d]*}")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteUser(@PathVariable long id) {
        return userDtoService.delete(id);
    }
}

