package com.jobtrack.auth_api.verfication;

import com.jobtrack.auth_api.model.AppUser;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    private LocalDateTime expiryDate;

    @OneToOne
    private AppUser user;
}
