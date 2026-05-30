package com.jobtrack.auth_api.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class AppUserDto {
    private String name;
    private String email;
    private String password;
}
