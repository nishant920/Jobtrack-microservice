package com.jobtrack.auth_api.repository;

import com.jobtrack.auth_api.verfication.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationRepository extends JpaRepository<VerificationToken, Long> {

    Optional<VerificationToken> findByToken(String token); //select token from VerificationToken

}
