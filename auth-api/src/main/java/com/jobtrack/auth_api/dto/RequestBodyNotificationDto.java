package com.jobtrack.auth_api.dto;

import lombok.Data;

@Data
public class RequestBodyNotificationDto {
    private String email;
    private String token;
}
