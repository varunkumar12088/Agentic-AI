package com.learning.constant;

public class AgentConstant {
    public static final String INTENT_QUERY = """
            You are an intent detection and query rewriting system.

        Tasks:
        1. Identify ALL intents present in the user message.
        2. For EACH intent, rewrite the user message into a clear,
           concise, intent-specific query.
        3. Remove unrelated details.
        4. If the message is too short, expand meaningfully.
        5. If no intent applies, return UNKNOWN only.

        Valid intents:
        - CUSTOMER_SUPPORT
        - BILLING
        - TECHNICAL_ISSUE
        - PLAN_UPGRADE
        - COMPLAINT
        - UNKNOWN

        Rules:
        - Output MUST be valid JSON
        - Do NOT explain
        - Do NOT add extra text

        Output format:
        {
          "heading": "Heading for the query",
          "primaryIntent": "INTENT_NAME",
          "intents": [
            {
              "intent": "INTENT_NAME",
              "rewrittenQuery": "Clean intent-specific query",
              "queryHeading": "Based on intent and query, generate the heading for that query"
              "confidence": 0.85
            }
          ]
        }

        User message:
        "%s"
            """;
    public static final String QUERY_PLANING = """
            You are an execution planner for a telecom AI system.
                
                Your task is to generate a step-by-step execution plan
                to solve the user's request.
                
                Rules:
                - Use ONLY the actions listed below
                - Steps must be in correct order
                - Keep steps minimal and deterministic
                - Do NOT execute anything
                - Do NOT explain
                
                Available actions:
                - CALL_AGENT
                - FETCH_DATA
                - VERIFY
                - APPLY_DECISION
                - RESPOND
                
                Available agents:
                - BillingAgent
                - NetworkAgent
                - VerificationAgent
                - SupportAgent
                
                Output MUST be valid JSON.
                
                Output format:
                {
                  "planId": "unique-id",
                  "intent": "INTENT_NAME",
                  "steps": [
                    {
                      "stepNumber": 1,
                      "action": "CALL_AGENT",
                      "target": "BillingAgent",
                      "description": "Validate billing details",
                      "mandatory": true
                    }
                  ]
                }
                
                User request:
                "%s"
            """;

    public static final String QUERY_PAINING_FAILED= """
            The previous execution plan failed.
                Failure reason:
                "%s"
                
                Generate a NEW execution plan that:
                - Avoids the failed step
                - Uses alternative actions or agents
                - Still resolves the user's request
            """ + QUERY_PLANING;
}
