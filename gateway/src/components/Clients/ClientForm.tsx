import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import api from '../../services/api';
import { Client } from '../../types';
import { Button, TextField, Container, Typography } from '@mui/material';

const ClientForm: React.FC = () => {
  const { id } = useParams<{ id: string }>();  // Pega o ID da URL
  const navigate = useNavigate();  // Substitui useHistory
  const [client, setClient] = useState<Client>({
    name: '',
    email: '',
    phone: '',
    planId: '',
  });

  useEffect(() => {
    if (id) {
      api.get(`/client/${id}`).then((response) => {
        setClient(response.data);
      });
    }
  }, [id]);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setClient({ ...client, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (id) {
      // Atualizar cliente existente
      api.put(`/client/${id}`, client).then(() => {
        alert('Cliente atualizado com sucesso!');
        navigate('/');  // Substitui history.push('/')
      });
    } else {
      // Criar novo cliente
      api.post('/client', client).then(() => {
        alert('Cliente criado com sucesso!');
        navigate('/');  // Substitui history.push('/')
      });
    }
  };

  return (
    <Container>
      <Typography variant="h4" gutterBottom>
        {id ? 'Editar Cliente' : 'Criar Cliente'}
      </Typography>
      <form onSubmit={handleSubmit}>
        <TextField
          label="Nome"
          name="name"
          value={client.name}
          onChange={handleChange}
          fullWidth
          required
        />
        <TextField
          label="Email"
          name="email"
          value={client.email}
          onChange={handleChange}
          fullWidth
          required
        />
        <TextField
          label="Telefone"
          name="phone"
          value={client.phone}
          onChange={handleChange}
          fullWidth
          required
        />
        <TextField
          label="ID do Plano"
          name="planId"
          value={client.planId}
          onChange={handleChange}
          fullWidth
          required
        />
        <Button type="submit" variant="contained" color="primary">
          {id ? 'Atualizar' : 'Criar'}
        </Button>
      </form>
    </Container>
  );
};

export default ClientForm;
