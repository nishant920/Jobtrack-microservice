package com.jobtrack.job_api.Dtos;

import lombok.Data;

@Data
public class JobResponseDto {

    private String ResponseMessage;

    private String jobTitle;

    private String company;
}
