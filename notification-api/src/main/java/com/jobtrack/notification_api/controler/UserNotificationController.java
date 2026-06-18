package com.jobtrack.notification_api.controler;

import com.jobtrack.notification_api.dto.RequestBodyNotificationDto;
import com.jobtrack.notification_api.model.NotificationLog;
import com.jobtrack.notification_api.repository.NotificationLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/notify/user")
public class UserNotificationController {

    @Autowired
    private NotificationLogRepository logRepository;

    @PostMapping("/send-verification")
    public ResponseEntity<String> receiveVerificationToken(@RequestBody RequestBodyNotificationDto request){
        String email = request.getEmail();
        String token = request.getToken();

        // 1. Create a log entry
        NotificationLog notificationLog = NotificationLog.builder() // Maps to recipient_email column
                .recipientEmail(email)
                .notificationType("VERIFICATION_EMAIL")   // Maps to notification_type column
                .sentAt(LocalDateTime.now()) // Maps to sent_at column
                .status("RECEIVED") // Maps to status column
                .build();

        // 2. Save to database
        logRepository.save(notificationLog);

        return ResponseEntity.ok("Notification request Received and Logged");
    }
}

