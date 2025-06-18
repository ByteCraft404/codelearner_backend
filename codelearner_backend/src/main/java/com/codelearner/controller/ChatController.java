package com.codelearner.controller;

import com.codelearner.model.ChatRequest;
import com.codelearner.model.ChatResponse;
import com.google.gson.*;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Value("${gemini.api.key}")
    private String apiKey;

    private static final String GEMINI_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent";

    @PostMapping
    public ChatResponse askGemini(@RequestBody ChatRequest request) {

        OkHttpClient client = new OkHttpClient();

        // Prepare JSON payload
        JsonObject textPart = new JsonObject();
        textPart.addProperty("text", request.getQuestion());

        JsonObject content = new JsonObject();
        content.add("parts", new Gson().toJsonTree(Collections.singletonList(textPart)));

        JsonObject body = new JsonObject();
        body.add("contents", new Gson().toJsonTree(Collections.singletonList(content)));

        // Explicitly using okhttp3.RequestBody to avoid ambiguity
        okhttp3.RequestBody requestBody = okhttp3.RequestBody.create(
                body.toString(),
                MediaType.parse("application/json")
        );

        Request httpRequest = new Request.Builder()
                .url(GEMINI_URL + "?key=" + apiKey)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(httpRequest).execute()) {
            if (!response.isSuccessful()) {
                return new ChatResponse("Error: Gemini API failed with code " + response.code());
            }

            String responseBody = response.body() != null ? response.body().string() : "";
            if (responseBody.isEmpty()) {
                return new ChatResponse("Error: Empty response from Gemini API.");
            }

            JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();
            String reply = json.getAsJsonArray("candidates")
                    .get(0).getAsJsonObject()
                    .getAsJsonObject("content")
                    .getAsJsonArray("parts")
                    .get(0).getAsJsonObject()
                    .get("text").getAsString();

            return new ChatResponse(reply);

        } catch (Exception e) {
            return new ChatResponse("Error: " + e.getMessage());
        }
    }
}
