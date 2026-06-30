package com.jobtrack.job_api.Dtos;

import com.jobtrack.job_api.models.JobStatus;
import lombok.Data;

@Data
public class JobSatusResponseDto {
    String jobProfile;
    JobStatus jobStatus;
}
