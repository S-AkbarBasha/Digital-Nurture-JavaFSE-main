-- =========================================================================
-- Exercise 2: Error Handling
-- Scenario 3: AddNewCustomer stored procedure with duplicate handling.
-- =========================================================================

-- Enable DBMS_OUTPUT for printing to console
SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE AddNewCustomer (
    p_CustomerID IN NUMBER,
    p_Name       IN VARCHAR2,
    p_DOB        IN DATE,
    p_Balance    IN NUMBER
) AS
BEGIN
    DBMS_OUTPUT.PUT_LINE('--------------------------------------------------');
    DBMS_OUTPUT.PUT_LINE('Attempting to add new customer: ' || p_Name || ' (ID: ' || p_CustomerID || ')');
    
    -- Insert new customer record (IsVIP defaults to 'FALSE', LastModified to SYSDATE)
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified, IsVIP)
    VALUES (p_CustomerID, p_Name, p_DOB, p_Balance, SYSDATE, 'FALSE');
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('SUCCESS: Customer ' || p_Name || ' (ID: ' || p_CustomerID || ') added successfully.');
    
EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        -- Handle duplicate primary key exception
        DBMS_OUTPUT.PUT_LINE('ERROR: Customer with ID ' || p_CustomerID || ' already exists. Insertion aborted.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: Unexpected error during customer insertion: ' || SQLERRM);
END AddNewCustomer;
/

-- Test Cases script block (Copy and run to test)
/*
BEGIN
    -- Test Case 1: Insert customer with duplicate ID (ID = 1 already exists)
    AddNewCustomer(1, 'Duplicate John', TO_DATE('1985-05-15', 'YYYY-MM-DD'), 5000);
    
    -- Test Case 2: Insert customer with unique ID
    AddNewCustomer(4, 'Michael Green', TO_DATE('1992-11-04', 'YYYY-MM-DD'), 7500);
END;
/
*/
