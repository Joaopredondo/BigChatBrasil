Big Chat Brasil

Este é o repositório do "Big Chat Brasil", um sistema de envio de mensagens SMS e WhatsApp desenvolvido em Java com Spring Boot no backend e React com TypeScript no frontend.

Requisitos
Antes de começar, certifique-se de ter os seguintes softwares instalados:

Java 17: Versão recomendada para execução do backend.
Maven 3.6+: Para gerenciar dependências e build do projeto.
Node.js 14+: Para executar o frontend (React).
Docker: Para o banco de dados PostgreSQL.

Configuração do Ambiente
1. Clonar o Repositório
git clone https://github.com/usuario/big-chat-brasil.git
cd big-chat-brasil

2. Configurar o Banco de Dados
Certifique-se de que o Docker esteja instalado e rodando em sua máquina. Em seguida, execute o comando abaixo para inicializar o banco de dados PostgreSQL:

docker-compose up -d
Isso iniciará o banco de dados PostgreSQL na porta 5432.

3. Configurar o Backend
Navegue até o diretório do backend e compile o projeto:

cd core
mvn clean install

Em seguida, inicie o servidor Spring Boot:
mvn spring-boot:run
O backend estará disponível em http://localhost:8080.

4. Configurar o Frontend
Navegue até o diretório do frontend e instale as dependências:

cd gateway
npm install

Inicie o servidor de desenvolvimento do React:
npm start
O frontend estará disponível em http://localhost:3000.

--------------------------------------------------------

Durante o desenvolvimento do sistema "Big Chat Brasil", foram assumidas as seguintes premissas:

Autenticação e Autorização:

Foi assumido que o sistema não requer autenticação ou autorização para acessar os endpoints. Todos os usuários podem realizar operações CRUD sem necessidade de um token de autenticação.
Estrutura do Plano:

Assumiu-se que cada cliente deve estar associado a um plano previamente cadastrado no sistema, seja ele pré-pago ou pós-pago.
Planos pré-pagos devem ter créditos suficientes para envio de mensagens. O custo de cada mensagem foi definido como R$0,25.
Estrutura de Dados:

As entidades Cliente, Plano e Mensagem foram modeladas com base nos requisitos fornecidos. Os dados do cliente incluem informações básicas como nome, email, telefone, CPF do responsável, CNPJ, e nome da empresa.
Cada mensagem deve ser associada a um cliente existente e pode ser enviada via SMS ou WhatsApp, conforme indicado por um flag.
Validação de Dados:

Supõe-se que todas as validações, como formatos de email, CPF, e CNPJ, sejam realizadas pelo frontend antes de enviar os dados para o backend. No backend, validações adicionais foram implementadas para garantir a integridade dos dados.
O sistema não lida com cenários complexos de validação de crédito ou cobrança em tempo real.
Ambiente de Execução:

Foi assumido que o sistema será executado em um ambiente local para desenvolvimento e testes, utilizando Docker para o banco de dados PostgreSQL.
O backend foi desenvolvido utilizando Java 17 com Spring Boot, e o frontend foi desenvolvido utilizando React com TypeScript.