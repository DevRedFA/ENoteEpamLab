package com.epam.dao.jpaproxyrepository;

import com.epam.dao.entity.UserJpaEntity;
import com.epam.dao.jparepository.UserJpaRepository;
import com.epam.dao.mapper.UserMapper;
import com.epam.models.User;
import com.epam.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaProxyUserRepository implements UserRepository {

    @Autowired
    private UserJpaRepository jpaRepository;

    @Autowired
    private UserMapper userMapper;

    public User save(User newUser) {
        UserJpaEntity savedEntity = jpaRepository.save(userMapper.userToUserEntity(newUser));
        return userMapper.userEntityToUser(savedEntity);
    }

    public void update(User user) {
        jpaRepository.save(userMapper.userToUserEntity(user));
    }


    public List<User> all() {
        List<UserJpaEntity> entities = jpaRepository.findAll();
        return userMapper.userEntitiesToUsers(entities);
    }

    public User getById(int userId) {
        List<UserJpaEntity> entities = jpaRepository.findAll();
        for (UserJpaEntity entity : entities) {
            if (entity.getId() == userId) {
                return userMapper.userEntityToUser(entity);
            }
        }
        return null;
    }

    public User getByName(String name) {
        List<UserJpaEntity> entities = jpaRepository.findAll();
        for (UserJpaEntity entity : entities) {
            if (entity.getName().equalsIgnoreCase(name)) {
                return userMapper.userEntityToUser(entity);
            }
        }
        return null;
    }
}