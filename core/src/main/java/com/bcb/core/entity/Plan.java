package com.bcb.core.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID id;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private double credits;
    @Column(name = "credit_limit", nullable = false)
    private double limit;

    protected Plan() {}

    public Plan(String type, double credits, double limit) {
        this.type    = type;
        this.credits = credits;
        this.limit   = limit;
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

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }
}
