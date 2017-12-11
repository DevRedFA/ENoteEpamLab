package com.epam.services.interfaces;

import com.epam.models.User;

import java.util.List;

public interface UserService {

    User save(User user);

    List<User> all();

    User getById(int userId);

    User getByName(String name);

    void update(User user);
}
