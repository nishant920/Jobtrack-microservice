package com.jobtrack.job_api.service;

import com.jobtrack.job_api.Dtos.JobDto;
import com.jobtrack.job_api.Dtos.JobResponseDto;
import com.jobtrack.job_api.Dtos.JobSatusResponseDto;
import com.jobtrack.job_api.Dtos.JobStatusDto;
import com.jobtrack.job_api.models.Job;
import com.jobtrack.job_api.repository.JobRepository;
import com.jobtrack.job_api.utility.Mapper;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class JobService {

        Mapper mapper;
        JobRepository jobRepository;
        public JobService(Mapper mapper, JobRepository jobRepository){
            this.mapper=mapper;
            this.jobRepository=jobRepository;
        }

        public JobResponseDto saveJob(JobDto jobDto){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if(authentication == null){
                throw new RuntimeException("User is not authorised");
            }

            Job saveJob = mapper.mapJobDtoToJob(jobDto);
            Object principal = authentication.getPrincipal();
            if(principal instanceof JobDto){
                String ownerEmail = principal.toString();
                saveJob.setOwnerEmail(ownerEmail);
            }

            JobResponseDto jobResponseDto = mapper.mapJobToJobResponse(saveJob);

            jobResponseDto.setResponseMessage("Job is added. You can track it in the Dashboard");

            return jobResponseDto;

        }
        @Transactional
        public JobSatusResponseDto updateStatusS(Long id, JobStatusDto jobStatusDto){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if(authentication == null){
                throw new RuntimeException("User is not authorised");
            }
            String email = authentication.getPrincipal().toString();

            Job job = jobRepository.findByIdAndOwnerEmail(id, email).orElseThrow(() -> new RuntimeException("Job not found"));

            if(jobStatusDto.getStatus() == null){
                throw new RuntimeException("Job Status is required");
            }


           job.setStatus(jobStatusDto.getStatus());

           return mapper.mapJobToJobStatusResponseDto(job);

        }
}
