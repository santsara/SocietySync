# SocietySync: Digital Society Management System
SocietySync is a comprehensive, web-based platform designed to **streamline and digitize** the core functions of residential society management. The primary goal is to promote **transparency, accountability, and collaboration** among residents and committee members.
This platform replaces fragmented, manual processes with a unified digital ecosystem, focusing on essential society needs using easily maintainable, open-source technologies.
---
## Key Features and Modules
The application provides a centralized dashboard built around six core modules:

| Module | Purpose | Functionality |
| :--- | :--- | :--- |
| **Fund Flow** | Transparent record keeping | Displays **current fund balance** (maintenance and donations), **festival-wise budget** tracking, and a list of **defaulters** with pending dues. |
| **Sectoral Distribution** | Accountability in expenditure | Provides a visual breakdown (pie chart) of fund allocation across major sectors (Security, Sanitation, Infrastructure). |
| **Quick Resolve** | Effective issue management | Simplifies **issue reporting** and tracking. Residents can raise queries, and committee members can update the status (e.g., Pending, In Progress, Resolved). |
| **Society Connect** | Fostering communication | An internal communication channel for residents and committee members to share important information and discuss society matters. |
| **Notice Board** | Centralized communication | Displays society announcements, event updates, and maintenance schedules. Urgent notices can be **highlighted** for immediate attention. |
| **Civic Circle** | Community networking | A searchable directory of **verified professionals** (doctors, lawyers, advisors) residing within the community, including contact details. |

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
