package com.example.scan3d.services;

import com.example.scan3d.dtos.request.UserDTO;
import com.example.scan3d.dtos.request.auth.RegisterRequest;
import com.example.scan3d.dtos.response.AuthResponse;
import com.example.scan3d.entities.Auth;
import com.example.scan3d.mappers.AuthMapper;
import com.example.scan3d.repositories.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final AuthMapper authMapper;
    private final KeycloakService keycloakService;

    public AuthResponse register(RegisterRequest registerRequest){

        Auth auth = authMapper.toEntity(registerRequest);

        auth.setUserName(registerRequest.getEmail());

        UUID userKeycloakId = keycloakService.createUser(auth.getUserName(), auth.getEmail(), auth.getFirstName(), auth.getLastName(), registerRequest.getPassword());

        auth.setUserKeycloakId(userKeycloakId);

        authRepository.save(auth);

        return authMapper.toAuthResponse(auth);
    }
}
