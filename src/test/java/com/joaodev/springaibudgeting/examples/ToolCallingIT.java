package com.joaodev.springaibudgeting.examples;

import com.joaodev.springaibudgeting.examples.aitools.MathTools;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".*")
public class ToolCallingIT {
    @Autowired
    OpenAiChatModel openAiChatModel;

    @Autowired
    MathTools mathTools;

    @Test
    void shouldExecuteSumWhenPrompted() {
        var chatClient = ChatClient.builder(openAiChatModel)
                .defaultSystem("Você é um matemático")
                .defaultTools(mathTools)

                .build();

        var response = chatClient
                .prompt("Qual é a soma de 2 + 2? Exiba apenas o resultado final sem explicações.")
                .call().content();

        assertThat(response).contains("4");
    }

    @Test
    void shouldExecuteDiffWhenPrompted() {
        var chatClient = ChatClient.builder(openAiChatModel)
                .defaultSystem("Você é um matemático")
                .defaultTools(mathTools)
                .build();

        var response = chatClient
                .prompt("Qual é a diferença entre 5 e 3? Exiba apenas o resultado final sem explicações.")
                .call().content();

        assertThat(response).contains("2");
    }
}
