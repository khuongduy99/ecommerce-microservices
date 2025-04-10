package com.ms.authservice.dto;

import com.ms.authservice.entity.Role;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private Role role;
}
