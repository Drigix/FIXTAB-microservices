package com.fixtab.user.service.Impl;

import java.util.ArrayList;
import java.util.List;

import com.fixtab.user.event.UserEvent;
import com.fixtab.user.model.dto.Company;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fixtab.user.mapper.UserMapper;
import com.fixtab.user.model.User;
import com.fixtab.user.model.dto.UserDTO;
import com.fixtab.user.repository.UserRepository;
import com.fixtab.user.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final WebClient webClient;

    private final KafkaTemplate<String, UserEvent> kafkaTemplate;

    @Override
    public void createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);

        userRepository.save(user);
        log.info("User {} is created", user.getId());
    }
    
    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toDto).toList();
    }

    @Override
    public UserDTO getCurrentUser(Long id) {
        User user = userRepository.getById(id);
        Company company = webClient.get()
                .uri("http://localhost:8082/api/company/user-company",
                        uriBuilder -> uriBuilder.queryParam("id", user.getCompanyId()).build())
                .retrieve()
                .bodyToMono(Company.class)
                .block();

        log.info("User company is {}", company.getName());
        kafkaTemplate.send("notificationTopic", new UserEvent(user.getId()));

        return userMapper.toDto(user);
    }

}
