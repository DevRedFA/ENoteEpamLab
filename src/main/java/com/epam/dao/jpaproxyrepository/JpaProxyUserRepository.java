package com.epam.dao.jpaproxyrepository;

import com.epam.dao.entity.UserJpaEntity;
import com.epam.dao.jparepository.UserJpaRepository;
import com.epam.dao.mapper.UserMapper2;
import com.epam.models.User;
import com.epam.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.epam.dao.mapper.UserMapper;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class JpaProxyUserRepository implements UserRepository {

    @Autowired
    private UserJpaRepository jpaRepository;

//    @Autowired
//    private UserMapper2 userMapper2;

    @Override
    public User save(User newUser) {
        UserJpaEntity savedEntity = jpaRepository.save(UserMapper.toUserJpaEntity(newUser));
        return UserMapper.toUser(savedEntity);
    }

    @Override
    public void update(User user) {
        jpaRepository.save(UserMapper.toUserJpaEntity(user));
    }

    @Override
    public User update(long id, User user) {
        User updatedUser = getById(id);
        if (updatedUser != null) {
            updatedUser.setName(user.getName());
            updatedUser.setPassword(user.getPassword());
            jpaRepository.save(UserMapper.toUserJpaEntity(updatedUser));
        }
        return updatedUser;
    }

    @Override
    public void delete(User user) {
        jpaRepository.delete(UserMapper.toUserJpaEntity(user));
    }

    @Override
    public List<User> all() {
        List<UserJpaEntity> entities = jpaRepository.findAll();
        return UserMapper.toUsers(entities);
    }

    @Override
    public void deleteById(long userId) {
        jpaRepository.delete(userId);
    }

    @Override
    public User getById(long userId) {
        List<UserJpaEntity> entities = jpaRepository.findAll();
        for (UserJpaEntity entity : entities) {
            if (entity.getId() == userId) {
                return UserMapper.toUser(entity);
            }
        }
        return null;
    }

    @Override
    public User getByName(String name) {
        List<UserJpaEntity> entities = jpaRepository.findAll();
        for (UserJpaEntity entity : entities) {
            if (entity.getName()
                    .equalsIgnoreCase(name)) {
                return UserMapper.toUser(entity);
            }
        }
        return null;
    }
}