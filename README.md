# Library API

A Spring Boot 3.x RESTful API for managing a library system. The project follows SOLID and GRASP principles, employing a layered architecture for clean maintainability.

## Tech Stack

* Java 17
* Spring Boot 3.x
* Spring Data JPA
* PostgreSQL
* Maven
* Docker & Docker Compose
* Lombok
* Jakarta Bean Validation
* JUnit 5 & Mockito

## Architecture

```
com.example.library
├── controller        # REST controllers (presentation layer)
│   └── advice        # Global exception handling
├── dto              # Data-transfer objects
├── entity           # JPA entities (domain layer)
├── mapper           # DTO ↔ Entity mappers (abstraction layer)
├── repository       # Spring Data JPA repositories (data access layer)
├── service          # Interfaces (business layer)
│   └── impl         # Implementations
└── exception        # Domain-specific exceptions
```

Key design-decisions:

* **Single Responsibility (SOLID – S)**: Every class focuses on one task (e.g., `BookService` only manipulates book data).
* **Open/Closed (SOLID – O)**: Services are defined by interfaces so implementations can evolve without changing clients.
* **Liskov Substitution (SOLID – L)**: Clients rely on abstractions (`BookService`) not concrete classes.
* **Interface Segregation (SOLID – I)**: Fine-grained service interfaces prevent bloating.
* **Dependency Inversion (SOLID – D)**: High-level modules depend on interfaces, injected by Spring.
* **GRASP Controller**: REST controllers delegate work to services, coordinating the request life-cycle.
* **GRASP Creator**: Repositories create/return entities, services create DTOs.
* **GRASP Low Coupling & High Cohesion**: Each layer hides implementation details and contains related responsibilities only.

## Getting Started

### Prerequisites

* Java 17+
* Maven 3.9+
* Docker & Docker Compose

### Build & Run with Docker Compose

```
# Build images & start app + PostgreSQL
$ docker compose up --build
```

The API will be available at `http://localhost:8080` and PostgreSQL at `localhost:5432` (user/pass `postgres` / `postgres`).

### Local Run (without Docker)

```
# build
$ mvn clean package -DskipTests
# run
$ java -jar target/library-api-0.0.1-SNAPSHOT.jar
```

Ensure a local PostgreSQL instance is running and matches `src/main/resources/application.yml` credentials.

## API Endpoints (v1)

| Method | Endpoint                   | Description              |
|--------|---------------------------|--------------------------|
| GET    | /api/v1/books             | List books               |
| GET    | /api/v1/books/{id}        | Book by id               |
| POST   | /api/v1/books             | Create book              |
| PUT    | /api/v1/books/{id}        | Update book              |
| DELETE | /api/v1/books/{id}        | Delete book              |
| GET    | /api/v1/authors           | List authors             |
| …      | …                         | _Other endpoints similar_|
| POST   | /api/v1/loans             | Borrow a book            |
| POST   | /api/v1/loans/{id}/return | Return a borrowed book   |

### Example – Borrow a Book

```
POST /api/v1/loans
Content-Type: application/json
{
  "bookId": 1,
  "borrowerId": 2
}
```

Successful response:

```
201 Created
{
  "loanId": 10,
  "bookId": 1,
  "borrowerId": 2,
  "borrowDate": "2024-01-01",
  "returnDate": null
}
```

## Testing

Run unit tests:

```
$ mvn test
```

## Contribution & License

MIT 