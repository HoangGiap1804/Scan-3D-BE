package com.example.scan3d.controllers;

import com.example.scan3d.dtos.request.UserDTO;
import com.example.scan3d.dtos.request.UserRequest;
import com.example.scan3d.entities.User;
import com.example.scan3d.mappers.UserMapper;
import com.example.scan3d.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserController(UserRepository userRepository, UserMapper userMapper){
       this.userRepository = userRepository;
       this.userMapper = userMapper;
    }

    @GetMapping("/{id}")
    private ResponseEntity<UserDTO> getUser(
            @PathVariable UUID id
    ){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        UserDTO userDTO = userMapper.toDTO(user);
        return new ResponseEntity<>(userDTO,HttpStatus.OK);
    }

    @PutMapping()
    private ResponseEntity<UserDTO> getUser(
            @RequestBody UserDTO userDTO
    ){
        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        UserDTO userResponse = userMapper.toDTO(user);
        return new ResponseEntity<>(userResponse,HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UserDTO> createUser(
            @Valid @RequestBody UserRequest userRequest
    ){
        User user = userMapper.requestToUser(userRequest);
        user = userRepository.save(user);
        UserDTO userResponse = userMapper.toDTO(user);
        return new ResponseEntity<>(userResponse,HttpStatus.OK);
    }
}
