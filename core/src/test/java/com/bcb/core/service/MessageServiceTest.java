package com.bcb.core.service;

import com.bcb.core.entity.Client;
import com.bcb.core.entity.Message;
import com.bcb.core.entity.Plan;
import com.bcb.core.repository.ClientRepository;
import com.bcb.core.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private MessageService messageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendMessageWithPrePaidPlanSuccessfully() throws Exception {
        Plan plan = new Plan("PRE_PAID", 0.0, 1.0);

        Client client = new Client("Jorge", "jorge@cliente.com", "123456789", "123.456.789-00", "12.345.678/0001-00", "JorgeFoods", plan);

        Message message = new Message("987654321", "Teste Mensagem", true , client);

        when(clientRepository.save(any(Client.class))).thenReturn(client);
        when(messageRepository.save(any(Message.class))).thenReturn(message);

        messageService.sendMessage(message);

        assertEquals(0.75, plan.getCredits());
    }

    @Test
    void testSendMessageWithPostPaidPlanSuccessfully() throws Exception {
        Plan plan = new Plan("POST_PAID", 1000.0, 0.0); // Limite de 1000

        Client client = new Client("Lucas", "lucas@cliente.com", "123456789", "123.456.789-00", "12.345.678/0001-00", "Nome da Empresa", plan);

        Message message = new Message("987654321", "Mensagem com o tipo Pos Pago", true, client);

        when(clientRepository.save(any(Client.class))).thenReturn(client);
        when(messageRepository.save(any(Message.class))).thenReturn(message);

        messageService.sendMessage(message);

        assertEquals(999.75, plan.getLimit());
    }

    @Test
    void testSendMessageWithInsufficientCreditsThrowsException() {
        Plan plan = new Plan("PRE_PAID", 0.0, 0.1);

        Client client = new Client("Antonio", "antonio@cliente.com", "123456789", "123.456.789-00", "12.345.678/0001-00", "Nome da Empresa", plan);

        Message message = new Message("987654321", "Mensagem Creditos insuficientes", true , client);

        Exception exception = assertThrows(Exception.class, () -> {
            messageService.sendMessage(message);
        });

        assertEquals("Insufficient credits.", exception.getMessage());
    }

    @Test
    void testSendMessageWithExceededLimitThrowsException() {
        Plan plan = new Plan("POST_PAID", 0.1, 0.0);

        Client client = new Client("Pedro", "pedro@cliente.com", "123456789", "123.456.789-00", "12.345.678/0001-00", "Nome da Empresa", plan);

        Message message = new Message("987654321", "Teste limite excedido", true, client);

        Exception exception = assertThrows(Exception.class, () -> {
            messageService.sendMessage(message);
        });

        assertEquals("Credit limit exceeded.", exception.getMessage());
    }

    @Test
    void testSendMessageThrowsExceptionWhenPlanIsNull() {
        Client client = new Client("João", "joão@cliente.com", "123456789", "123.456.789-00", "12.345.678/0001-00", "Nome da Empresa", null);

        Message message = new Message("987654321", "Testa Plano Nulo", true, client);

        Exception exception = assertThrows(Exception.class, () -> {
            messageService.sendMessage(message);
        });

        assertEquals("O cliente não tem um plano associado.", exception.getMessage());
    }

}
