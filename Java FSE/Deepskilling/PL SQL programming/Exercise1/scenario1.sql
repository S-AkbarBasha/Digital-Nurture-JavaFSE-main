-- =========================================================================
-- Exercise 1: Control Structures
-- Scenario 1: Apply a 1% discount to interest rates for customers > 60 years old.
-- =========================================================================

-- Enable DBMS_OUTPUT for printing to console
SET SERVEROUTPUT ON;

DECLARE
    -- Cursor to select customers and their loans
    CURSOR c_customers_loans IS
        SELECT c.CustomerID, c.Name, c.DOB, l.LoanID, l.InterestRate
        FROM Customers c
        JOIN Loans l ON c.CustomerID = l.CustomerID;
        
    v_age NUMBER;
    v_new_rate NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Processing Age-Based Loan Interest Discounts ---');
    
    FOR r_record IN c_customers_loans LOOP
        -- Calculate current age based on Date of Birth (DOB)
        v_age := FLOOR(MONTHS_BETWEEN(SYSDATE, r_record.DOB) / 12);
        
        -- Check if customer is older than 60
        IF v_age > 60 THEN
            -- Apply 1% (1 percentage point) discount to the interest rate
            v_new_rate := r_record.InterestRate - 1;
            
            -- Keep interest rate positive
            IF v_new_rate < 0 THEN
                v_new_rate := 0;
            END IF;
            
            -- Update the loan interest rate
            UPDATE Loans
            SET InterestRate = v_new_rate
            WHERE LoanID = r_record.LoanID;
            
            DBMS_OUTPUT.PUT_LINE('Applied 1% discount for Customer: ' || r_record.Name || 
                                 ' (Age: ' || v_age || '). Loan ID: ' || r_record.LoanID || 
                                 ' | Old Rate: ' || r_record.InterestRate || '% | New Rate: ' || v_new_rate || '%');
        ELSE
            DBMS_OUTPUT.PUT_LINE('Skipped Customer: ' || r_record.Name || 
                                 ' (Age: ' || v_age || ') - Not above 60 years old.');
        END IF;
    END LOOP;
    
    -- Save the updates
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Interest rate discount updates completed and committed.');
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Transaction rolled back. Details: ' || SQLERRM);
END;
/

-- Verification SELECT:
SELECT c.Name, FLOOR(MONTHS_BETWEEN(SYSDATE, c.DOB)/12) AS Age, l.LoanID, l.InterestRate
FROM Customers c
JOIN Loans l ON c.CustomerID = l.CustomerID;
