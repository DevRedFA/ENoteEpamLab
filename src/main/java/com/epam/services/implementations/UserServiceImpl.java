package com.epam.services.implementations;

import com.epam.dao.JpaProxyUserRepository;
import com.epam.model.User;
import com.epam.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    JpaProxyUserRepository jpaProxyUserRepository;

    public User save(User user) {
        return jpaProxyUserRepository.save(user);
    }

    public List<User> all() {
        return jpaProxyUserRepository.all();
    }

    public User getById(int userId) {
        return jpaProxyUserRepository.getById(userId);
    }

    public User getByName(String name) {
        return jpaProxyUserRepository.getByName(name);
    }

    public void update(User user) {
        jpaProxyUserRepository.update(user);
    }
}
