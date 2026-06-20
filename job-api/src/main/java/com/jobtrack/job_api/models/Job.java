package com.jobtrack.job_api.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "job")
@Data
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;



}
