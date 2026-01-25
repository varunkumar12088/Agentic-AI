package com.learning.dto.plain;

import lombok.Data;

import java.util.List;

@Data
public class ExecutionPlan {

    private String planId;
    private String intent;
    private List<PlanStep> steps;
}
