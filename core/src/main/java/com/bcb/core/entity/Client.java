package com.bcb.core.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String email;
    private String phone;
    private String cpfResponsible;
    private String cnpj;
    private String companyName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "plan_id")
    private Plan plan;

    protected Client(){}

    public Client(String name, String email, String phone, String cpfResponsible, String cnpj, String companyName, Plan plan ) {
        this.name  = name;
        this.email = email;
        this.phone = phone;
        this.cpfResponsible = cpfResponsible;
        this.cnpj = cnpj;
        this.plan = plan;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCpfResponsible() {
        return cpfResponsible;
    }

    public void setCpfResponsible(String cpfResponsible) {
        this.cpfResponsible = cpfResponsible;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
