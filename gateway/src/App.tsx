import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import ClientList from './components/Clients/ClientList';
import ClientForm from './components/Clients/ClientForm';
import PlanList from './components/Plans/PlanList';
import PlanForm from './components/Plans/PlanForm';
import MessageList from './components/Messages/MessageList';
import MessageForm from './components/Messages/MessageForm';

const App: React.FC = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<ClientList />} />
        <Route path="/clients/new" element={<ClientForm />} />
        <Route path="/clients/edit/:id" element={<ClientForm />} /> {/* Rota para editar */}
        <Route path="/plans" element={<PlanList />} />
        <Route path="/plans/new" element={<PlanForm />} />
        <Route path="/plans/edit/:id" element={<PlanForm />} /> {/* Rota para editar */}
        <Route path="/messages" element={<MessageList />} />
        <Route path="/messages/new" element={<MessageForm />} />
        <Route path="/messages/edit/:id" element={<MessageForm />} /> {/* Rota para editar */}
      </Routes>
    </Router>
  );
};

export default App;
