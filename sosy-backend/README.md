# Sosy Backend

## Overview
Sosy is a Spring Boot application designed for society fund tracking and management. This application allows users to sign up and manage their profiles, including personal information and family details.

## Project Structure
The project is structured as follows:

```
sosy-backend
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── sosy
│   │   │               ├── SosyApplication.java
│   │   │               ├── controller
│   │   │               │   └── SignupController.java
│   │   │               ├── model
│   │   │               │   └── User.java
│   │   │               ├── repository
│   │   │               │   └── UserRepository.java
│   │   │               └── service
│   │   │                   └── UserService.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── static
│   │       └── templates
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── sosy
│                       └── SosyApplicationTests.java
├── pom.xml
└── README.md
```

## Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd sosy-backend
   ```

2. **Build the Project**
   Ensure you have Maven installed. Run the following command to build the project:
   ```bash
   mvn clean install
   ```

3. **Run the Application**
   You can run the application using the following command:
   ```bash
   mvn spring-boot:run
   ```

4. **Access the Application**
   Once the application is running, you can access it at `http://localhost:8080`.

## Usage
- Users can sign up by providing their full name, flat number, Aadhaar number, occupation, number of family members, family names, phone number, email, and password.
- The application validates the input and stores user information in the database.

## Dependencies
This project uses the following dependencies:
- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- H2 Database (for in-memory database during development)
- Lombok (for reducing boilerplate code)

## Contributing
Contributions are welcome! Please feel free to submit a pull request or open an issue for any enhancements or bug fixes.

## License
This project is licensed under the MIT License. See the LICENSE file for details.