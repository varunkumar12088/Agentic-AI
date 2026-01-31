package com.learning.registry;

import com.learning.agents.SpecializedAgent;
import com.learning.constant.IntentType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class AgentRegistry {

    private final Map<IntentType, SpecializedAgent> agents;

    public AgentRegistry(List<SpecializedAgent> agentList) {
        this.agents = agentList.stream()
                .collect(Collectors.toMap(
                        a -> a.supportedIntentType(),
                        a -> a
                ));
    }

    public SpecializedAgent get(IntentType intentType) {
        return agents.get(intentType);
    }
}
