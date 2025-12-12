# EcoPass

API REST para gestão de reciclagem e recompensas sustentáveis. O EcoPass é uma plataforma que permite gerenciar usuários, carteiras digitais, materiais recicláveis e registros de reciclagem, incentivando práticas ecológicas através de um sistema de recompensas.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.3.1**
- **Spring Data JPA** - Persistência de dados
- **Spring Validation** - Validação de dados
- **H2 Database** - Banco de dados em memória
- **Lombok** - Redução de código boilerplate
- **MapStruct 1.5.5** - Mapeamento entre DTOs e entidades
- **Maven** - Gerenciamento de dependências

## Estrutura do Projeto

```
ecopass/
├── src/main/java/br/com/darioklein/ecopass/
│   ├── config/                 # Configurações da aplicação
│   ├── controller/             # Controllers REST
│   ├── domain/
│   │   ├── dto/                # Data Transfer Objects
│   │   ├── mapper/             # Mappers (MapStruct)
│   │   └── model/
│   │       ├── entity/         # Entidades JPA
│   │       └── enumTypes/      # Enumerações
│   ├── exception/              # Tratamento de exceções
│   ├── repository/             # Repositórios JPA
│   ├── service/
│   │   ├── exception/          # Exceções de serviço
│   │   └── impl/               # Implementações dos serviços
│   └── utils/                  # Utilitários e validadores
└── src/main/resources/
    ├── application.properties
    └── application-local.properties
```

## Entidades Principais

| Entidade | Descrição |
|----------|-----------|
| **User** | Usuário do sistema com dados pessoais |
| **Wallet** | Carteira digital vinculada ao usuário |
| **Material** | Material reciclável com preço por kg |
| **Recycling** | Registro de reciclagem do usuário |
| **RecyclingMaterial** | Relacionamento entre reciclagem e materiais |

## Rotas da API

### Usuários (`/users`)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/users/{id}` | Buscar usuário por ID |
| `GET` | `/users` | Listar todos os usuários |
| `GET` | `/users/by-pending-balance` | Listar usuários ordenados por saldo pendente (decrescente) |
| `GET` | `/users/by-birth-date` | Listar usuários ordenados por data de nascimento |
| `GET` | `/users/pending-balance-between?min=X&max=Y` | Buscar usuários com saldo pendente entre valores |
| `POST` | `/users` | Criar novo usuário |
| `PUT` | `/users/{id}` | Atualizar usuário completo |
| `PATCH` | `/users/{id}` | Atualizar usuário parcialmente |
| `PATCH` | `/users/password/{id}` | Atualizar senha do usuário |
| `DELETE` | `/users/{id}` | Deletar usuário |

### Favoritos do Usuário (`/users`)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/users/{userId}/favorites` | Listar materiais favoritos do usuário |
| `POST` | `/users/favorite` | Adicionar material aos favoritos |
| `DELETE` | `/users/favorite` | Remover material dos favoritos |

### Carteiras (`/wallets`)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/wallets/{id}` | Buscar carteira por ID |
| `POST` | `/wallets` | Criar nova carteira |
| `PUT` | `/wallets/{id}` | Atualizar carteira completa |
| `PATCH` | `/wallets/{id}` | Atualizar carteira parcialmente |
| `DELETE` | `/wallets/{id}` | Deletar carteira |

### Materiais (`/materials`)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/materials/{id}` | Buscar material por ID |
| `GET` | `/materials` | Listar todos os materiais |
| `GET` | `/materials/search?name=X` | Buscar materiais por nome |
| `GET` | `/materials/less-than-equal?pricePerKg=X` | Buscar materiais com preço menor ou igual |
| `GET` | `/materials/greater-than-equal?pricePerKg=X` | Buscar materiais com preço maior ou igual |
| `POST` | `/materials` | Criar novo material |
| `PUT` | `/materials/{id}` | Atualizar material completo |
| `PATCH` | `/materials/{id}` | Atualizar material parcialmente |
| `DELETE` | `/materials/{id}` | Deletar material |

### Reciclagens (`/recycling`)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/recycling/{id}` | Buscar reciclagem por ID |
| `GET` | `/recycling` | Listar todas as reciclagens |
| `GET` | `/recycling/users/{userId}/latest` | Buscar última reciclagem do usuário |
| `GET` | `/recycling/users/{userId}/{status}` | Buscar reciclagens do usuário por status |
| `GET` | `/recycling/date-after?date=YYYY-MM-DD` | Buscar reciclagens após uma data |
| `GET` | `/recycling/users/{userId}/list-material-name?listMaterialName=X,Y` | Buscar reciclagens por nomes de materiais |
| `POST` | `/recycling` | Criar nova reciclagem |
| `PUT` | `/recycling/{id}` | Atualizar reciclagem completa |
| `PATCH` | `/recycling/{id}` | Atualizar reciclagem parcialmente |
| `DELETE` | `/recycling/{id}` | Deletar reciclagem |

### Materiais de Reciclagem (`/recycling-materials`)

| Método | Endpoint                                                       | Descrição |
|--------|----------------------------------------------------------------|-----------|
| `GET` | `/recycling-materials/search-by-id?recyclingId=X&materialId=Y` | Buscar por ID composto |
| `GET` | `/recycling-materials`                                         | Listar todos os materiais de reciclagem |
| `GET` | `/recycling-materials/search-by-material/{id}`                 | Buscar materiais por ID de reciclagem |
| `GET` | `/recycling-materials/less-than?quantityKg=X`                  | Buscar com quantidade menor que |
| `GET` | `/recycling-materials/greater-than?quantityKg=X`               | Buscar com quantidade maior que |
| `POST` | `/recycling-materials`                                         | Criar novo material de reciclagem |
| `PUT` | `/recycling-materials?recyclingId=X&materialId=Y`              | Atualizar material de reciclagem |
| `DELETE` | `/recycling-materials?recyclingId=X&materialId=Y`              | Deletar material de reciclagem |

## Status de Reciclagem

A entidade `Recycling` possui um campo `status` que pode conter os seguintes valores:

- `PENDING` - Pendente
- `APPROVED` - Aprovado
- `REJECTED` - Rejeitado

## Tratamento de Erros

A aplicação possui tratamento centralizado de exceções através do `ResourceExceptionHandler`, retornando respostas padronizadas com:

- **ObjectNotFoundException** - Recurso não encontrado (404)
- **DataIntegrityViolationException** - Violação de integridade de dados (400)
- **InvalidDateFormatException** - Formato de data inválido (400)

## Como Executar

1. Clone o repositório
2. Navegue até a pasta `ecopass`
3. Execute o comando:

```bash
./mvnw spring-boot:run
```

A aplicação será iniciada na porta padrão `8080`.

## Console H2

O banco de dados H2 está configurado para uso em memória. Para acessar o console H2:

- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:ecopass`

## Autor

Desenvolvido por **Dario Klein**
