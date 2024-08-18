import React, { useState } from 'react';
import api from '../../services/api';
import { Message } from '../../types';
import { Button, TextField, Container, Typography, FormControlLabel, Checkbox } from '@mui/material';

const MessageForm: React.FC = () => {
  const [message, setMessage] = useState<Message>({
    phoneNumber: '',
    isWhatsApp: false,
    text: '',
    clientId: '',
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setMessage({ ...message, [e.target.name]: e.target.value });
  };

  const handleCheckboxChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setMessage({ ...message, isWhatsApp: e.target.checked });
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    api.post('/messages', message).then(() => {
      alert('Mensagem enviada com sucesso!');
      setMessage({ phoneNumber: '', isWhatsApp: false, text: '', clientId: '' });
    });
  };

  return (
    <Container>
      <Typography variant="h4" gutterBottom>Enviar Mensagem</Typography>
      <form onSubmit={handleSubmit}>
        <TextField label="Número de Telefone" name="phoneNumber" value={message.phoneNumber} onChange={handleChange} fullWidth required />
        <FormControlLabel
          control={
            <Checkbox checked={message.isWhatsApp} onChange={handleCheckboxChange} name="isWhatsApp" />
          }
          label="Enviar via WhatsApp"
        />
        <TextField label="Texto" name="text" value={message.text} onChange={handleChange} fullWidth required />
        <TextField label="ID do Cliente" name="clientId" value={message.clientId} onChange={handleChange} fullWidth required />
        <Button type="submit" variant="contained" color="primary">Enviar</Button>
      </form>
    </Container>
  );
};

export default MessageForm;
