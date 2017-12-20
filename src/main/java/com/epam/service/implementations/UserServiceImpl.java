package com.epam.service.implementations;

import com.epam.service.models.User;
import com.epam.service.models.UserRepository;
import com.epam.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository jpaProxyUserRepository;

    public User save(User user) {
        return jpaProxyUserRepository.save(user);
    }

    public List<User> all() {
        return jpaProxyUserRepository.all();
    }

    public User getById(long userId) {
        return jpaProxyUserRepository.getById(userId);
    }

    public User getByName(String name) {
        return jpaProxyUserRepository.getByName(name);
    }

    @Override
    public User update(long id, User user) {
        User oldUser = jpaProxyUserRepository.getById(id);
        //TODO: check fields for null
        oldUser.setName(user.getName());
        oldUser.setNotebooks(user.getNotebooks());
        oldUser.setPassword(user.getPassword());
        oldUser.setNotes(user.getNotes());
        oldUser.setTags(user.getTags());
        oldUser.setTags(user.getTags());
        jpaProxyUserRepository.update(oldUser);
        return oldUser;
    }

    public void update(User user) {
        jpaProxyUserRepository.update(user);
    }

    @Override
    public void delete(User user) {
        jpaProxyUserRepository.delete(user);
    }

    @Override
    public void delete(long userId) {
        jpaProxyUserRepository.deleteById(userId);
    }
}
