-- =========================================================================
-- Exercise 1: Control Structures
-- Scenario 3: Print reminder messages for loans due within the next 30 days.
-- =========================================================================

-- Enable DBMS_OUTPUT for printing to console
SET SERVEROUTPUT ON;

DECLARE
    -- Cursor to select loans due in the next 30 days
    CURSOR c_due_loans IS
        SELECT l.LoanID, c.CustomerID, c.Name, l.LoanAmount, l.EndDate
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE l.EndDate BETWEEN SYSDATE AND (SYSDATE + 30);
        
    v_count NUMBER := 0;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Processing Loan Due Date Reminders (Next 30 Days) ---');
    
    FOR r_loan IN c_due_loans LOOP
        v_count := v_count + 1;
        DBMS_OUTPUT.PUT_LINE('Reminder ' || v_count || ':');
        DBMS_OUTPUT.PUT_LINE('  Dear ' || r_loan.Name || ' (CustomerID: ' || r_loan.CustomerID || '),');
        DBMS_OUTPUT.PUT_LINE('  Your loan (ID: ' || r_loan.LoanID || ') with balance $' || 
                             TO_CHAR(r_loan.LoanAmount, '999,999.99') || 
                             ' is due on ' || TO_CHAR(r_loan.EndDate, 'YYYY-MM-DD') || '.');
        DBMS_OUTPUT.PUT_LINE('  Please ensure that funds are available for repayment.');
        DBMS_OUTPUT.PUT_LINE('--------------------------------------------------');
    END LOOP;
    
    IF v_count = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No loans are due within the next 30 days.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Total reminders generated: ' || v_count);
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error processing reminders: ' || SQLERRM);
END;
/
