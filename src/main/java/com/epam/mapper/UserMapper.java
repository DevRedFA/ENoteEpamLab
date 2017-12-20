package com.epam.mapper;

import com.epam.dao.entity.UserJpaEntity;
import com.epam.dto.UserDto;
import com.epam.models.User;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    private UserMapper() {
    }

    private static ModelMapper modelMapper = new ModelMapper();

    public static User toUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public static UserDto toUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public static User toUser(UserJpaEntity userJpaEntity) {
        return modelMapper.map(userJpaEntity, User.class);
    }

    public static UserJpaEntity toUserJpaEntity(User user) {

        modelMapper.getTypeMaps();
        return modelMapper.map(user, UserJpaEntity.class);
    }

    public static List<UserJpaEntity> toUserJpaEntities(List<User> users) {
        List<UserJpaEntity> list = new ArrayList<>();
        for (User user : users) {
            list.add(modelMapper.map(user, UserJpaEntity.class));
        }
        return list;
    }

    public static List<User> toUsers(List<UserJpaEntity> users) {
        List<User> list = new ArrayList<>();
        for (UserJpaEntity user : users) {
            list.add(modelMapper.map(user, User.class));
        }
        return list;
    }
}
