package com.fixtab.user.service;

import java.util.List;

import com.fixtab.user.model.dto.UserDTO;

public interface UserService {

    public void createUser(UserDTO userDTO);

    public List<UserDTO> getAllUsers();

    public UserDTO getCurrentUser(Long id);
}
