# Commercia-360-API

A sales management system based on Hexagonal Architecture (Ports & Adapters), implemented with Java 17, Spring Boot, and JPA. It manages the relationship between time, products, stores, and sales.

---

## Key Features

- Clean and decoupled architecture (Hexagonal / Clean Architecture)
- Clear separation between domain models, DTOs, and entities
- Validation handled at the application layer
- Explicit conversion between DTOs and domain models (no ModelMapper)

---

## Architecture

This project follows Hexagonal Architecture, also known as Ports & Adapters, which enforces a strong separation of concerns:
- Domain: Contains the core business logic (e.g., SalesModel, rules, validations)
- Application: Defines use cases (SalesUseCase) and services (SalesService) that orchestrate business processes
- Infrastructure: Implements real adapters such as REST controllers, JPA entities, and repositories
- DTOs & Mappers: Prevent direct exposure of entities, using ID references and manual conversions
This architecture enhances testability, maintainability, and modularity. It allows replacing technologies (e.g., database or web framework) without affecting the business core.

---

## Technologies

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- H2
- Lombok
- Jakarta Validation
- ModelMapper
