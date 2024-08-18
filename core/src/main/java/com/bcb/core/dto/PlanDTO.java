package com.bcb.core.dto;

import java.util.UUID;

public class PlanDTO {
    private UUID id;
    private String type;
    private double limit;
    private double credits;

    public PlanDTO() {}

    public PlanDTO(UUID id, String type, double limit, double credits) {
        this.id = id;
        this.type = type;
        this.limit = limit;
        this.credits = credits;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }
}
