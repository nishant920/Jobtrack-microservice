package com.jobtrack.auth_api.connector;

import com.jobtrack.auth_api.dto.RequestBodyNotificationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class NotificationApiConnector {

    @Autowired
    RestTemplate restTemplate;

    // This name must match the 'spring.application.name' in notification-api's properties
    private final String NOTIFICATION_SERVICE_URL = "http://notification-api/api/v1/notify/user";
    public void sendVerification(String email, String token){
        RequestBodyNotificationDto request = new RequestBodyNotificationDto();
        request.setEmail(email);
        request.setToken(token);
        try {
            restTemplate.postForEntity(NOTIFICATION_SERVICE_URL + "/send-verification", request, String.class);

            log.info("Successfully sent verification request to Notification API");
        }catch (Exception e){
            log.error("Failed to connect to Notification API: " + e.getMessage() );
        }
    }

}
