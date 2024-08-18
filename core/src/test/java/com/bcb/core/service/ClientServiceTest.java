package com.bcb.core.service;

import com.bcb.core.entity.Client;
import com.bcb.core.entity.Plan;
import com.bcb.core.repository.ClientRepository;
import com.bcb.core.repository.PlanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private PlanRepository planRepository;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testClientRegisterSucessfully() {
        Plan plan = new Plan("POST_PAID", 100.0, 0.0);

        Client client = new Client("Marcos Paulo", "teste@teste.com", "999546546","123.456.789-10", "51.620.194/0001-69", "Empresa Teste", plan);

        when(planRepository.save(any(Plan.class))).thenReturn(plan);
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Client registeredClient = clientService.clientRegister(client);

        assertNotNull(registeredClient);
        assertNotNull(registeredClient.getPlan());
        assertEquals("POST_PAID", registeredClient.getPlan().getType());
        assertEquals(1000.0, registeredClient.getPlan().getLimit());
    }

    @Test
    void testClientRegisterThrowsExceptionWhenPlanIsNull() {
        Client client = new Client("Paulo", "teste2@teste.com", "999546546","123.456.789-10", "51.620.194/0001-69", "Empresa Teste", null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            clientService.clientRegister(client);
        });

        assertEquals("O cliente deve ter um plano associado", exception.getMessage());
    }

    @Test
    void testGetClientByIdSuccessfully() {
        UUID clientId = UUID.randomUUID();
        Plan plan = new Plan("POST_PAID", 1000.0, 0.0);
        Client client = new Client("Nome do Cliente", "email@cliente.com", "123456789", "123.456.789-00", "12.345.678/0001-00", "Nome da Empresa", plan);

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        Optional<Client> retrievedClient = clientService.getClientById(clientId);

        assertTrue(retrievedClient.isPresent());
        assertEquals("Nome do Cliente", retrievedClient.get().getName());
    }

    @Test
    void testGetClientByIdNotFound() {
        UUID clientId = UUID.randomUUID();

        when(clientRepository.findById(clientId)).thenReturn(Optional.empty());

        Optional<Client> retrievedClient = clientService.getClientById(clientId);

        assertFalse(retrievedClient.isPresent());
    }

    @Test
    void testDeleteClient() {
        UUID clientId = UUID.randomUUID();

        when(clientRepository.existsById(clientId)).thenReturn(true);

        clientService.deleteClient(clientId);

        assertDoesNotThrow(() -> clientService.deleteClient(clientId));
    }
}
