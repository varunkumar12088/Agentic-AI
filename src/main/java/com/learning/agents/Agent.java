package com.learning.agents;

public interface Agent<I, O> {

    O execute(I input);
}
