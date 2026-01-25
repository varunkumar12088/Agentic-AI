package com.learning.agents;

import com.learning.domain.model.UserQuery;
import com.learning.dto.IntentDetectionResult;

public interface IntentDetectionAgent {

    IntentDetectionResult detect(UserQuery query);
}
