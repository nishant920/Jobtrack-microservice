package com.jobtrack.job_api.conroller;

import com.jobtrack.job_api.Dtos.JobDto;
import com.jobtrack.job_api.Dtos.JobResponseDto;
import com.jobtrack.job_api.Dtos.JobSatusResponseDto;
import com.jobtrack.job_api.Dtos.JobStatusDto;
import com.jobtrack.job_api.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateJobStatus(@PathVariable Long id,  @RequestBody JobStatusDto jobStatusDto){
        JobSatusResponseDto updatedJob = jobService.updateStatusS(id, jobStatusDto);
        return new ResponseEntity<>(updatedJob, HttpStatus.OK);
    }
}