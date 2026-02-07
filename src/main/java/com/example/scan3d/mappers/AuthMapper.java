package com.example.scan3d.mappers;

import com.example.scan3d.dtos.request.auth.RegisterRequest;
import com.example.scan3d.dtos.response.AuthResponse;
import com.example.scan3d.entities.Auth;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper extends GenericMapper <Auth, RegisterRequest>{
    AuthResponse toAuthResponse(Auth auth);
}
