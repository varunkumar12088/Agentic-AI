package com.learning.dto.plain;

import com.learning.constant.Action;
import com.learning.constant.IntentType;
import lombok.Data;

@Data
public class PlanStep {

    private int stepNumber;
    private Action action;
    private IntentType target;
    private String query;
    private String description;
    private boolean mandatory;
}
