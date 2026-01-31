package com.learning.domain.model;

import com.learning.domain.intent.IntentType;
import lombok.Data;

@Data
public class UserQuery {

    private final String userId;
    private final String message;
}
