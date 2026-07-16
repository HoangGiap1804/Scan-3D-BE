package com.example.scan3d.dtos.request.auth;

import com.example.scan3d.dtos.request.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequest extends BaseDTO {
    @NotNull(message = "First name must be not null")
    private String firstName;

    @NotNull(message = "Last name must be not null")
    private String lastName;

    @JsonProperty("email")
    @NotNull(message = "Email must be not null")
    @Email(message = "Invalid email format")
    private String email;

    @JsonProperty("password")
    @NotNull(message = "Password must be not null")
    private String password;
}
