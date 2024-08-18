package com.bcb.core.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String phoneNumber;
    private String text;
    private boolean isWhatsapp;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    protected Message() {}

    public Message(String phoneNumber, String text, boolean isWhatsapp, Client client) {
        this.phoneNumber = phoneNumber;
        this.text   = text;
        this.isWhatsapp = isWhatsapp;
        this.client = client;
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

    public void setPhoneNumberNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean getIsWhatsapp() {
        return isWhatsapp;
    }

    public void setIsWhatsapp(boolean isWhatsapp) {
        this.isWhatsapp = isWhatsapp;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getText(){
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
