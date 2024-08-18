export interface Client {
    id?: string;
    name: string;
    email: string;
    phone: string;
    cpfResponsible?: string;
    cnpj?: string;
    companyName?: string;
    planId: string;
  }
  
  export interface Plan {
    id?: string;
    type: string;
    limit: number;
    credits: number;
  }
  
  export interface Message {
    id?: string;
    phoneNumber: string;
    isWhatsApp: boolean;
    text: string;
    clientId: string;
  }
  