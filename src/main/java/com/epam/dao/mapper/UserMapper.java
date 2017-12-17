package com.epam.dao.mapper;

import com.epam.dao.entity.UserJpaEntity;
import com.epam.models.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    User userEntityToUser(UserJpaEntity user);

    List<User> userEntitiesToUsers(List<UserJpaEntity> users);

    UserJpaEntity userToUserEntity(User user);

    List<UserJpaEntity> usersToUserEntities(List<User> users);
}
