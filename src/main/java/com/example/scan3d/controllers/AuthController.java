package com.example.scan3d.controllers;

import com.example.scan3d.dtos.request.UserDTO;
import com.example.scan3d.dtos.request.auth.RegisterRequest;
import com.example.scan3d.dtos.response.AuthResponse;
import com.example.scan3d.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    private ResponseEntity<AuthResponse> regiester(
            @Valid @RequestBody RegisterRequest registerRequest
            ){

        AuthResponse authResponse = authService.register(registerRequest);
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED) ;
    }
}
