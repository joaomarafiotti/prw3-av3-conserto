# PRW3 – API de Consertos de Veículos (Avaliação 3)

Projeto desenvolvido para a disciplina **Programação para Web III (PRW3)** – IFSP São Carlos.  
Objetivo: criar uma **API REST com Spring Boot**, utilizando **JPA, H2, Flyway e validações**, conforme pedido nas partes 1, 2 e 3 da avaliação.

---

## Como executar

1. Clonar ou baixar o projeto.

2. No terminal, rodar: ./mvnw spring-boot:run

3. A API ficará disponível em: http://localhost:8080

4. Banco de dados H2 (visualização):

- Acessar: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:prw3db`
- Usuário: `sa` | Senha: (em branco)

---

## Endpoints principais

| Método | Endpoint              | Descrição |
|--------|------------------------|-----------|
| POST   | `/consertos`          | Cadastrar um conserto |
| GET    | `/consertos`          | Listagem completa (paginada) |
| GET    | `/consertos/dados`    | Listagem parcial (id, datas, mecânico, veículo) – apenas ativos |
| GET    | `/consertos/{id}`     | Detalhar um conserto por id |
| PUT    | `/consertos`          | Atualizar data de saída ou dados do mecânico |
| DELETE | `/consertos/{id}`     | Exclusão lógica (ativo = false) |

---

## Tecnologias e requisitos da atividade

- **Java 17**
- **Spring Boot 3**
- **Spring Web + Spring Data JPA**
- **Banco de dados H2 (memória)**
- **Flyway (migração de banco de dados)**
- **Lombok**
- **Validação com Bean Validation (@NotBlank, @Pattern, etc.)**

---

## Estrutura principal do projeto

| Pacote / Arquivo        | Descrição |
|-------------------------|-----------|
| `domain/conserto/`     | Classes de modelo (Conserto, Veiculo, Mecanico – embutidos/embedded) |
| `dto/conserto/`        | DTOs de cadastro, listagem, atualização e detalhamento |
| `repository/`          | `ConsertoRepository` (interface JPA) |
| `controller/`          | Endpoints REST – `ConsertoController` |
| `resources/db/migration/` | Migrações Flyway: V1 (tabela), V2 (cor), V3 (ativo) |
| `application.properties` | Configuração do banco H2 |

---

## Entidade principal

Um **Conserto** possui:

- `id`
- `dataEntrada` (String – dd/MM/yyyy)
- `dataSaida` (String – opcional)
- `ativo` (para exclusão lógica)
- `Mecanico` (embedded – nome e anos de experiência)
- `Veiculo` (embedded – marca, modelo, ano e cor)

---

## O que fiz nas 3 partes:

- Classes `Conserto`, `Mecanico` e `Veiculo` (embutidas com @Embeddable)
- POST salvando no banco (H2)
- Flyway com V1, V2 (cor) e V3 (ativo)
- Validação de datas e campos obrigatórios
- GET completo com paginação
- GET parcial com apenas dados essenciais
- PUT alterando apenas campos permitidos
- DELETE lógico (ativo = false)
- Uso de ResponseEntity em todas as respostas HTTP

---

## Como testar rapidamente (Postman ou Insomnia)

1. Criar **Environment** com:
- `baseUrl = http://localhost:8080`

2. Testar na ordem:

POST {{baseUrl}}/consertos

{
"dataEntrada": "10/10/2025",
"dataSaida": "12/10/2025",
"mecanicoNome": "Carlos",
"mecanicoAnos": 5,
"veiculoMarca": "Fiat",
"veiculoModelo": "Argo",
"veiculoAno": "2022",
"veiculoCor": "Vermelho"
}

GET {{baseUrl}}/consertos

GET {{baseUrl}}/consertos/dados

PUT {{baseUrl}}/consertos

DELETE {{baseUrl}}/consertos/1

3. Verificar na H2 se os dados estão sendo gravados corretamente.

---

Desenvolvido para a disciplina **PRW3 – Programação para Web III**, IFSP São Carlos.
