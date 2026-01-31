package com.learning.domain.model;

import lombok.Data;

@Data
public class UserQuery {

    private final String userId;
    private final String message;
}
