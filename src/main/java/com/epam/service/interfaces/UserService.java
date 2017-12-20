package com.epam.service.interfaces;

import com.epam.service.models.User;

import java.util.List;

public interface UserService {

    User save(User user);

    List<User> all();

    User getById(long userId);

    User getByName(String name);

    User update(long id, User user);

    void update(User user);

    void delete(User user);

    void delete(long userId);

}
