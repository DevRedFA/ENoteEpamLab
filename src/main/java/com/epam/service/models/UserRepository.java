package com.epam.service.models;

import java.util.List;

public interface UserRepository {

    User save(User user);

    void update(User user);

    User update(long id, User user);

    void delete(User user);

    List<User> all();

    void deleteById(long userId);

    User getById(long userId);

    User getByName(String name);
}