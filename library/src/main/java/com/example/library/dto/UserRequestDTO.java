package com.example.library.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestDTO {

    @NotBlank(message = "Name cannot be empty")
    private String name;
    @NotBlank(message = "Username cannot be empty")
    private String username;
    @NotBlank(message = "User email cannot be empty")
    private String email;
}
