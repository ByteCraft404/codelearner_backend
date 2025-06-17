package com.codelearner.service;

import com.codelearner.config.OpenAIConfig;
import com.codelearner.model.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class OpenAIService {

    private final OpenAIConfig config;

    public OpenAIService(OpenAIConfig config) {
        this.config = config;
    }

    private final String OPENAI_URL = "https://api.openai.com/v1/chat/completions";

    public String askQuestion(String question) {
        RestTemplate restTemplate = new RestTemplate();

        Message userMessage = new Message("user", question);
        OpenAIRequest request = new OpenAIRequest("gpt-3.5-turbo", Collections.singletonList(userMessage));


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(config.getKey()); // Use the key from OpenAIConfig

        HttpEntity<OpenAIRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<OpenAIResponse> response = restTemplate.exchange(
                OPENAI_URL,
                HttpMethod.POST,
                entity,
                OpenAIResponse.class
        );

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return response.getBody().getChoices().get(0).getMessage().getContent().trim();
        }

        return "Sorry, I couldn't get a response from the AI.";
    }
}
