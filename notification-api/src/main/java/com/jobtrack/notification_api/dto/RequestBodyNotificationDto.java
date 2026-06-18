package com.jobtrack.notification_api.dto;

import lombok.Data;

@Data
public class RequestBodyNotificationDto {
    private String email;
    private String token;
}
