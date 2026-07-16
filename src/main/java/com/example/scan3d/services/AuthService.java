package com.example.scan3d.services;

import com.example.scan3d.dtos.request.auth.RegisterRequest;
import com.example.scan3d.dtos.response.AuthResponse;
import com.example.scan3d.entities.Auth;
import com.example.scan3d.mappers.AuthMapper;
import com.example.scan3d.models.response.errors.BadRequestException;
import com.example.scan3d.repositories.AuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final AuthRepository authRepository;
    private final AuthMapper authMapper;
    private final KeycloakService keycloakService;

    @Transactional
    public AuthResponse register(RegisterRequest registerRequest) {

        if (authRepository.existsByEmail(registerRequest.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        Auth auth = authMapper.toEntity(registerRequest);
        auth.setUserName(registerRequest.getEmail());

        UUID userKeycloakId = keycloakService.createUser(
                auth.getUserName(),
                auth.getEmail(),
                auth.getFirstName(),
                auth.getLastName(),
                registerRequest.getPassword()
        );

        auth.setUserKeycloakId(userKeycloakId);

        try {
            authRepository.save(auth);
        } catch (DataIntegrityViolationException ex) {
            log.error("Failed to save user after Keycloak creation. Keycloak ID: {}", userKeycloakId, ex);
            keycloakService.deleteUser(userKeycloakId);
            throw new BadRequestException("User registration failed. The email or username may already be in use.");
        }

        return authMapper.toAuthResponse(auth);
    }
}
