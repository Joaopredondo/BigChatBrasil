import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import api from '../../services/api';
import { Client } from '../../types';
import { Button, Container, Typography, List, ListItem, ListItemText } from '@mui/material';

const ClientList: React.FC = () => {
  const [clients, setClients] = useState<Client[]>([]);

  useEffect(() => {
    api.get('/client').then((response) => {
      setClients(response.data);
    });
  }, []);

  const handleDelete = (id: string) => {
    api.delete(`/client/${id}`).then(() => {
      setClients(clients.filter(client => client.id !== id));
    });
  };

  return (
    <Container>
      <Typography variant="h4" gutterBottom>Clientes</Typography>
      <List>
        {clients.map((client) => (
          <ListItem key={client.id}>
            <ListItemText primary={client.name} secondary={client.email} />
            <Button variant="outlined" component={Link} to={`/clients/edit/${client.id}`}>Editar</Button>
            <Button variant="outlined" color="secondary" onClick={() => handleDelete(client.id!)}>Excluir</Button>
          </ListItem>
        ))}
      </List>
    </Container>
  );
};

export default ClientList;
