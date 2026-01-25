package com.learning.registry;

import com.learning.agents.SpecializedAgent;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AgentRegistry {

    private final Map<String, SpecializedAgent> agents;

    public AgentRegistry(List<SpecializedAgent> agentList) {
        this.agents = agentList.stream()
                .collect(Collectors.toMap(
                        a -> a.getClass().getSimpleName(),
                        a -> a
                ));
    }

    public SpecializedAgent get(String agentName) {
        return agents.get(agentName);
    }
}
