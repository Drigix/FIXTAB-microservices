package com.fixtab.user.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fixtab.user.mapper.UserMapper;
import com.fixtab.user.model.User;
import com.fixtab.user.model.dto.UserDTO;
import com.fixtab.user.repository.UserRepository;
import com.fixtab.user.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void createUser(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(userDTO.getPassword());

        userRepository.save(user);
        log.info("User {} is created", user.getId());
    }
    
    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::toDto).toList();
        // List<User> users = new ArrayList<User>();
        // return users.stream().map(UserMapper::toDto).toList();
    }
}
