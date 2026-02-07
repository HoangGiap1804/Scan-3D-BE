package com.example.scan3d.dtos.response;

import com.example.scan3d.dtos.request.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthResponse extends BaseDTO {
    @JsonProperty("email")
    private String email;
}
