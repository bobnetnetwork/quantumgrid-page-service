# Page Service

The **Page Service** is responsible for managing static and dynamic pages in the QuantumGrid platform.

## Features

- CRUD operations for pages
- Version control
- Page templates
- SEO management for pages

## Technology Stack

- **Java**: Programming language
- **Spring Boot**: Microservice framework
- **PostgreSQL**: Relational database for storing page data

## Getting Started

### Prerequisites

- **Java 17** or higher
- **Maven** for build automation
- **PostgreSQL** installed and running

### Setup

1. Clone the repository:
    ```bash
    git clone https://github.com/bobnetnetwork/quantumgrid-page-service.git
    cd quantumgrid-page-service
    ```

2. Configure the database connection in `src/main/resources/application.properties`:
    ```
    spring.datasource.url=jdbc:postgresql://localhost:5432/quantumgrid
    spring.datasource.username=yourusername
    spring.datasource.password=yourpassword
    ```

3. Build the application:
    ```bash
    mvn clean install
    ```

4. Run the application:
    ```bash
    mvn spring-boot:run
    ```

### API Endpoints

- `POST /api/pages` - Create a new page
- `GET /api/pages` - List all pages
- `GET /api/pages/{id}` - Get page details
- `PUT /api/pages/{id}` - Update a page
- `DELETE /api/pages/{id}` - Delete a page

## Contributing

Please read the [CONTRIBUTING.md](https://github.com/bobnetnetwork/quantumgrid/blob/main/CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests.

## License

This project is licensed under the GPL-3.0 license - see the [LICENSE.md](https://github.com/bobnetnetwork/quantumgrid/blob/main/LICENSE.md) file for details.
