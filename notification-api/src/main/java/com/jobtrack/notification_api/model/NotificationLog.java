package com.jobtrack.notification_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recipientEmail;

    private String notificationType; // e.g., "VERIFICATION_EMAIL"

    private LocalDateTime sentAt;

    private String status; // e.g., "SENT", "FAILED"

    private String errorMessage;
}
