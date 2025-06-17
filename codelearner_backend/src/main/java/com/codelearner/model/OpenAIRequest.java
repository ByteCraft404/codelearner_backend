package com.codelearner.model;

import java.util.List;

public class OpenAIRequest {
    private String model;
    private List<Message> messages;

    public OpenAIRequest() {
    }

    public OpenAIRequest(String model, List<Message> messages) {
        this.model = model;
        this.messages = messages;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
