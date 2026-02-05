package com.example.scan3d.dtos.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank(message = "Username is required")
    @JsonProperty("user_name")
    private String userName;
    @NotBlank(message = "Email is required")
    private String email;
}
