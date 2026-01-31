package com.learning.dto.plain;

import lombok.Data;

import java.util.List;

@Data
public class ExecutionPlan {

    private String planId;
    private List<PlanStep> steps;
}
