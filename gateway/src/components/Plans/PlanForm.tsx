import React, { useState } from 'react';
import api from '../../services/api';
import { Plan } from '../../types';
import { Button, TextField, Container, Typography } from '@mui/material';

const PlanForm: React.FC = () => {
  const [plan, setPlan] = useState<Plan>({
    type: '',
    limit: 0,
    credits: 0,
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setPlan({ ...plan, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    api.post('/plan', plan).then(() => {
      alert('Plano criado com sucesso!');
      setPlan({ type: '', limit: 0, credits: 0 });
    });
  };

  return (
    <Container>
      <Typography variant="h4" gutterBottom>Criar Plano</Typography>
      <form onSubmit={handleSubmit}>
        <TextField label="Tipo" name="type" value={plan.type} onChange={handleChange} fullWidth required />
        <TextField label="Limite" name="limit" type="number" value={plan.limit} onChange={handleChange} fullWidth required />
        <TextField label="Créditos" name="credits" type="number" value={plan.credits} onChange={handleChange} fullWidth required />
        <Button type="submit" variant="contained" color="primary">Criar</Button>
      </form>
    </Container>
  );
};

export default PlanForm;
