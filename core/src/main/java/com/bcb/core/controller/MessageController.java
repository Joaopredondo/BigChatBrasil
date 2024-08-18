package com.bcb.core.controller;

import com.bcb.core.dto.MessageDTO;
import com.bcb.core.entity.Client;
import com.bcb.core.entity.Message;
import com.bcb.core.service.ClientService;
import com.bcb.core.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;
    private final ClientService clientService;

    public MessageController(MessageService messageService, ClientService clientService) {
        this.messageService = messageService;
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<MessageDTO> sendMessage(@RequestBody MessageDTO messageDTO) throws Exception {
        Client client = clientService.getClientById(messageDTO.getClientId()).orElseThrow(() -> new Exception("Cliente não encontrado"));
        Message message = new Message(messageDTO.getPhoneNumber(), messageDTO.getText(), messageDTO.isWhatsApp(), client);
        Message sentMessage = messageService.sendMessage(message);
        MessageDTO sentMessageDTO = new MessageDTO(sentMessage.getId(), sentMessage.getPhoneNumber(),
                                                   sentMessage.getIsWhatsapp(), sentMessage.getText(), sentMessage.getId());
        return ResponseEntity.ok(sentMessageDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageDTO> getMessage(@PathVariable UUID id) {
        Optional<Message> message = messageService.getMessageById(id);

        return message.map(value -> {
            MessageDTO messageDTO = new MessageDTO(value.getId(), value.getPhoneNumber(),value.getIsWhatsapp(),
                                                   value.getText(), value.getClient().getId());
            return ResponseEntity.ok(messageDTO);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<MessageDTO>> getAllMessage(){
        List<Message> messages = messageService.listAllMessages();

        List<MessageDTO> messageDTOS = messages.stream()
                .map(message -> new MessageDTO(message.getId(), message.getPhoneNumber(), message.getIsWhatsapp(),
                                               message.getText(), message.getClient().getId()))
                .toList();
        return ResponseEntity.ok(messageDTOS);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteMessage(@PathVariable UUID id) {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }
}
