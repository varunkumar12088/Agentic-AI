package com.learning.dto.plain;

import lombok.Data;

@Data
public class PlanStep {

    private int stepNumber;
    private String action;
    private String target;
    private String description;
    private boolean mandatory;
}
