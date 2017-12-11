package com.epam.models;

import java.util.List;

public interface UserRepository {

    User save(User user);

    void update(User user);

    List<User> all();
}