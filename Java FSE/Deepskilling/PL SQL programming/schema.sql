-- =========================================================================
-- schema.sql
-- PL/SQL programming: Core Schema and Sample Data Setup
-- =========================================================================

-- Drop Tables (if they exist for clean environment reset)
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE AuditLog CASCADE CONSTRAINTS';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE Transactions CASCADE CONSTRAINTS';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE Loans CASCADE CONSTRAINTS';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE Accounts CASCADE CONSTRAINTS';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE Customers CASCADE CONSTRAINTS';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE Employees CASCADE CONSTRAINTS';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/

-- Drop Sequences
BEGIN
    EXECUTE IMMEDIATE 'DROP SEQUENCE Transactions_Seq';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/
BEGIN
    EXECUTE IMMEDIATE 'DROP SEQUENCE AuditLog_Seq';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/

-- Create Tables

CREATE TABLE Customers (
    CustomerID   NUMBER PRIMARY KEY,
    Name         VARCHAR2(100) NOT NULL,
    DOB          DATE NOT NULL,
    Balance      NUMBER DEFAULT 0,
    LastModified DATE DEFAULT SYSDATE,
    IsVIP        VARCHAR2(5) DEFAULT 'FALSE' CHECK (IsVIP IN ('TRUE', 'FALSE'))
);

CREATE TABLE Accounts (
    AccountID    NUMBER PRIMARY KEY,
    CustomerID   NUMBER,
    AccountType  VARCHAR2(20) CHECK (AccountType IN ('Savings', 'Checking')),
    Balance      NUMBER DEFAULT 0,
    LastModified DATE DEFAULT SYSDATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID) ON DELETE CASCADE
);

CREATE TABLE Transactions (
    TransactionID   NUMBER PRIMARY KEY,
    AccountID       NUMBER,
    TransactionDate DATE DEFAULT SYSDATE,
    Amount          NUMBER NOT NULL CHECK (Amount > 0),
    TransactionType VARCHAR2(10) CHECK (TransactionType IN ('Deposit', 'Withdrawal')),
    FOREIGN KEY (AccountID) REFERENCES Accounts(AccountID) ON DELETE CASCADE
);

CREATE TABLE Loans (
    LoanID       NUMBER PRIMARY KEY,
    CustomerID   NUMBER,
    LoanAmount   NUMBER NOT NULL CHECK (LoanAmount > 0),
    InterestRate NUMBER NOT NULL,
    StartDate    DATE DEFAULT SYSDATE,
    EndDate      DATE NOT NULL,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID) ON DELETE CASCADE
);

CREATE TABLE Employees (
    EmployeeID   NUMBER PRIMARY KEY,
    Name         VARCHAR2(100) NOT NULL,
    Position     VARCHAR2(50),
    Salary       NUMBER CHECK (Salary >= 0),
    Department   VARCHAR2(50),
    HireDate     DATE DEFAULT SYSDATE
);

-- AuditLog Table (to support transaction triggers/logs)
CREATE TABLE AuditLog (
    LogID           NUMBER PRIMARY KEY,
    TransactionID   NUMBER,
    AccountID       NUMBER,
    ActionDate      DATE DEFAULT SYSDATE,
    Amount          NUMBER,
    ActionType      VARCHAR2(50)
);

-- Create Sequences
CREATE SEQUENCE Transactions_Seq START WITH 3 INCREMENT BY 1;
CREATE SEQUENCE AuditLog_Seq START WITH 1 INCREMENT BY 1;

-- =========================================================================
-- Insert Sample Mock Data
-- =========================================================================

-- Customers
-- Customer 1: Age 71 (> 60), Balance $11,000 (> 10,000) -> eligible for VIP & loan discount
INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified, IsVIP)
VALUES (1, 'John Doe', TO_DATE('1955-05-15', 'YYYY-MM-DD'), 11000, SYSDATE, 'FALSE');

-- Customer 2: Age 36 (< 60), Balance $1,500 (< 10,000) -> not eligible for VIP or age discount
INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified, IsVIP)
VALUES (2, 'Jane Smith', TO_DATE('1990-07-20', 'YYYY-MM-DD'), 1500, SYSDATE, 'FALSE');

-- Customer 3: Age 76 (> 60), Balance $8,500 (< 10,000) -> eligible for interest discount, not VIP
INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified, IsVIP)
VALUES (3, 'Robert Miller', TO_DATE('1950-01-10', 'YYYY-MM-DD'), 8500, SYSDATE, 'FALSE');

-- Accounts
INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (1, 1, 'Savings', 11000, SYSDATE);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (2, 2, 'Checking', 1500, SYSDATE);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (3, 3, 'Savings', 8500, SYSDATE);

-- Transactions (ID 1 and 2 already used, next insert will use sequence Transactions_Seq)
INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (1, 1, SYSDATE - 5, 200, 'Deposit');

INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (2, 2, SYSDATE - 2, 300, 'Withdrawal');

-- Loans
-- Customer 1 has loan, is > 60 -> discount applicable
INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate)
VALUES (1, 1, 5000, 5.5, SYSDATE - 10, ADD_MONTHS(SYSDATE, 60));

-- Customer 2 has loan, due in 15 days -> reminder message should fetch this
INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate)
VALUES (2, 2, 12000, 7.0, SYSDATE - 5, SYSDATE + 15);

-- Customer 3 has loan, due in 45 days -> reminder message should NOT fetch this
INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate)
VALUES (3, 3, 3000, 6.2, SYSDATE - 8, SYSDATE + 45);

-- Employees
INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES (1, 'Alice Johnson', 'Manager', 70000, 'HR', TO_DATE('2015-06-15', 'YYYY-MM-DD'));

INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES (2, 'Bob Brown', 'Developer', 60000, 'IT', TO_DATE('2017-03-20', 'YYYY-MM-DD'));

COMMIT;
/
