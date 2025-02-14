package com.idevalot.springboot_restful_webservices.service;

import com.idevalot.springboot_restful_webservices.dto.UserDto;
import com.idevalot.springboot_restful_webservices.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);

    User getUserById(Long id);

    List<User> getAllUsers();

    User updateUser(User user);

    void  deleteUser(Long id);
}
