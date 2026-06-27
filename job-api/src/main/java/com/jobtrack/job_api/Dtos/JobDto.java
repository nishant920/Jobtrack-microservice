package com.jobtrack.job_api.Dtos;

import com.jobtrack.job_api.models.JobStatus;
import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class JobDto {
    private String companyName;


    private String jobTitle;

    private String location;
    private String platform;
    private String jobUrl;
    private BigDecimal salary;
    private Integer experience;

    private JobStatus status;


    private LocalDate appliedDate;

    private List<String> skillSet = new ArrayList<>();


    private String notes;
}
