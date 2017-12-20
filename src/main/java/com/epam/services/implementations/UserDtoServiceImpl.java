package com.epam.services.implementations;

import com.epam.mapper.UserMapper;
import com.epam.dto.UserDto;
import com.epam.models.*;
import com.epam.services.interfaces.UserDtoService;
import com.epam.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserDtoServiceImpl implements UserDtoService {

    @Autowired
    UserService userService;

    public UserDto save(UserDto userDto) {
        User user = UserMapper.toUser(userDto);
        user = userService.save(user);
        return UserMapper.toUserDto(user);
    }


    public List<UserDto> all() {
        List<UserDto> result = new ArrayList<>();
        for (User user : userService.all()) {
            result.add(UserMapper.toUserDto(user));
        }
        return result;
    }

    public UserDto getById(long userId) {
        return UserMapper.toUserDto(userService.getById(userId));
    }

    public UserDto getByName(String name) {
        return null;
    }

    @Override
    public UserDto update(long id, UserDto userDto) {
        User oldUser = userService.getById(id);
        //TODO: check fields for null
        User user = UserMapper.toUser(userDto);
        if (user.getName() != null)
            oldUser.setName(user.getName());
        if (user.getPassword() != null)
            oldUser.setPassword(user.getPassword());
        if (user.getNotebooks() != null)
            oldUser.setNotebooks(user.getNotebooks());
        if (oldUser.getNotebooks() != null)
            for (Notebook notebook : oldUser.getNotebooks()) {
                notebook.setUser(oldUser);
                for (Note note : notebook.getNotes()) {
                    oldUser.getNotes().add(note);
                    note.setNotebook(notebook);
                }
            }
        if (user.getNotes() != null)
            oldUser.getNotes().addAll(user.getNotes());
        if (oldUser.getNotes() != null)
            for (Note note : oldUser.getNotes()) {
                note.setUser(oldUser);
            }
        if (user.getTags() != null)
            oldUser.setTags(user.getTags());
        userService.update(oldUser);
        return UserMapper.toUserDto(oldUser);
    }

    public boolean update(UserDto userDto) {
        userService.update(UserMapper.toUser(userDto));
        return true;

    }

    @Override
    public boolean delete(UserDto userDto) {
        userService.delete(UserMapper.toUser(userDto));
        return true;
    }

    @Override
    public boolean delete(long userId) {
        userService.delete(userId);
        return true;
    }
}
