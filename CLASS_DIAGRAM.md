classDiagram
    class Transaction {
        <<interface>>
        +deposit(double amount)
        +withdraw(double amount)
    }
    class BankAccount {
        <<abstract>>
        -int accountNumber
        -String accountHolder
        -double balance
        -ArrayList transactionHistory
        +getBalance()
        +getAccountType()
        +getWithdrawalFee()
    }
    class SavingsAccount {
        +getAccountType()
        +getWithdrawalFee()
    }
    class CurrentAccount {
        +getAccountType()
        +getWithdrawalFee()
    }
    class BankingManagementSystem {
        -Scanner scanner
        -HashMap accounts
        +main()
        +createAccount()
        +depositMoney()
        +withdrawMoney()
    }
    Transaction <|.. BankAccount
    BankAccount <|-- SavingsAccount
    BankAccount <|-- CurrentAccount
    BankingManagementSystem --> BankAccount