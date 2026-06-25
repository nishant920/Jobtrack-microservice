package com.jobtrack.job_api.repository;

import com.jobtrack.job_api.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
