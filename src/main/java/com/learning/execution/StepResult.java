package com.learning.execution;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StepResult {

    private boolean success;
    private String message;
    private Object data;

    public static StepResult success(String msg, Object data) {
        return new StepResult(true, msg, data);
    }

    public static StepResult failure(String msg) {
        return new StepResult(false, msg, null);
    }
}
