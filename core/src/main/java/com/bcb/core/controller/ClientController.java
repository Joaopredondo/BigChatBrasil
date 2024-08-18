package com.bcb.core.controller;

import com.bcb.core.dto.ClientDTO;
import com.bcb.core.dto.PlanDTO;
import com.bcb.core.entity.Client;
import com.bcb.core.entity.Plan;
import com.bcb.core.service.ClientService;
import com.bcb.core.service.PlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;
    private final PlanService planService;

    public ClientController(ClientService clientService, PlanService planService) {
        this.clientService = clientService;
        this.planService = planService;
    }

    @PostMapping
    public ResponseEntity<ClientDTO> registerClient(@RequestBody ClientDTO clientDTO) {
        Plan plan = planService.getPlanById(clientDTO.getPlanId()).orElse(null);
        Client client = new Client(clientDTO.getName(), clientDTO.getEmail(), clientDTO.getPhone(),
                                   clientDTO.getCpfResponsible(),clientDTO.getCnpj(),clientDTO.getCompanyName(),plan);

        Client newClient = clientService.clientRegister(client);

        ClientDTO newClientDTO = new ClientDTO(newClient.getId(), newClient.getName(), newClient.getEmail(),
                                               newClient.getPhone(), newClient.getCpfResponsible(),
                                               newClient.getCnpj(), newClient.getCompanyName(),
                                               newClient.getPlan() != null ? newClient.getPlan().getId() : null);
        return ResponseEntity.ok(newClientDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable UUID id) {
        Optional<Client> client = clientService.getClientById(id);

        return client.map(value -> {
            ClientDTO clientDTO = new ClientDTO(value.getId(), value.getName(), value.getEmail(), value.getPhone(),
                                                value.getCpfResponsible(), value.getCnpj(), value.getCompanyName(),
                                                value.getPlan() != null ? value.getPlan().getId() : null);
            return ResponseEntity.ok(clientDTO);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        List<Client> clients = clientService.listAllClients();

        List<ClientDTO> clientDTOS = clients.stream()
                .map(client -> new ClientDTO(client.getId(), client.getName(), client.getEmail(),
                                             client.getPhone(), client.getCpfResponsible(), client.getCnpj(),
                                             client.getCompanyName(), client.getPlan() != null ? client.getPlan().getId():null))
                .toList();

        return ResponseEntity.ok(clientDTOS);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable UUID id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

}
