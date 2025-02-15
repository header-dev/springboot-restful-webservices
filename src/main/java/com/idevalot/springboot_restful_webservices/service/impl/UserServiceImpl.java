package com.idevalot.springboot_restful_webservices.service.impl;

import com.idevalot.springboot_restful_webservices.dto.UserDto;
import com.idevalot.springboot_restful_webservices.entity.User;
import com.idevalot.springboot_restful_webservices.mapper.AutoUserMapper;
import com.idevalot.springboot_restful_webservices.mapper.UserMapper;
import com.idevalot.springboot_restful_webservices.repository.UserRepository;
import com.idevalot.springboot_restful_webservices.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        // Covert UserDto to User JPA Entity
        // User user = UserMapper.mapToUser(userDto);

        // Using Model Mapper Library
        // User user = modelMapper.map(userDto, User.class);

        User user = AutoUserMapper.MAPPER.mapToUser(userDto);

        User savedUser = userRepository.save(user);
        // Convert User JPA Entity to UserDto
        // Using Model Mapper Library
        // UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);

        return AutoUserMapper.MAPPER.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new RuntimeException("User not found");
        }
        // User user = optionalUser.get();
        // return UserMapper.mapToUserDto(user);
        // using Model Mapper Library
        // return modelMapper.map(user, UserDto.class);
        return  AutoUserMapper.MAPPER.mapToUserDto(optionalUser.get());
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        // return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
        /* Using Model Mapper Library
        return  users.stream().map((user) -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
         */

        return  users.stream().map((user) -> AutoUserMapper.MAPPER.mapToUserDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getFirstName());
        existingUser.setEmail(user.getEmail());
        User updateUser = userRepository.save(existingUser);
        // return UserMapper.mapToUserDto(updateUser);
        // Using Model Mapper Library
        // return modelMapper.map(updateUser, UserDto.class);
        return  AutoUserMapper.MAPPER.mapToUserDto(updateUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
