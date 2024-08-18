import React, { useEffect, useState } from 'react';
import api from '../../services/api';
import { Message } from '../../types';
import { Button, Container, Typography, List, ListItem, ListItemText } from '@mui/material';

const MessageList: React.FC = () => {
  const [messages, setMessages] = useState<Message[]>([]);

  useEffect(() => {
    api.get('/message').then((response) => {
      setMessages(response.data);
    });
  }, []);

  const handleDelete = (id: string) => {
    api.delete(`/message/${id}`).then(() => {
      setMessages(messages.filter(message => message.id !== id));
    });
  };

  return (
    <Container>
      <Typography variant="h4" gutterBottom>Mensagens</Typography>
      <List>
        {messages.map((message) => (
          <ListItem key={message.id}>
            <ListItemText primary={message.text} secondary={`Para: ${message.phoneNumber}, WhatsApp: ${message.isWhatsApp}`} />
            <Button variant="outlined">Editar</Button>
            <Button variant="outlined" color="secondary" onClick={() => handleDelete(message.id!)}>Excluir</Button>
          </ListItem>
        ))}
      </List>
    </Container>
  );
};

export default MessageList;
