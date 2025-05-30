# 📋 Desafio QA - API de Cadastro de Clientes

Este projeto é uma API RESTful desenvolvida em **Java com Spring Boot**, como parte de um desafio técnico de QA. O objetivo é fornecer um serviço de cadastro de clientes, com operações de **CRUD (Create, Read, Update, Delete)**, além de possuir validações nos campos obrigatórios e retorno adequado de erros.

---

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot 3.2.5
- Spring Data JPA
- Banco de dados H2 (em memória)
- Maven
- Cucumber (para testes BDD)
- JUnit 5
- Swagger (pode ser adicionado para documentação da API)
- Lombok (facilita a criação de getters/setters)

---

## 🔧 Funcionalidades

- ✅ Cadastro de clientes
- ✅ Atualização de dados dos clientes
- ✅ Busca de cliente por ID
- ✅ Listagem de todos os clientes
- ✅ Exclusão de clientes
- ✅ Validações de campos obrigatórios e formato (CPF, telefone, data)
- ✅ Retorno de mensagens claras em caso de erro ou dados inválidos

---

## 🎯 Regras de Validação

- **CPF:** obrigatório e deve conter 11 dígitos numéricos.
- **Nome:** obrigatório.
- **Data de Nascimento:** obrigatória (string no formato yyyy-MM-dd).
- **Telefone:** obrigatório, com 10 ou 11 dígitos numéricos.
- **Endereço:** obrigatório.

---

## 🏗️ Como executar o projeto localmente

### 🔥 Pré-requisitos

- Java 17 instalado
- Maven instalado
- Git instalado

### 🚀 Passos para rodar:

1. Clone o repositório:

git clone https://github.com/BernardoVR/DesafioQA.git
cd DesafioQA

2. Execute o projeto com Maven:

mvn spring-boot:run

3. A API estará disponível no endereço:
   
http://localhost:8080/api/clientes

🗺️ Endpoints da API
✔️ Criar cliente (POST)

POST /api/clientes

Corpo da requisição:
{
  "cpf": "12345678901",
  "nome": "João da Silva",
  "dataNascimento": "1990-05-10",
  "telefone": "11999999999",
  "endereco": "Rua Exemplo, 123, São Paulo - SP"
}

✔️ Buscar todos os clientes (GET)
GET /api/clientes

✔️ Buscar cliente por ID (GET)
GET /api/clientes/{id}

✔️ Atualizar cliente (PUT)
PUT /api/clientes/{id}

Corpo da requisição: igual ao POST

✔️ Deletar cliente (DELETE)
DELETE /api/clientes/{id}

⚠️ Tratamento de Erros
✅ Campos obrigatórios não preenchidos: 400 Bad Request

✅ CPF duplicado: 400 Bad Request ou 409 Conflict (ajustável conforme necessidade)

✅ Cliente não encontrado: 404 Not Found

🧪 Testes
O projeto possui testes automatizados utilizando Cucumber + JUnit, com cenários escritos em BDD para validar o funcionamento da API.

Para rodar os testes:
mvn test

💾 Banco de Dados
O projeto utiliza banco H2 em memória.
Acesse o console do banco em:

http://localhost:8080/h2-console
jdbc:h2:mem:testdb


