package com.epam.dto.interfaces;

import com.epam.dto.UserDto;

import java.util.List;

public interface UserDtoService {

    UserDto save(UserDto userDto);

    List<UserDto> all();

    UserDto getById(long userId);

    UserDto getByName(String name);

    UserDto update(long id, UserDto userDto);

    boolean update(UserDto userDto);

    boolean delete(UserDto userDto);

    boolean delete(long userId);

}
