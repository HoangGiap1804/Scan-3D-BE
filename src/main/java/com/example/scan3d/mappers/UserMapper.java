package com.example.scan3d.mappers;

import com.example.scan3d.dtos.request.UserDTO;
import com.example.scan3d.dtos.request.UserRequest;
import com.example.scan3d.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<User, UserDTO> {
    User requestToUser (UserRequest userRequest);
}
