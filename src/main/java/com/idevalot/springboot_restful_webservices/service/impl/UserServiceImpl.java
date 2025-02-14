package com.idevalot.springboot_restful_webservices.service.impl;

import com.idevalot.springboot_restful_webservices.dto.UserDto;
import com.idevalot.springboot_restful_webservices.entity.User;
import com.idevalot.springboot_restful_webservices.mapper.UserMapper;
import com.idevalot.springboot_restful_webservices.repository.UserRepository;
import com.idevalot.springboot_restful_webservices.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        //Covert UserDto to User JPA Entity
        User user = UserMapper.mapToUser(userDto);
        User savedUser = userRepository.save(user);
        // Convert User JPA Entity to UserDto
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new RuntimeException("User not found");
        }
        User user = optionalUser.get();

        return UserMapper.mapToUserDto(user);
    }
    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(UserMapper::mapToUserDto).toList();
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getFirstName());
        existingUser.setEmail(user.getEmail());
        User updateUser = userRepository.save(existingUser);
        return UserMapper.mapToUserDto(updateUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
