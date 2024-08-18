package com.bcb.core.service;

import com.bcb.core.dto.ClientDTO;
import com.bcb.core.entity.Client;
import com.bcb.core.entity.Plan;
import com.bcb.core.repository.ClientRepository;
import com.bcb.core.repository.PlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final PlanRepository planRepository;

    public ClientService(ClientRepository clientRepository, PlanRepository planRepository) {
        this.clientRepository = clientRepository;
        this.planRepository = planRepository;
    }

    public Client clientRegister(Client client) {
        Plan plan = client.getPlan();
        if (plan == null) {
            throw new IllegalArgumentException("O cliente precisar ter um plano associado.");
        }
        Optional<Plan> existingPlan = planRepository.findById(plan.getId());
        if (existingPlan.isEmpty()) {
            throw new IllegalArgumentException("O plano associado ao cliente não foi encontrado. Verifique!");
        }
        client.setPlan(existingPlan.get());
        return clientRepository.save(client);
    }

    public Client updateClient(UUID id, ClientDTO clientDTO) throws Exception {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new Exception("Cliente não encontrado"));

        existingClient.setName(clientDTO.getName());
        existingClient.setEmail(clientDTO.getEmail());
        existingClient.setPhone(clientDTO.getPhone());
        existingClient.setCpfResponsible(clientDTO.getCpfResponsible());
        existingClient.setCnpj(clientDTO.getCnpj());
        existingClient.setCompanyName(clientDTO.getCompanyName());
        existingClient.setPlan(planRepository.findById(clientDTO.getPlanId())
                .orElseThrow(() -> new Exception("Plano não encontrado")));

        return clientRepository.save(existingClient);
    }

    public Optional<Client> getClientById(UUID id) {
        return clientRepository.findById(id);
    }

    public List<Client> listAllClients() {
        return clientRepository.findAll();
    }

    public void deleteClient(UUID id) {
        clientRepository.deleteById(id);
    }
}
