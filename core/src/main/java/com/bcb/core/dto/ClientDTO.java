package com.bcb.core.dto;

import jakarta.validation.constraints.*;

import java.util.UUID;

public class ClientDTO {
    private UUID id;

    @NotNull(message = "Nome não pode ser nulo")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String name;

    @NotNull(message = "Email não pode ser nulo")
    @Email(message = "Email deve ser válido")
    private String email;

    @NotNull(message = "Telefone não pode ser nulo")
    @Size(min = 10, max = 15, message = "Telefone deve ter entre 10 e 15 caracteres")
    private String phone;

    @NotBlank(message = "CPF do responsável não pode estar em branco")
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos")
    private String cpfResponsible;

    @NotBlank(message = "CNPJ não pode estar em branco")
    @Pattern(regexp = "\\d{14}", message = "CNPJ deve conter 14 dígitos")
    private String cnpj;

    @Size(max = 100, message = "Nome da empresa deve ter no máximo 100 caracteres")
    private String companyName;

    @NotNull(message = "ID do plano não pode ser nulo")
    private UUID planId;

    public ClientDTO() {}

    public ClientDTO(UUID id, String name, String email, String phone, String cpfResponsible, String cnpj, String companyName, UUID planId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.cpfResponsible = cpfResponsible;
        this.cnpj = cnpj;
        this.companyName = companyName;
        this.planId = planId;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public UUID getPlanId() {
        return planId;
    }

    public void setPlanId(UUID planId) {
        this.planId = planId;
    }
}
