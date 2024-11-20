### **SimCRM - Customer Relationship Management System**

---

#### **Overview**

SimCRM is a simple and user-friendly Customer Relationship Management (CRM) software designed for businesses and individuals. It provides a GUI interface to manage customer information, including Business and Individual customers, and allows users to load and save data from/to CSV files.

This project demonstrates core Java concepts such as inheritance, encapsulation, and polymorphism, alongside Swing for GUI development.

---

#### **Features**

1. **Add Customers**  
   - Add **Business Customers** with company name.  
   - Add **Individual Customers** with phone numbers.

2. **View Customer Data**  
   - Display all customers in a table.

3. **Load/Save Customer Data**  
   - Load customer details from a CSV file.  
   - Save customer details to a CSV file.

4. **User Authentication**  
   - Simple login using username input at launch.

5. **Cross-Platform**  
   - Compatible with Windows, MacOS, and Linux.

---

#### **Class Descriptions**

1. **`Customer`**  
   Base class representing common attributes:  
   - `customerID`  
   - `name`  
   - `email`

2. **`BusinessCustomer`**  
   Inherits from `Customer` and adds:  
   - `companyName`

3. **`IndividualCustomer`**  
   Inherits from `Customer` and adds:  
   - `phoneNumber`

4. **`CRM`**  
   Manages a list of customers using an `ArrayList`.  
   Provides methods to:  
   - Add, remove, and clear customers.  
   - Retrieve all customers.

5. **`CRMGUI`**  
   Provides a graphical user interface to manage customers.  
   Core functionalities include adding customers, displaying data, and handling CSV operations.

---

#### **Technologies Used**

- **Java**  
   - Core OOP principles like inheritance, encapsulation, and polymorphism.  
   - Swing for GUI development.  

- **CSV File Operations**  
   - File I/O for importing/exporting data.

---

#### **How to Run**

1. **Prerequisites**  
   - Install Java Development Kit (JDK) version 8 or higher.  
   - Ensure your IDE or terminal is set up to run Java applications.

2. **Steps**  
   - Clone or download the project files.  
   - Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).  
   - Compile and run the `CRMGUI` class.  

3. **Login**  
   - Enter a username to log in to SimCRM.  

4. **Usage**  
   - Use the interface to add, view, load, and save customer data.

---

#### **Project File Structure**

```plaintext
SimCRM/
├── src/
│   ├── Customer.java
│   ├── BusinessCustomer.java
│   ├── IndividualCustomer.java
│   ├── CRM.java
│   ├── CRMGUI.java
│   └── simcrm_icon.png
└── README.md
```

---

#### **CSV File Format**

- **Business Customers:**  
  `CustomerID,Name,Email,CompanyName`

- **Individual Customers:**  
  `CustomerID,Name,Email,,PhoneNumber`  

**Example:**  
```csv
1,John Doe,johndoe@example.com,TechCorp,
2,Jane Smith,janesmith@example.com,,9876543210
```

---

#### **Screenshots**

1. **Main GUI**
   - Displays customer data in a table.  

2. **Add Customer Panel**
   - Input fields for Business/Individual customers.  

3. **Load/Save CSV**
   - Buttons to handle CSV operations.  

*(Add screenshots when ready)*

---

#### **Future Enhancements**

- Add search functionality to filter customers.  
- Add database integration for persistent storage.  
- Enhance authentication with login credentials (PASSWORD).  

---

#### **Contributors**

- **Your Name**  
   Email: f20230129@dubai.bits-pilani.ac.in

Feel free to fork this project or contribute by raising issues or submitting pull requests.

---

#### **License**

This project is licensed under the MIT License. See the `LICENSE` file for details.
