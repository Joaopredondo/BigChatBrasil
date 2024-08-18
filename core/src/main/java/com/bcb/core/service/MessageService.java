package com.bcb.core.service;

import com.bcb.core.entity.Client;
import com.bcb.core.entity.Message;
import com.bcb.core.repository.ClientRepository;
import com.bcb.core.repository.MessageRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final ClientRepository clientRepository;

    public MessageService(MessageRepository messageRepository, ClientRepository clientRepository) {
        this.messageRepository = messageRepository;
        this.clientRepository  = clientRepository;
    }

    @Transactional
    public Message sendMessage(Message message) throws Exception {
        Client client = message.getClient();

        if (client.getPlan() == null) {
            throw new Exception("Cliente informado não possui plano associado.");
        }

        if("PRE_PAID".equals(client.getPlan().getType())) {
            if (client.getPlan().getCredits() < 0.25) {
                throw new Exception("Créditos Insuficientes para enviar a mensagem, realize uma nova recarga.");
            } else {
                client.getPlan().setCredits(client.getPlan().getCredits() - 0.25);
                clientRepository.save(client);
            }
        } else if ("POST_PAID".equals(client.getPlan().getType())) {
            if (client.getPlan().getLimit() < 0.25) {
                throw new Exception("Não foi possivel enviar a mensagem, limite de crédito excedido");
            } else {
                client.getPlan().setLimit(client.getPlan().getLimit() - 0.25);
                clientRepository.save(client);
            }
        }
        messageRepository.save(message);
        return message;
    }

    public Optional<Message> getMessageById(UUID id) {
        return messageRepository.findById(id);
    }

    public List<Message> listAllMessages() {
        return messageRepository.findAll();
    }

    public void deleteMessage(UUID id) {
        if(messageRepository.existsById(id)) {
            messageRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Mensagem com o id" + id + "não encontrada.");
        }
    }
}
