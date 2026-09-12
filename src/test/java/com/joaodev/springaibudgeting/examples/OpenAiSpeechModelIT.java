package com.joaodev.springaibudgeting.examples;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".*")
public class OpenAiSpeechModelIT {
    @Autowired
    OpenAiAudioSpeechModel OpenAiAudioSpeechModel;

    @Test
    void shouldProduceAudioWhenTextIsProvided() throws IOException {
        var response = OpenAiAudioSpeechModel.call("O valor total do serviço ficou em 80 reais. Posso confirmar o pagamento?");

        assertThat(response).isNotNull();
        assertThat(response).hasSizeGreaterThan(1024);

        var tempFile = Files.createTempFile("AUDIO_", ".mp3");
        Files.write(tempFile, response);

        assertThat(tempFile).exists();
        assertThat(tempFile).isRegularFile();

        // Comente a linha abaixo caso queira abrir o arquivo temporário na tmp
        Files.delete(tempFile);
    }
}
