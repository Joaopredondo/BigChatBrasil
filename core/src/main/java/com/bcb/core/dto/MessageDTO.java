package com.bcb.core.dto;

import java.util.UUID;

public class MessageDTO {
    private UUID id;
    private String phoneNumber;
    private boolean isWhatsApp;
    private String text;
    private UUID clientId;

    public MessageDTO() {}

    public MessageDTO(UUID id, String phoneNumber, boolean isWhatsApp, String text, UUID clientId) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.isWhatsApp = isWhatsApp;
        this.text = text;
        this.clientId = clientId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isWhatsApp() {
        return isWhatsApp;
    }

    public void setWhatsApp(boolean isWhatsApp) {
        this.isWhatsApp = isWhatsApp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }
}
