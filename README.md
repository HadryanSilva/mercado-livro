# Mercado Livro

## Descrição
Mercado Livro é um sistema de gerenciamento de livros desenvolvido com Kotlin e Spring Boot. 
Este projeto foi criado para fins de aprendizado da linguagem Kotlin no desenvolvimento backend usando o framework Spring Boot.

## Tecnologias Utilizadas
- **Kotlin 1.9.25**: Linguagem de programação principal
- **Spring Boot 3.4.4**: Framework para desenvolvimento de aplicações
- **Spring Data JPA**: Para persistência de dados
- **PostgreSQL 13**: Banco de dados relacional
- **Flyway 11.7.0**: Ferramenta de migração de banco de dados
- **Docker**: Para containerização do banco de dados
- **Gradle 8.13**: Ferramenta de automação de build

## Estrutura do Projeto
O projeto segue uma arquitetura em camadas típica de aplicações Spring:
- **Controller**: Gerencia as requisições HTTP
- **Service**: Contém a lógica de negócio
- **Repository**: Responsável pela persistência de dados
- **Model**: Representa as entidades de domínio
- **DTO**: Objetos de transferência de dados (Request/Response)

## Funcionalidades
- Cadastro de clientes (CRUD completo)
- Listagem de clientes com filtro por nome
- Busca de cliente por ID

## Como Executar

### Pré-requisitos
- JDK 21
- Docker e Docker Compose

### Passos para Execução
1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/mercado-livro.git
cd mercado-livro
```

2. Inicie o banco de dados PostgreSQL com Docker Compose:
```bash
docker-compose up -d
```

3. Execute a aplicação:
```bash
./gradlew bootRun
```

4. A aplicação estará disponível em: http://localhost:8080

## Endpoints da API
- `GET /api/v1/customers/hello`: Endpoint de teste
- `GET /api/v1/customers/list?name={name}`: Lista todos os clientes (filtragem opcional por nome)
- `GET /api/v1/customers/{id}`: Busca um cliente pelo ID
- `POST /api/v1/customers`: Cria um novo cliente
- `PUT /api/v1/customers`: Atualiza um cliente existente
- `DELETE /api/v1/customers/{id}`: Remove um cliente

## Desenvolvimento
Este projeto utiliza Gradle como ferramenta de build. Comandos úteis:

```bash
# Executar testes
./gradlew test

# Compilar o projeto
./gradlew build

# Limpar diretório de build
./gradlew clean
```

## Migrações de Banco de Dados
As migrações são gerenciadas pelo Flyway e estão localizadas em `src/main/resources/db/migration/`.