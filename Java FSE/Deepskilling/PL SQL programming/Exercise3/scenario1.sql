-- =========================================================================
-- Exercise 3: Stored Procedures
-- Scenario 1: ProcessMonthlyInterest stored procedure for Savings accounts.
-- =========================================================================

-- Enable DBMS_OUTPUT for printing to console
SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
    v_RowsUpdated NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--------------------------------------------------');
    DBMS_OUTPUT.PUT_LINE('Processing monthly interest for Savings accounts...');
    
    -- Update the balance of all Savings accounts by applying 1% interest
    -- and update their LastModified date to the current date.
    UPDATE Accounts
    SET Balance = Balance * 1.01,
        LastModified = SYSDATE
    WHERE AccountType = 'Savings';
    
    -- Save the number of rows updated
    v_RowsUpdated := SQL%ROWCOUNT;
    
    COMMIT;
    
    DBMS_OUTPUT.PUT_LINE('SUCCESS: Monthly interest processed successfully.');
    DBMS_OUTPUT.PUT_LINE('Total Savings Accounts updated: ' || v_RowsUpdated);
    
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: Failed to process monthly interest. Details: ' || SQLERRM);
END ProcessMonthlyInterest;
/

-- Test Cases script block (Copy and run to test)
/*
DECLARE
    v_bal1 NUMBER;
    v_bal2 NUMBER;
BEGIN
    -- Print balances of Savings accounts (ID 1 and 3 are savings, ID 2 is checking)
    SELECT Balance INTO v_bal1 FROM Accounts WHERE AccountID = 1;
    SELECT Balance INTO v_bal2 FROM Accounts WHERE AccountID = 3;
    DBMS_OUTPUT.PUT_LINE('Before Interest - Acct 1: $' || v_bal1 || ' | Acct 3: $' || v_bal2);
    
    -- Run Procedure
    ProcessMonthlyInterest;
    
    -- Print balances after interest
    SELECT Balance INTO v_bal1 FROM Accounts WHERE AccountID = 1;
    SELECT Balance INTO v_bal2 FROM Accounts WHERE AccountID = 3;
    DBMS_OUTPUT.PUT_LINE('After Interest - Acct 1: $' || v_bal1 || ' | Acct 3: $' || v_bal2);
END;
/
*/
