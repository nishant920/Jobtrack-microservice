package com.jobtrack.job_api.repository;

import com.jobtrack.job_api.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Long> {


    Optional<Job> findByIdAndOwnerEmail(Long id, String ownerEmail);
}
