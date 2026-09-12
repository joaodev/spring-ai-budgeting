package com.joaodev.springaibudgeting.examples.aitools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class MathTools {
    @Tool(description = "soma dois inteiros, a e b")
    public int sum(int a, int b) {
        return a + b;
    }

    @Tool(description = "subtrai dois inteiros, a e b")
    public int diff(int a, int b) {
        return a - b;
    }
}
