import React, { useEffect, useState } from 'react';
import api from '../../services/api';
import { Plan } from '../../types';
import { Button, Container, Typography, List, ListItem, ListItemText } from '@mui/material';

const PlanList: React.FC = () => {
  const [plans, setPlans] = useState<Plan[]>([]);

  useEffect(() => {
    api.get('/plan').then((response) => {
      setPlans(response.data);
    });
  }, []);

  const handleDelete = (id: string) => {
    api.delete(`/plan/${id}`).then(() => {
      setPlans(plans.filter(plan => plan.id !== id));
    });
  };

  return (
    <Container>
      <Typography variant="h4" gutterBottom>Planos</Typography>
      <List>
        {plans.map((plan) => (
          <ListItem key={plan.id}>
            <ListItemText primary={plan.type} secondary={`Limite: ${plan.limit}, Créditos: ${plan.credits}`} />
            <Button variant="outlined">Editar</Button>
            <Button variant="outlined" color="secondary" onClick={() => handleDelete(plan.id!)}>Excluir</Button>
          </ListItem>
        ))}
      </List>
    </Container>
  );
};

export default PlanList;
