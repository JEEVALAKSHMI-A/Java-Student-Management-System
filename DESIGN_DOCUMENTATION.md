# Banking Management System – Design Documentation

## 1. Project Overview

The Banking Management System is a Java console-based application developed to manage basic banking operations. It allows users to create accounts, deposit money, withdraw money, check balances, and view transaction history.

## 2. Objectives

- Implement Object-Oriented Programming concepts in Java.
- Manage customer bank accounts.
- Perform deposit and withdrawal operations.
- Maintain transaction history.
- Demonstrate inheritance, abstraction, encapsulation, and polymorphism.

## 3. Technologies Used

- Programming Language: Java
- IDE: Visual Studio Code
- Data Structures: HashMap, ArrayList
- Concepts: OOP, Interfaces, Exception Handling

## 4. System Modules

1. Account Creation – Creates Savings or Current accounts.
2. Deposit – Adds money to an account.
3. Withdrawal – Withdraws money after checking the available balance and applicable fee.
4. Balance Enquiry – Displays the current account balance.
5. Transaction History – Displays account transactions.
6. Account Listing – Displays all created accounts.

## 5. Class Description

### Transaction Interface
Defines the deposit() and withdraw() operations.

### BankAccount (Abstract Class)
- Stores account number, account holder, and balance.
- Maintains transaction history.
- Implements deposit and withdrawal operations.
- Defines abstract methods for account type and withdrawal fee.

### SavingsAccount
Extends BankAccount and provides savings account-specific details.

### CurrentAccount
Extends BankAccount and applies a withdrawal fee of ₹10.

### BankingManagementSystem
Contains the main method and manages account creation, deposits, withdrawals, balance enquiries, and transaction history using a HashMap.

## 6. Object-Oriented Programming Concepts

| Concept | Implementation |
|---|---|
| Encapsulation | Private account fields |
| Inheritance | SavingsAccount and CurrentAccount extend BankAccount |
| Abstraction | Abstract BankAccount class |
| Polymorphism | Overridden account methods |
| Interface | Transaction interface |
| Constructor | Initializes bank account details |
| Method Overriding | Account-specific method implementations |

## 7. Data Structures Used

- HashMap: Stores accounts using account numbers as keys.
- ArrayList: Stores transaction history.

## 8. Application Workflow

1. Start the application.
2. Display the banking menu.
3. Select an operation.
4. Enter the required account details.
5. Validate the operation.
6. Update the account balance and transaction history.
7. Display the result.
8. Return to the menu or exit.

## 9. Limitations

- Account details are stored in memory only.
- Data is not retained after the application exits.
- This project is an educational simulation, not a real banking application.

## 10. Conclusion

The Banking Management System demonstrates the practical implementation of Java OOP concepts through a simple banking application. It provides experience in class design, inheritance, abstraction, interfaces, collections, and exception handling.