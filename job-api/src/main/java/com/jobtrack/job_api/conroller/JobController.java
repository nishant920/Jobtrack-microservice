package com.jobtrack.job_api.conroller;

import com.jobtrack.job_api.Dtos.JobDto;
import com.jobtrack.job_api.Dtos.JobResponseDto;
import com.jobtrack.job_api.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/job")
public class JobController {

        JobService jobService;

        public JobController(JobService jobService){
            this.jobService=jobService;
        }
        @PostMapping("/save")
        public ResponseEntity<?> createJob(@RequestBody JobDto jobDto){
            try {
                JobResponseDto jobResponseDto = jobService.saveJob(jobDto);
                return new ResponseEntity<>(jobResponseDto, HttpStatus.CREATED);
            } catch (RuntimeException e) {
                throw new RuntimeException(e.getMessage());
            }

        }
}