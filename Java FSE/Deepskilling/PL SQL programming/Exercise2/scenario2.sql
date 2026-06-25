-- =========================================================================
-- Exercise 2: Error Handling
-- Scenario 2: UpdateSalary stored procedure with custom error logging.
-- =========================================================================

-- Enable DBMS_OUTPUT for printing to console
SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE UpdateSalary (
    p_EmployeeID IN NUMBER,
    p_Percentage IN NUMBER
) AS
    v_CurrentSalary NUMBER;
    v_NewSalary     NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--------------------------------------------------');
    DBMS_OUTPUT.PUT_LINE('Attempting to update salary for Employee ID: ' || p_EmployeeID || ' by ' || p_Percentage || '%');
    
    -- 1. Attempt to fetch current salary.
    -- If the EmployeeID does not exist, this query will raise NO_DATA_FOUND.
    SELECT Salary INTO v_CurrentSalary 
    FROM Employees 
    WHERE EmployeeID = p_EmployeeID
    FOR UPDATE;
    
    -- 2. Validate percentage (must be positive/zero, though negative updates are conceptually rare)
    IF p_Percentage < 0 THEN
        DBMS_OUTPUT.PUT_LINE('Warning: Negative salary adjustment requested.');
    END IF;

    -- 3. Calculate new salary
    v_NewSalary := v_CurrentSalary * (1 + (p_Percentage / 100));

    -- 4. Apply the salary increase
    UPDATE Employees
    SET Salary = v_NewSalary
    WHERE EmployeeID = p_EmployeeID;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('SUCCESS: Salary updated for Employee ID: ' || p_EmployeeID || 
                         ' | Old Salary: $' || TO_CHAR(v_CurrentSalary, '999,999.00') || 
                         ' | New Salary: $' || TO_CHAR(v_NewSalary, '999,999.00'));
                         
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        -- Handle missing employee record
        DBMS_OUTPUT.PUT_LINE('ERROR: Employee ID ' || p_EmployeeID || ' does not exist. No update performed.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: Unexpected error during update: ' || SQLERRM);
END UpdateSalary;
/

-- Test Cases script block (Copy and run to test)
/*
DECLARE
    v_sal NUMBER;
BEGIN
    -- Initial state check
    SELECT Salary INTO v_sal FROM Employees WHERE EmployeeID = 1;
    DBMS_OUTPUT.PUT_LINE('Initial Salary Employee 1: $' || v_sal);
    
    -- Test Case 1: Valid Update
    UpdateSalary(1, 10); -- 10% raise
    
    -- Test Case 2: Invalid Employee ID
    UpdateSalary(999, 10);
    
    -- Final state check
    SELECT Salary INTO v_sal FROM Employees WHERE EmployeeID = 1;
    DBMS_OUTPUT.PUT_LINE('Final Salary Employee 1: $' || v_sal);
END;
/
*/
