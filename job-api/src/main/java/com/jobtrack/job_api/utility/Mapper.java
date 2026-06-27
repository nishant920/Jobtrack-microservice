package com.jobtrack.job_api.utility;

import com.jobtrack.job_api.Dtos.JobDto;
import com.jobtrack.job_api.Dtos.JobResponseDto;
import com.jobtrack.job_api.models.Job;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public Job mapJobDtoToJob(JobDto jobDto){
        Job job = new Job();
        job.setJobTitle(job.getJobTitle());
        job.setCompanyName(jobDto.getCompanyName());
        job.setAppliedDate(jobDto.getAppliedDate());
        job.setExperience(jobDto.getExperience());
        job.setLocation(jobDto.getLocation());
        job.setExperience(jobDto.getExperience());
        job.setPlatform(jobDto.getPlatform());
        job.setJobUrl(jobDto.getJobUrl());
        job.setSkillSet(jobDto.getSkillSet());
        job.setSalary(jobDto.getSalary());
        job.setNotes(jobDto.getNotes());

        return job;
    }

    public JobResponseDto mapJobToJobResponse(Job job){
        JobResponseDto jobResponseDto = new JobResponseDto();
        jobResponseDto.setJobTitle(job.getJobTitle());
        jobResponseDto.setCompany(jobResponseDto.getCompany());
        return jobResponseDto;
    }
}
