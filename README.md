Finance Dashboard Backend 
This is a backend application built with Spring Boot that manages financial records while supporting role-based 
access control. It is designed to demonstrate clean API design, proper access management, and structured data 
handling. 
The system supports three types of users: Admin, Analyst, and Viewer. Admin users can create, update, and 
delete records, as well as manage users. Analysts can view financial data and summary insights. Viewers have 
read-only access to records. 
 
Key Features 
• User and Role Management – Each user has a defined role with specific permissions.  
• Financial Records CRUD – Create, read, update, and delete financial records.  
• Filtering – Records can be filtered by type, category, and date range.  
• Dashboard Summaries – APIs provide total income, total expenses, and net balance.  
• Security – Role-based access control is enforced using Spring Security.  
• Data Persistence – Uses an in-memory H2 database for easy setup and testing.  
• Validation and Error Handling – Ensures reliable and meaningful API responses.  
 
Getting Started 
To run this project locally: 
1. Clone the repository using: 
https://github.com/sanjay038/finance-dashboard-backend.git  
2. Make sure you have Java 17+ and Maven installed.  
3. Build the project by running mvn clean install.  
4. Start the application using mvn spring-boot:run.  
The server will start on http://localhost:8080. 
 
Default Test Users 
For quick testing, you can use these credentials: 
• Admin – Username: admin, Password: admin123  
• Analyst – Username: analyst, Password: analyst123  
• Viewer – Username: viewer, Password: viewer123  
 
How to Access the Database 
The application uses an in-memory H2 database. You can access the H2 console at http://localhost:8080/h2
console using: 
• JDBC URL: jdbc:h2:mem:finance_db  
• Username: sa  
• Password: password  
 
API Endpoints 
• GET /api/records – List all records  
• POST /api/records – Create a new record (Admin only)  
• PUT /api/records/{id} – Update a record (Admin only)  
• DELETE /api/records/{id} – Delete a record (Admin only)  
• GET /api/dashboard/summary – Get income, expenses, and net balance  
• GET /api/users – List users (Admin only)  
• POST /api/users – Create a user (Admin only)  
You can filter records by type, category, or date range using query parameters. 
 
Notes 
This project is perfect for local development and testing purposes. For production, you can replace the in-memory 
H2 database with a more permanent solution like MySQL or PostgreSQL and configure stronger authentication. 
 
Author 
Sanjay S
