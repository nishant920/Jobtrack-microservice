package com.jobtrack.auth_api.dto;

import lombok.Data;

@Data
public class UserResponseDto {
    private String name;
    private String email;
    private boolean verified;

}
