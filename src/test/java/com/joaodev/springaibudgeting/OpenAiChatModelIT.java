package com.joaodev.springaibudgeting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatModel.ResponseFormat;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".*")
public class OpenAiChatModelIT {

    @Test
    void shouldReceiveResponseWhenChatModelIsCalled() {
        var options = OpenAiChatOptions.builder()
                .model("gpt-4o-mini")
                .temperature(0.5)
                .responseFormat(ResponseFormat.builder().type(ResponseFormat.Type.TEXT).build())
                .build();

        var chatModel = OpenAiChatModel.builder()
                .options(options)
                .build();

        var response = chatModel.call("Gere um registro de orçamento, com descrição de gasto, valor em reais e local");

        assertThat(response).isNotEmpty();
    }
}
