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

---

## Documentación de Endpoints de la API

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## Endpoints de la API

| Método | Endpoint               | Descripción                | Controlador        |
|--------|------------------------|----------------------------|--------------------|
| GET    | /api/times/{id}        | Obtener tiempo por ID      | time-controller    |
| PUT    | /api/times/{id}        | Actualizar tiempo por ID   | time-controller    |
| DELETE | /api/times/{id}        | Eliminar tiempo por ID     | time-controller    |
| GET    | /api/times             | Listar todos los tiempos   | time-controller    |
| POST   | /api/times             | Crear nuevo tiempo         | time-controller    |
| GET    | /api/stores/{id}       | Obtener tienda por ID      | store-controller   |
| PUT    | /api/stores/{id}       | Actualizar tienda por ID   | store-controller   |
| DELETE | /api/stores/{id}       | Eliminar tienda por ID     | store-controller   |
| GET    | /api/stores            | Listar todas las tiendas   | store-controller   |
| POST   | /api/stores            | Crear nueva tienda         | store-controller   |
| GET    | /api/sales/{id}        | Obtener venta por ID       | sales-controller   |
| PUT    | /api/sales/{id}        | Actualizar venta por ID    | sales-controller   |
| DELETE | /api/sales/{id}        | Eliminar venta por ID      | sales-controller   |
| GET    | /api/sales             | Listar todas las ventas    | sales-controller   |
| POST   | /api/sales             | Crear nueva venta          | sales-controller   |
| GET    | /api/products/{id}     | Obtener producto por ID    | product-controller |
| PUT    | /api/products/{id}     | Actualizar producto por ID | product-controller |
| DELETE | /api/products/{id}     | Eliminar producto por ID   | product-controller |
| GET    | /api/products          | Listar todos los productos | product-controller |
| POST   | /api/products          | Crear nuevo producto       | product-controller |

