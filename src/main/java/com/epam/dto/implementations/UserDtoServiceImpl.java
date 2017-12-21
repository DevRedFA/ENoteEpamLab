package com.epam.dto.implementations;

import com.epam.mapper.UserMapper;
import com.epam.dto.UserDto;
import com.epam.service.models.*;
import com.epam.dto.interfaces.UserDtoService;
import com.epam.service.interfaces.UserService;
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
    if (user.getName() != null) {
      oldUser.setName(user.getName());
    }
    if (user.getPassword() != null) {
      oldUser.setPassword(user.getPassword());
    }
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
