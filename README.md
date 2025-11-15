# SocietySync: Digital Society Management System
SocietySync is a comprehensive, web-based platform designed to **streamline and digitize** the core functions of residential society management. The primary goal is to promote **transparency, accountability, and collaboration** among residents and committee members.
This platform replaces fragmented, manual processes with a unified digital ecosystem, focusing on essential society needs using easily maintainable, open-source technologies.
---
## Key Features and Modules
The application provides a centralized dashboard built around six core modules:

| Module | Core Functionality | Purpose |
| :--- | :--- | :--- |
| **Fund Flow** | Tracks **current fund balance** (maintenance and donations) and displays a clear list of **defaulters** with pending dues. | Financial Transparency |
| **Festival Budget** | Allows committee members to track **festival-wise budget** allocation, money used, donations received, and remaining funds. | Budget Accountability |
| **Sectoral Distribution** | Provides a **visual breakdown** (pie chart) of fund allocation across major sectors like security, sanitation, and infrastructure. | Financial Reporting |
| **Notice Board** | Centralizes and displays society announcements, event updates, and maintenance schedules, with the ability to **highlight urgent notices**. | Communication |
| **Quick Resolve** | Simplifies **issue reporting** and tracking. Residents can raise queries, and committee members can provide resolutions, updating the query status efficiently. | Issue Management |
| **Civic Circle** | A directory of **verified professionals** (doctors, lawyers, advisors) residing within the community, including contact details and filtering options. | Community Networking |

---

## Technology Stack
This project is implemented using the following modern and robust technologies:
| Layer | Technology | Version / Framework |
| :--- | :--- | :--- |
| **Backend** | **Java** | Spring Boot |
| **Database** | **MySQL** | JPA / Hibernate |
| **Frontend** | **Web Standards** | HTML5, CSS3, JavaScript |
| **Build Tool** | **Maven** | Dependency Management |

---

## Installation and Setup

Follow these steps to set up and run the SocietySync backend locally.

### Prerequisites

* **Java Development Kit (JDK) 17+**
* **Apache Maven**
* **MySQL Server**

### 1. Database Setup

1.  Log in to your MySQL environment.
2.  Create the database:
    ```sql
    CREATE DATABASE sosy;
    ```
3.  Ensure your `application.properties` file is configured with the correct database connection details (username, password, and URL).

### 2. Clone and Build

1.  Clone the repository to your local machine:
    ```bash
    git clone [https://github.com/santsara/SocietySync.git](https://github.com/santsara/SocietySync.git)
    cd SocietySync
    ```
2.  Use Maven to clean the project and install dependencies:
    ```bash
    mvn clean install
    ```

### 3. Run the Application

Execute the Spring Boot application using Maven:

```bash
mvn spring-boot:run
