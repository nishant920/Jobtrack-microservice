package com.jobtrack.job_api.service;

import com.jobtrack.job_api.Dtos.JobDto;
import com.jobtrack.job_api.Dtos.JobResponseDto;
import com.jobtrack.job_api.models.Job;
import com.jobtrack.job_api.utility.Mapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class JobService {

        Mapper mapper;
        public JobService(Mapper mapper){
            this.mapper=mapper;
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
}
