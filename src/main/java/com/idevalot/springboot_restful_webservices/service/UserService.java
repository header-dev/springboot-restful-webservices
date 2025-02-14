package com.idevalot.springboot_restful_webservices.service;

import com.idevalot.springboot_restful_webservices.dto.UserDto;
import com.idevalot.springboot_restful_webservices.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);

    UserDto getUserById(Long id);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto user);

    void  deleteUser(Long id);
}
