# 🔐 Spring Security Template

A production-ready **Spring Boot 3.5.6** template with **JWT authentication** using **Spring Security 6+**. Built with **Clean Architecture**. **MySQL database integration**, and **comprehensive security configuration**. This template provides a solid foundation for building secure REST APIs with user authentication and authorization.

## ✨ Features

- **🔒 JWT Authentication**: Stateless authentication using JSON Web Tokens with configurable expiration
- **👤 User Management**: Complete user registration, login, and profile endpoints
- **🛡️ Spring Security**: Comprehensive security configuration with role-based access control
- **🗄️ MySQL Integration**: JPA/Hibernate setup with MySQL 8.0 database
- **📚 API Documentation**: Integrated Swagger/OpenAPI 3.0 documentation
- **🐳 Docker Support**: Complete Docker containerization with MySQL service
- **🧪 Integration Testing**: Comprehensive test suite with MockMvc and security testing
- **⚙️ Production Ready**: Configurable profiles, connection pooling, and error handling
- **🔧 Clean Architecture**: Well-structured codebase with separation of concerns

## 🛠️ Tech Stack

- **Framework**: Spring Boot 3.5.6
- **Security**: Spring Security 6+ with JWT (JJWT 0.11.5)
- **Database**: MySQL 8.0 with JPA/Hibernate
- **Build Tool**: Maven
- **Java Version**: 17
- **Documentation**: SpringDoc OpenAPI 3 (2.7.0)
- **Testing**: JUnit 5, MockMvc, Spring Security Test
- **Containerization**: Docker & Docker Compose
- **Additional**: Lombok, BCrypt Password Encoding, HikariCP Connection Pool

## 🚀 Quick Start

### Prerequisites
- Java 17+
- Maven 3.6+
- Docker & Docker Compose (optional)

### Option 1: Running with Docker (Recommended)
```bash
# Clone the repository
git clone <your-repo-url>
cd spring-security-template

chmod +x up-dev.sh
./up-dev.sh
```

### Option 2: Running Locally
```bash
# Start MySQL database only
docker-compose up -d db

# Run the Spring Boot application
mvn spring-boot:run
```

### Option 3: Manual Setup
1. Set up a MySQL database
2. Update `application.yml` with your database credentials
3. Run the application:
```bash
mvn clean install
mvn spring-boot:run
```

## 📋 API Endpoints

### Authentication Endpoints
| Method | Endpoint | Description | Authentication |
|--------|----------|-------------|----------------|
| `POST` | `/api/v1/auth/register` | Register a new user | No |
| `POST` | `/api/v1/auth/login` | Login user | No |

### User Management Endpoints
| Method | Endpoint | Description | Authentication |
|--------|----------|-------------|----------------|
| `GET` | `/api/v1/user` | Get current user profile | Yes (JWT) |

### Documentation Endpoints
| Method | Endpoint | Description | Authentication |
|--------|----------|-------------|----------------|
| `GET` | `/api/v1/swagger-ui` | Swagger UI | No |

## 📝 API Usage Examples

### User Registration
```bash
curl -X POST http://localhost:8080/api/v1/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "email": "user@example.com",
    "password": "securePassword123"
  }'
```

### User Login
```bash
curl -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "user@example.com",
    "password": "securePassword123"
  }'
```

### Get User Profile (with JWT token)
```bash
curl -X GET http://localhost:8080/api/v1/user \
  -H "Authorization: Bearer <your-jwt-token>"
```

## 🔧 Configuration

### Environment Variables
The application supports environment-based configuration:

| Variable | Description | Default Value |
|----------|-------------|---------------|
| `DATASOURCE_URL` | Database connection URL | `jdbc:mysql://localhost:3306/template` |
| `DATASOURCE_USERNAME` | Database username | `template` |
| `DATASOURCE_PASSWORD` | Database password | `123456` |
| `JWT_SECRET` | JWT signing secret key | `replace_with_a_very_long_random_secret_key_1234567890` |
| `JWT_EXPIRATION` | Token expiration time (ms) | `3600000` (1 hour) |
| `APP_PORT` | Application port | `8080` |
| `DDL_AUTO` | Hibernate DDL mode | `update` |


## 🧪 Testing

### Option 1: Running Tests with Docker (Recommended)
```bash
./up-test.sh
```

### Option 2: Running Tests Locally
```bash
# Run all tests
mvn test

# Run integration tests only
mvn test -Dtest="**/*IT"

# Run with coverage
mvn test jacoco:report
```

## 📚 Learning Resources

### Spring Boot & Security
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Security Documentation](https://spring.io/projects/spring-security)
- [JWT Introduction](https://jwt.io/introduction/)

### Database & JPA
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Hibernate Documentation](https://hibernate.org/orm/documentation/)

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Contribution Guidelines
- Follow the existing code style
- Add tests for new features
- Update documentation
- Ensure all tests pass
- Keep commits atomic and well-described


## 📄 License

This project is open source and available under the [MIT License](LICENSE).

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- Spring Security team for robust security features
- JWT.io for JWT implementation guidance
- MySQL team for the reliable database system

---

**Perfect for**: Microservices, REST APIs, Authentication services, Spring Boot learning, Production applications

**Keywords**: `spring-boot` `spring-security` `jwt` `mysql` `rest-api` `authentication` `docker` `java` `maven` `template`

---

Made with ❤️ by Oswaldo Osuna, for developers. Star ⭐ this repository if you find it helpful! 