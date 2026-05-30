package com.jobtrack.auth_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "appUser")
@Data
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    @Column(name = "verify")
    private boolean isVerified;

}
