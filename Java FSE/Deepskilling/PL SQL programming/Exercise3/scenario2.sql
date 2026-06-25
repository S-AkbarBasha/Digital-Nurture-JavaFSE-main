-- =========================================================================
-- Exercise 3: Stored Procedures
-- Scenario 2: UpdateEmployeeBonus stored procedure.
-- =========================================================================

-- Enable DBMS_OUTPUT for printing to console
SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_Department      IN VARCHAR2,
    p_BonusPercentage IN NUMBER
) AS
    v_RowsUpdated NUMBER;
    e_invalid_bonus EXCEPTION;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--------------------------------------------------');
    DBMS_OUTPUT.PUT_LINE('Processing employee bonus scheme...');
    DBMS_OUTPUT.PUT_LINE('Department: ' || p_Department || ' | Bonus: ' || p_BonusPercentage || '%');
    
    -- 1. Validate the bonus percentage
    IF p_BonusPercentage < 0 THEN
        RAISE e_invalid_bonus;
    END IF;

    -- 2. Update employee salaries in the specified department
    UPDATE Employees
    SET Salary = Salary * (1 + (p_BonusPercentage / 100))
    WHERE Department = p_Department;
    
    v_RowsUpdated := SQL%ROWCOUNT;
    
    COMMIT;
    
    DBMS_OUTPUT.PUT_LINE('SUCCESS: Bonus updates completed.');
    DBMS_OUTPUT.PUT_LINE('Total employees updated in ' || p_Department || ' department: ' || v_RowsUpdated);

EXCEPTION
    WHEN e_invalid_bonus THEN
        DBMS_OUTPUT.PUT_LINE('ERROR: Bonus percentage cannot be negative. Update cancelled.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: Failed to update employee bonuses. Details: ' || SQLERRM);
END UpdateEmployeeBonus;
/

-- Test Cases script block (Copy and run to test)
/*
DECLARE
    v_sal NUMBER;
BEGIN
    -- Initial state check for Employee 1 (Department HR)
    SELECT Salary INTO v_sal FROM Employees WHERE EmployeeID = 1;
    DBMS_OUTPUT.PUT_LINE('Before Bonus (HR) - Alice Salary: $' || v_sal);
    
    -- Run Procedure to apply 5% bonus to HR
    UpdateEmployeeBonus('HR', 5);
    
    -- Final state check for Employee 1
    SELECT Salary INTO v_sal FROM Employees WHERE EmployeeID = 1;
    DBMS_OUTPUT.PUT_LINE('After Bonus (HR) - Alice Salary: $' || v_sal);
END;
/
*/
