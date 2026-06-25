-- =========================================================================
-- Exercise 1: Control Structures
-- Scenario 2: Promote customers to VIP status (IsVIP = 'TRUE') if balance > $10,000.
-- =========================================================================

-- Enable DBMS_OUTPUT for printing to console
SET SERVEROUTPUT ON;

DECLARE
    -- Cursor to iterate through all customers
    CURSOR c_customers IS
        SELECT CustomerID, Name, Balance, IsVIP
        FROM Customers;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Processing VIP Promotions ---');
    
    FOR r_cust IN c_customers LOOP
        -- Check if balance is greater than $10,000
        IF r_cust.Balance > 10000 THEN
            -- Update the IsVIP flag to 'TRUE'
            UPDATE Customers
            SET IsVIP = 'TRUE'
            WHERE CustomerID = r_cust.CustomerID;
            
            DBMS_OUTPUT.PUT_LINE('PROMOTED: ' || r_cust.Name || ' (ID: ' || r_cust.CustomerID || 
                                 ') has balance $' || TO_CHAR(r_cust.Balance, '999,999.99') || 
                                 '. Flag IsVIP set to TRUE.');
        ELSE
            DBMS_OUTPUT.PUT_LINE('No change: ' || r_cust.Name || ' (ID: ' || r_cust.CustomerID || 
                                 ') has balance $' || TO_CHAR(r_cust.Balance, '999,999.99') || 
                                 '. Keep VIP status: ' || r_cust.IsVIP);
        END IF;
    END LOOP;
    
    -- Save the changes
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('VIP status updates completed and committed.');
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Transaction rolled back. Details: ' || SQLERRM);
END;
/

-- Verification SELECT:
SELECT CustomerID, Name, Balance, IsVIP 
FROM Customers;
