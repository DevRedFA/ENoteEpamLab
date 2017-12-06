package com.epam.model;

import java.util.List;

public interface UserRepository {

    User save(User user);

    void update(User user);

    List<User> all();
}