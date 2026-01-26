package com.learning.dto.plain;

import com.learning.domain.intent.IntentType;
import lombok.Data;

@Data
public class PlanStep {

    private int stepNumber;
    private String action;
    private IntentType target;
    private String description;
    private boolean mandatory;
}
