package com.fixtab.user.controller;

import java.util.List;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.fixtab.user.model.dto.UserDTO;
import com.fixtab.user.service.UserService;
import com.fixtab.user.service.Impl.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/user")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @CircuitBreaker(name = "company", fallbackMethod = "fallbackMethod")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getCurrentUser(@PathVariable Long id){ return userService.getCurrentUser(id); }

    public String fallbackMethod(Long id, RuntimeException runtimeException) {
        return "Something went wrong!";
    }
}
