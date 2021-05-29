package com.notifire.server.model;

public class MessageResponse {

    private String statusMessage;

    public MessageResponse() {
    }

    public MessageResponse(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
