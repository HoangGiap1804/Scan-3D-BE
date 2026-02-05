package com.example.scan3d.controllers;

import com.example.scan3d.dtos.request.UserDTO;
import com.example.scan3d.dtos.request.ValidationExampleRequest;
import com.example.scan3d.entities.User;
import com.example.scan3d.mappers.UserMapper;
import com.example.scan3d.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("/test")
public class TestAPI {

    final private UserRepository userRepository;
    final private UserMapper userMapper;

    public TestAPI(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @GetMapping("/hello/{testId}")
    private ResponseEntity<UserDTO> TestAPI(
            @PathVariable UUID testId
            ){
        User user = userRepository.getUserById(testId);
        UserDTO userDTO = userMapper.toDTO(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
