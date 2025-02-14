package com.idevalot.springboot_restful_webservices.mapper;

import com.idevalot.springboot_restful_webservices.dto.UserDto;
import com.idevalot.springboot_restful_webservices.entity.User;

public class UserMapper {

    public static UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );

        return  userDto;
    }

    public static User mapToUser(UserDto userDto) {
        User user = new User(
          userDto.getId(),
          userDto.getFirstName(),
          userDto.getLastName(),
          userDto.getEmail()
        );

        return user;
    }

}
